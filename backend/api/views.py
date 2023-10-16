from rest_framework import viewsets

from .models import Proveidor, Producte
from .serializers import ProveidorSerializer, ProducteSerializer


class ProveidorsView(viewsets.ModelViewSet):
    queryset = Proveidor.objects.all()
    serializer_class = ProveidorSerializer
    models = Proveidor


class ProductesView(viewsets.ModelViewSet):
    queryset = Producte.objects.all()
    serializer_class = ProducteSerializer
    models = Producte
