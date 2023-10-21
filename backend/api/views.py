from rest_framework import viewsets

from .models import Proveidor, Producte, Client, Treballador, Admin
from .serializers import ProveidorSerializer, ProducteSerializer, UsuariChildrenSerializer, LoginClientSerializer, \
    LoginTreballadorSerializer, LoginAdminSerializer


class ProveidorsView(viewsets.ModelViewSet):
    queryset = Proveidor.objects.all()
    serializer_class = ProveidorSerializer
    models = Proveidor


class ProductesView(viewsets.ModelViewSet):
    queryset = Producte.objects.all()
    serializer_class = ProducteSerializer
    models = Producte


class ClientsView(viewsets.ModelViewSet):
    queryset = Client.objects.all()
    serializer_class = UsuariChildrenSerializer
    models = Client


class TreballadorsView(viewsets.ModelViewSet):
    queryset = Treballador.objects.all()
    serializer_class = UsuariChildrenSerializer
    models = Treballador


class AdminsView(viewsets.ModelViewSet):
    queryset = Admin.objects.all()
    serializer_class = UsuariChildrenSerializer
    models = Admin


class LoginClientView(viewsets.ModelViewSet):
    queryset = Client.objects.all()
    serializer_class = LoginClientSerializer
    models = Client


class LoginTreballadorView(viewsets.ModelViewSet):
    queryset = Treballador.objects.all()
    serializer_class = LoginTreballadorSerializer
    models = Treballador


class LoginAdminView(viewsets.ModelViewSet):
    queryset = Admin.objects.all()
    serializer_class = LoginAdminSerializer
    models = Admin
