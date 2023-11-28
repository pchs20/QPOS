from rest_framework import serializers
from django.contrib.auth.models import User
from django.contrib.auth.hashers import check_password
from django.contrib.auth.password_validation import validate_password
from django.shortcuts import get_object_or_404
from rest_framework.authtoken.models import Token

from .models import Proveidor, Producte, Client, Treballador, Admin, Usuari, Compra, LiniaCompra, Esdeveniment, \
    AssistenciaAEsdeveniment


class ProveidorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Proveidor
        fields = '__all__'


class ProducteSerializer(serializers.ModelSerializer):
    proveidor = ProveidorSerializer(read_only=True)
    proveidor_id = serializers.PrimaryKeyRelatedField(
        allow_null=False,
        queryset=Proveidor.objects.all(),
        write_only=True,
        required=True,
        source='proveidor'
    )
    
    class Meta:
        model = Producte
        fields = '__all__'


class UsuariChildrenSerializer(serializers.ModelSerializer):
    id = serializers.IntegerField(source="usuari.user.id", read_only=True)
    user = serializers.CharField(source="usuari.user")
    username = serializers.CharField(source="usuari.user.username")
    nom = serializers.CharField(source="usuari.user.first_name", required=False)
    cognoms = serializers.CharField(source="usuari.user.last_name", required=False)
    email = serializers.CharField(source="usuari.user.email", required=False)
    password = serializers.CharField(source="usuari.user.password", required=False, write_only=True)
    dni = serializers.CharField(source="usuari.dni", required=False)
    bio = serializers.CharField(source="usuari.bio", required=False)
    dataNaixement = serializers.CharField(source="usuari.dataNaixement", required=False)
    telefon = serializers.CharField(source="usuari.telefon", required=False)
    imatge = serializers.CharField(source="usuari.imatge", required=False)

    class Meta:
        model = Client
        fields = ('id', 'user', 'username', 'nom', 'cognoms', 'email', 'password', 'dni', 'bio', 'dataNaixement', 'telefon', 'imatge')


class LiniaCompraSerializer(serializers.ModelSerializer):
    producte = ProducteSerializer(read_only=True)
    producte_id = serializers.PrimaryKeyRelatedField(
        allow_null=False,
        queryset=Producte.objects.all(),
        write_only=True,
        required=True,
        source='producte'
    )

    class Meta:
        model = LiniaCompra
        fields = '__all__'


class CompraSerializer(serializers.ModelSerializer):
    liniesCompra = LiniaCompraSerializer(many=True, read_only=True)
    client = UsuariChildrenSerializer(read_only=True)
    client_id = serializers.PrimaryKeyRelatedField(
        allow_null=False,
        queryset=Client.objects.all(),
        write_only=True,
        required=True,
        source='client'
    )
    treballador = UsuariChildrenSerializer(read_only=True)
    treballador_id = serializers.PrimaryKeyRelatedField(
        allow_null=False,
        queryset=Treballador.objects.all(),
        write_only=True,
        required=True,
        source='treballador'
    )
    linies = serializers.ListField(write_only=True, required=True)
    importFinal = serializers.FloatField(read_only=True)

    def create(self, validated_data):
        linies_data = validated_data.pop("linies", None)
        validated_data['importFinal'] = 0.0
        compra = super().create(validated_data)

        total = 0.0
        if linies_data:
            for linia in linies_data:
                quantitat = linia['quantitat']
                producte = get_object_or_404(Producte, id=linia['producte'])
                LiniaCompra.objects.create(quantitat=quantitat, producte=producte, compra=compra)
                total += quantitat*producte.preu
        compra.importFinal = total

        return compra

    class Meta:
        model = Compra
        fields = ('id', 'data', 'client', 'client_id', 'treballador', 'treballador_id', 'liniesCompra', 'linies', 'importFinal')


class AssistenciaAEsdevenimentClientSerializer(serializers.ModelSerializer):
    client = UsuariChildrenSerializer(read_only=True)

    class Meta:
        model = AssistenciaAEsdeveniment
        fields = ('client',)


class EsdevenimentSerializer(serializers.ModelSerializer):
    creador = UsuariChildrenSerializer(read_only=True)
    creador_id = serializers.PrimaryKeyRelatedField(
        allow_null=False,
        queryset=Admin.objects.all(),
        write_only=True,
        required=True,
        source='creador'
    )
    assistencies = AssistenciaAEsdevenimentClientSerializer(read_only=True, many=True)

    class Meta:
        model = Esdeveniment
        fields = ('id', 'nom', 'descripcio', 'dataCreacio', 'data', 'aforament', 'durada', 'ubicacio', 'creador', 'creador_id', 'assistencies')


class AssistenciaAEsdevenimentSerializer(serializers.ModelSerializer):
    class Meta:
        model = AssistenciaAEsdeveniment
        fields = '__all__'


# LOGIN
def validacioLogin(data):
    username = data.get("username", None)
    password = data.get("password", None)

    try:
        user = User.objects.get(username=username)
    except User.DoesNotExist:
        raise serializers.ValidationError("No existeix un usuari amb aquest username.")

    pwd_valid = check_password(password, user.password)

    if not pwd_valid:
        raise serializers.ValidationError("Contrasenya incorrecta.")
    return user


