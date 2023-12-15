from django.test import TestCase
from rest_framework.test import APIRequestFactory
from django.urls import reverse, resolve
from rest_framework import status

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


class TestSignUpLogIn(TestCase):

    def setUp(self) -> None:
        self.email_client = "client@test.com"
        self.email_treballador = "treballador@test.com"
        self.email_admin = "admin@test.com"
        self.username_client = "testClient"
        self.username_treballador = "testTreballador"
        self.username_admin = "testAdmin"
        self.dni_client = "345"
        self.dni_treballador = "456"
        self.dni_admin = "567"
        self.nom_client = "Client"
        self.nom_treballador = "Treballador"
        self.nom_admin = "Admin"
        self.cognoms_client = "Client"
        self.cognoms_treballador = "Treballador"
        self.cognoms_admin = "Admin"
        self.password = "contrasenya4321"

    # Comprovació de Sign Up i Login d'un Client cas d'éxit
    def test_successful_register_client(self):
        # Sign Up Client
        urlSignUp = reverse('signup_clients-list')
        response = self.client.post(urlSignUp,
                                    {"email": self.email_client, "username": self.username_client,
                                     "dni": self.dni_client, "nom": self.nom_client, "cognoms": self.cognoms_client,
                                     "password": self.password, "password2": self.password})
        self.assertEquals(response.status_code, status.HTTP_201_CREATED)

        # Login Client
        urlLogIn = reverse('login_clients-list')
        response2 = self.client.post(urlLogIn,
                                     {"username": self.username_client, "password": self.password})
        self.assertEquals(response2.status_code, status.HTTP_201_CREATED)

        self.assertEquals(response.json()['token'], response2.json()['token'])

    # Comprovació de Sign Up i Login d'un Treballador cas d'éxit
    def test_successful_register_and_login_treballador(self):
        # Sign Up Treballador
        urlSignUp = reverse('signup_treballadors-list')
        response = self.client.post(urlSignUp,
                                    {"email": self.email_treballador, "username": self.username_treballador,
                                     "dni": self.dni_treballador, "nom": self.nom_treballador, "cognoms": self.cognoms_treballador,
                                     "password": self.password, "password2": self.password})
        self.assertEquals(response.status_code, status.HTTP_201_CREATED)

        # Login Treballador
        urlLogIn = reverse('login_treballadors-list')
        response2 = self.client.post(urlLogIn,
                                     {"username": self.username_treballador, "password": self.password})
        self.assertEquals(response2.status_code, status.HTTP_201_CREATED)

        self.assertEquals(response.json()['token'], response2.json()['token'])

    # Comprovació de Sign Up i Login d'un Admin cas d'éxit
    def test_successful_register_and_login_admin(self):
        # Sign Up Admin
        urlSignUp = reverse('signup_admins-list')
        response = self.client.post(urlSignUp,
                                    {"email": self.email_admin, "username": self.username_admin,
                                     "dni": self.dni_admin, "nom": self.nom_admin, "cognoms": self.cognoms_admin,
                                     "password": self.password, "password2": self.password})
        self.assertEquals(response.status_code, status.HTTP_201_CREATED)

        # Login Admin
        urlLogIn = reverse('login_admins-list')
        response2 = self.client.post(urlLogIn,
                                     {"username": self.username_admin, "password": self.password})
        self.assertEquals(response2.status_code, status.HTTP_201_CREATED)

        self.assertEquals(response.json()['token'], response2.json()['token'])
