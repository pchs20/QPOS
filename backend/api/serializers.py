from rest_framework import serializers

from django.shortcuts import get_object_or_404

from .models import Producte


class ProducteSerializer(serializers.ModelSerializer):
    class Meta:
        model = Producte
        fields = '__all__'
