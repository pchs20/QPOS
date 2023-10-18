from rest_framework import serializers

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
