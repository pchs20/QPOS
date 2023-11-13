from rest_framework import viewsets
from rest_framework.decorators import action
from rest_framework.response import Response
from django.shortcuts import get_object_or_404

from . import permissions
from .models import Proveidor, Producte, Client, Treballador, Admin, Compra, Esdeveniment, AssistenciaAEsdeveniment
from .serializers import ProveidorSerializer, ProducteSerializer, UsuariChildrenSerializer, LoginClientSerializer, \
    LoginTreballadorSerializer, LoginAdminSerializer, SignUpClientSerializer, SignUpTreballadorSerializer, \
    SignUpAdminSerializer, CompraSerializer, EsdevenimentSerializer, AssistenciaAEsdevenimentSerializer


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


class CompresView(viewsets.ModelViewSet):
    queryset = Compra.objects.all()
    serializer_class = CompraSerializer
    models = Compra
    permission_classes = [permissions.IsAuthenticated]


class EsdevenimentsView(viewsets.ModelViewSet):
    queryset = Esdeveniment.objects.all()
    serializer_class = EsdevenimentSerializer
    models = Esdeveniment
    permission_classes = [permissions.IsAuthenticated]


class AssistenciaAEsdevenimentView(viewsets.ModelViewSet):
    queryset = AssistenciaAEsdeveniment.objects.all()
    serializer_class = AssistenciaAEsdevenimentSerializer
    permission_classes = [permissions.IsAuthenticated]

    # Redefinició del mètode DELETE per tal de poder-lo realitzar a la ListView i a través de paràmetres, en aquest cas l'username i el codi de l'esdeveniment.
    # (Les ListViews només suporten les operacions GET i POST, les DELETE requests només estan permeses a les DetailViews)
    @action(methods=['delete'], detail=False)
    def delete(self, request):
        client = request.GET.get('client', None)
        esdeveniment = request.GET.get('esdeveniment', None)
        if(not (client is None or esdeveniment is None)):
            interes = get_object_or_404(self.queryset, client=client, esdeveniment=esdeveniment)
            interes.delete()
            return Response(status=200, data={'message': f'S\'ha eliminat de forma correcta l\'assistència del client {client} a l\'esdeveniment amb codi {esdeveniment}'})
        return Response(status=500, data={'error': 'La Request ha de tenir dos paràmetres: client (id del client) i esdeveniment (codi de l\'esdeveniment)', 'client indicat': client, 'esdeveniment indicat': esdeveniment})


# LOGIN I SIGNUP
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
