from rest_framework import viewsets, filters, mixins, status
from rest_framework import permissions as permissions_drf
from rest_framework.decorators import action
from rest_framework.response import Response
from rest_framework.exceptions import PermissionDenied
from django_filters.rest_framework import DjangoFilterBackend
from django.shortcuts import get_object_or_404

from . import permissions
from .models import Proveidor, Producte, Client, Treballador, Admin, Compra, Esdeveniment, AssistenciaAEsdeveniment, \
    Cupo, CuponsClients
from .serializers import ProveidorSerializer, ProducteSerializer, UsuariChildrenSerializer, LoginClientSerializer, \
    LoginTreballadorSerializer, LoginAdminSerializer, SignUpClientSerializer, SignUpTreballadorSerializer, \
    SignUpAdminSerializer, CompraSerializer, EsdevenimentSerializer, AssistenciaAEsdevenimentSerializer, \
    ClientSerializer, CupoSerializer


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
    filter_backends = [DjangoFilterBackend, filters.SearchFilter, filters.OrderingFilter]

    filterset_fields = {
        'id': ['exact', 'in'],
        'nom': ['exact', 'in', 'contains'],
        'preu': ['exact', 'in', 'range'],
        'proveidor': ['exact', 'in'],
    }
    search_fields = ['nom', 'descripcio']
    ordering_fields = ['id', 'nom', 'preu', 'estoc']


class ClientsView(viewsets.ModelViewSet):
    queryset = Client.objects.all()
    serializer_class = ClientSerializer
    models = Client
    permission_classes = [permissions.IsAuthenticated]
    filter_backends = [DjangoFilterBackend, filters.SearchFilter, filters.OrderingFilter]

    filterset_fields = {
        'usuari__user__id': ['exact', 'in'],
        'usuari__user__first_name': ['exact', 'in', 'contains'],
        'usuari__user__last_name': ['exact', 'in', 'contains'],
        'usuari__dni': ['exact', 'in', 'contains'],
    }
    search_fields = ['usuari__user__first_name', 'usuari__user__last_name']
    ordering_fields = ['usuari__user__id', 'usuari__dni']

    def check_object_permissions(self, request, obj):
        if request.method not in permissions_drf.SAFE_METHODS and request.user.usuari.client != obj:
            raise PermissionDenied("No tens permís per executar aquesta acció.")

    def update(self, request, *args, **kwargs):
        client = self.get_object()

        username = request.data.get('username', None)
        if username: client.usuari.user.username = username
        nom = request.data.get('nom', None)
        if nom: client.usuari.user.first_name = nom
        cognoms = request.data.get('cognoms', None)
        if cognoms: client.usuari.user.last_name = cognoms
        email = request.data.get('email', None)
        if email: client.usuari.user.email = email
        dni = request.data.get('dni', None)
        if dni: client.usuari.dni = dni
        bio = request.data.get('bio', None)
        if bio: client.usuari.bio = bio
        dataNaixement = request.data.get('dataNaixement', None)
        if dataNaixement: client.usuari.dataNaixement = dataNaixement
        telefon = request.data.get('telefon', None)
        if telefon: client.usuari.telefon = telefon

        client.usuari.user.save()
        client.usuari.save()
        client.save()

        serializer = self.get_serializer(client)
        return Response(serializer.data)

    @action(detail=False, methods=['post'])
    def punts(self, request):
        data = request.data
        if "client" not in data or "punts" not in data:
            return Response("Cal donar els camps client i punts", status=status.HTTP_400_BAD_REQUEST)

        client = get_object_or_404(Client, usuari__user__id=data["client"])
        difPunts = data["punts"]

        client.punts += difPunts
        client.save()

        serializer = self.get_serializer(client)
        return Response(serializer.data, status=status.HTTP_200_OK)


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
        if (not (client is None or esdeveniment is None)):
            interes = get_object_or_404(self.queryset, client=client, esdeveniment=esdeveniment)
            interes.delete()
            return Response(status=200, data={
                'message': f'S\'ha eliminat de forma correcta l\'assistència del client {client} a l\'esdeveniment amb codi {esdeveniment}'})
        return Response(status=500, data={
            'error': 'La Request ha de tenir dos paràmetres: client (id del client) i esdeveniment (codi de l\'esdeveniment)',
            'client indicat': client, 'esdeveniment indicat': esdeveniment})


class CuponsView(viewsets.ModelViewSet):
    queryset = Cupo.objects.all()
    serializer_class = CupoSerializer
    models = Cupo
    permissions = [permissions.IsAuthenticated]


# LOGIN I SIGNUP
class LoginClientView(mixins.CreateModelMixin, viewsets.GenericViewSet):
    queryset = Client.objects.all()
    serializer_class = LoginClientSerializer
    models = Client


class LoginTreballadorView(mixins.CreateModelMixin, viewsets.GenericViewSet):
    queryset = Treballador.objects.all()
    serializer_class = LoginTreballadorSerializer
    models = Treballador


class LoginAdminView(mixins.CreateModelMixin, viewsets.GenericViewSet):
    queryset = Admin.objects.all()
    serializer_class = LoginAdminSerializer
    models = Admin


class SignUpClientView(mixins.CreateModelMixin, viewsets.GenericViewSet):
    queryset = Client.objects.all()
    serializer_class = SignUpClientSerializer
    models = Client


class SignUpTreballadorView(mixins.CreateModelMixin, viewsets.GenericViewSet):
    queryset = Treballador.objects.all()
    serializer_class = SignUpTreballadorSerializer
    models = Treballador


class SignUpAdminView(mixins.CreateModelMixin, viewsets.GenericViewSet):
    queryset = Admin.objects.all()
    serializer_class = SignUpAdminSerializer
    models = Admin
