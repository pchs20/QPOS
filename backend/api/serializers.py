from rest_framework import serializers

from .models import Proveidor, Producte


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