def creacioLogin(data, user):
    token, created = Token.objects.get_or_create(user=user)
    data['token'] = token.key
    data['created'] = created
    return data


class LoginUsuariChildrenSerializer(UsuariChildrenSerializer):
    username = serializers.CharField()
    password = serializers.CharField(max_length=128, write_only=True, required=True)
    token = serializers.CharField(required=False, read_only=True)
    id = serializers.IntegerField(required=False, read_only=True, source='user.id')

    class Meta:
        model = Client
        fields = ('username', 'password', 'token', 'id')

    def create(self, data):
        user = self.context['user']
        data = creacioLogin(data, user)
        data['user'] = user
        return data


class LoginClientSerializer(LoginUsuariChildrenSerializer):
    def validate(self, data):
        user = validacioLogin(data)
        try:
            _ = user.usuari.client
        except:
            raise serializers.ValidationError("No existeix un client amb aquest username.")
        self.context['user'] = user

        return data


class LoginTreballadorSerializer(LoginUsuariChildrenSerializer):
    def validate(self, data):
        user = validacioLogin(data)
        try:
            _ = user.usuari.treballador
        except:
            raise serializers.ValidationError("No existeix un treballador amb aquest username.")
        self.context['user'] = user

        return data


class LoginAdminSerializer(LoginUsuariChildrenSerializer):
    def validate(self, data):
        user = validacioLogin(data)
        try:
            _ = user.usuari.admin
        except:
            raise serializers.ValidationError("No existeix un admin amb aquest username.")
        self.context['user'] = user

        return data


# SIGN UP
def validacioSignUp(data):
    if data['password'] != data['password2']:
            raise serializers.ValidationError({"password": "Password fields didn't match."})
    return data

def validacioLogin(data):
    username = data.get("username", None)
    password = data.get("password", None)

    try:
        user = User.objects.get(username=username)
    except User.DoesNotExist:
        raise serializers.ValidationError("No existeix un usuari amb aquest username.")

    pwd_valid = check_password(password, user.password)

    if not pwd_valid:
        raise serializers.ValidationError("Contrasenya incorrecta.")
    return user


def creacioUsuari(data):
    username = data.get("username", None)
    email = data.get("email", None)
    nom = data.get("nom", None)
    cognoms = data.get("cognoms", None)

    user = User.objects.create(
            username=username,
            email=email,
            first_name=nom,
            last_name=cognoms
        )
    user.is_active = True

    user.set_password(data.get('password'))
    user.save()

    token, created = Token.objects.get_or_create(user=user)
    data['token'] = token.key
    data['created'] = created

    dni = data.get("dni", None)
    bio = data.get("bio", None)
    dataNaixement = data.get("dataNaixement", None)
    telefon = data.get("telefon", None)

    usuari = Usuari.objects.create(
        user=user,
        dni=dni,
        bio=bio,
        dataNaixement=dataNaixement,
        telefon=telefon,
    )

    return usuari, data


class SignUpUsuariChildrenSerializer(UsuariChildrenSerializer):
    email = serializers.EmailField(required=True)
    username = serializers.CharField(max_length=255, required=True)
    password = serializers.CharField(write_only=True, required=True, validators=[validate_password])
    password2 = serializers.CharField(write_only=True, required=True)
    nom = serializers.CharField(required=True)
    cognoms = serializers.CharField(required=True)
    dni = serializers.CharField(required=True)
    bio = serializers.CharField(required=False)
    dataNaixement = serializers.DateField(required=False)
    telefon = serializers.IntegerField(required=False)
    token = serializers.CharField(required=False, read_only=True)
    created = serializers.BooleanField(required=False, read_only=True)

    class Meta:
        model = Client
        fields = ('email', 'username', 'password', 'password2', 'dni', 'dataNaixement',
                  'telefon', 'token', 'created', 'nom', 'cognoms')

    def validate(self, data):
        return validacioSignUp(data)


class SignUpClientSerializer(SignUpUsuariChildrenSerializer):
    class Meta:
        model = Client
        fields = ('email', 'username', 'password', 'password2', 'dni', 'dataNaixement',
                  'telefon', 'token', 'created', 'nom', 'cognoms')

    def create(self, data):
        usuari, data = creacioUsuari(data)
        Client.objects.create(usuari=usuari)
        data['usuari'] = usuari
        return data


class SignUpTreballadorSerializer(SignUpUsuariChildrenSerializer):
    class Meta:
        model = Treballador
        fields = ('email', 'username', 'password', 'password2', 'dni', 'dataNaixement',
                  'telefon', 'token', 'created', 'nom', 'cognoms')

    def create(self, data):
        usuari, data = creacioUsuari(data)
        Treballador.objects.create(usuari=usuari)
        data['usuari'] = usuari
        return data


class SignUpAdminSerializer(SignUpUsuariChildrenSerializer):
    class Meta:
        model = Admin
        fields = ('email', 'username', 'password', 'password2', 'dni', 'dataNaixement',
                  'telefon', 'token', 'created', 'nom', 'cognoms')

    def create(self, data):
        usuari, data = creacioUsuari(data)
        Admin.objects.create(usuari=usuari)
        data['usuari'] = usuari
        return data
