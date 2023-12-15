from django.test import TestCase
from rest_framework.test import APIRequestFactory

from django.contrib.auth.models import User
from .models import Usuari, Client, Treballador, Admin
from . import permissions


class TestPermissions(TestCase):

    def setUp(self) -> None:
        # Client
        self.userClient = User.objects.create(
            id=1,
            username='usuariClient',
            is_active=True
        )
        self.usuariClient = Usuari.objects.create(
            user=self.userClient
        )
        self.client = Client.objects.create(
            usuari=self.usuariClient
        )

        # Treballador
        self.userTreballador = User.objects.create(
            id=2,
            username='usuariTreballador',
            is_active=True
        )
        self.usuariTreballador = Usuari.objects.create(
            user=self.userTreballador
        )
        self.treballador = Treballador.objects.create(
            usuari=self.usuariTreballador
        )

        # Admin
        self.userAdmin = User.objects.create(
            id=3,
            username='usuariAdmin',
            is_active=True
        )
        self.usuariAdmin = Usuari.objects.create(
            user=self.userAdmin
        )
        self.admin = Admin.objects.create(
            usuari=self.usuariAdmin
        )

    # Qualsevol usuari, com un cclient, està autenticat
    def test_authenticated_true(self):
        request = APIRequestFactory().get('')
        request.user = self.userClient
        permission = permissions.IsAuthenticated()
        self.assertTrue(permission.has_permission(request))

    # Si no hi ha usuari no està autenticat
    def test_authenticated_false(self):
        request = APIRequestFactory().get('')
        request.user = None
        permission = permissions.IsAuthenticated()
        self.assertFalse(permission.has_permission(request))

    # Comprovació de si ets client
    def test_client_true(self):
        request = APIRequestFactory().get('')
        request.user = self.userClient
        permission = permissions.IsClient()
        self.assertTrue(permission.has_permission(request))

    # Comprovació de si no ets client
    def test_client_false(self):
        request = APIRequestFactory().get('')
        request.user = self.userTreballador
        permission = permissions.IsClient()
        self.assertFalse(permission.has_permission(request))

    # Comprovació de si ets treballador
    def test_treballador_true(self):
        request = APIRequestFactory().get('')
        request.user = self.userTreballador
        permission = permissions.IsTreballador()
        self.assertTrue(permission.has_permission(request))

    # Comprovació de si no ets treballador
    def test_treballador_false(self):
        request = APIRequestFactory().get('')
        request.user = self.userClient
        permission = permissions.IsTreballador()
        self.assertFalse(permission.has_permission(request))

    # Comprovació de si ets administrador
    def test_admin_true(self):
        request = APIRequestFactory().get('')
        request.user = self.userAdmin
        permission = permissions.IsAdmin()
        self.assertTrue(permission.has_permission(request))

    # Comprovació de si no ets administrador
    def test_admin_false(self):
        request = APIRequestFactory().get('')
        request.user = self.userClient
        permission = permissions.IsAdmin()
        self.assertFalse(permission.has_permission(request))
