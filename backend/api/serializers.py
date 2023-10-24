from rest_framework import serializers
from django.contrib.auth.models import User
from django.contrib.auth.hashers import check_password
from rest_framework.authtoken.models import Token

from .models import Proveidor, Producte, Client, Treballador, Admin


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
    user = serializers.CharField(source="usuari.user")
    username = serializers.CharField(source="usuari.user.username", read_only=True)
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
        fields = ('user', 'username', 'nom', 'cognoms', 'email', 'password', 'dni', 'bio', 'dataNaixement', 'telefon', 'imatge')


# LOGIN, SIGNUP
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

    class Meta:
        model = Client
        fields = ('username', 'password', 'token')

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
