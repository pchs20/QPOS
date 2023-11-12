from rest_framework import viewsets

from . import permissions
from .models import Proveidor, Producte, Client, Treballador, Admin
from .serializers import ProveidorSerializer, ProducteSerializer, UsuariChildrenSerializer, LoginClientSerializer, \
    LoginTreballadorSerializer, LoginAdminSerializer, SignUpClientSerializer, SignUpTreballadorSerializer, SignUpAdminSerializer


class ProveidorsView(viewsets.ModelViewSet):
    queryset = Proveidor.objects.all()
    serializer_class = ProveidorSerializer
    models = Proveidor
    permission_classes = [permissions.IsAuthenticated]


class ProductesView(viewsets.ModelViewSet):
    queryset = Producte.objects.all()
    serializer_class = ProducteSerializer
    models = Producte
    permission_classes = [permissions.IsAuthenticated]


class ClientsView(viewsets.ModelViewSet):
    queryset = Client.objects.all()
    serializer_class = UsuariChildrenSerializer
    models = Client
    permission_classes = [permissions.IsAuthenticated]


class TreballadorsView(viewsets.ModelViewSet):
    queryset = Treballador.objects.all()
    serializer_class = UsuariChildrenSerializer
    models = Treballador
    permission_classes = [permissions.IsTreballador | permissions.IsAdmin]


class AdminsView(viewsets.ModelViewSet):
    queryset = Admin.objects.all()
    serializer_class = UsuariChildrenSerializer
    models = Admin
    permission_classes = [permissions.IsAdmin]


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


class SignUpClientView(viewsets.ModelViewSet):
    queryset = Client.objects.all()
    serializer_class = SignUpClientSerializer
    models = Client


class SignUpTreballadorView(viewsets.ModelViewSet):
    queryset = Treballador.objects.all()
    serializer_class = SignUpTreballadorSerializer
    models = Treballador


class SignUpAdminView(viewsets.ModelViewSet):
    queryset = Admin.objects.all()
    serializer_class = SignUpAdminSerializer
    models = Admin
