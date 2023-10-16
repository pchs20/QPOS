from rest_framework import serializers

from .models import Proveidor, Producte


class ProveidorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Proveidor
        fields = '__all__'


class ProducteSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producte
        fields = '__all__'
