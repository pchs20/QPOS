from rest_framework import viewsets, filters

from .models import Producte
from .serializers import ProducteSerializer


class ProductesView(viewsets.ModelViewSet):
    queryset = Producte.objects.all()
    serializer_class = ProducteSerializer
    models = Producte
