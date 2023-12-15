from rest_framework import routers
from django.urls import path, include
from . import views

router = routers.DefaultRouter()
router.register(r'proveidors', views.ProveidorsView, basename='proveidors')
router.register(r'productes', views.ProductesView, basename='productes')
router.register(r'clients', views.ClientsView, basename='clients')
router.register(r'treballadors', views.TreballadorsView, basename='treballadors')
router.register(r'admins', views.AdminsView, basename='admins')
router.register(r'compres', views.CompresView, basename='compres')
router.register(r'esdeveniments', views.EsdevenimentsView, basename='esdeveniments')
router.register(r'assistencies', views.AssistenciaAEsdevenimentView, basename='assistencies')
router.register(r'cupons', views.CuponsView, basename='cupons')

router.register(r'login/clients', views.LoginClientView, 'login_clients')
router.register(r'login/treballadors', views.LoginTreballadorView, 'login_treballadors')
router.register(r'login/admins', views.LoginAdminView, 'login_admins')
router.register(r'signup/clients', views.SignUpClientView, 'signup_clients')
router.register(r'signup/treballadors', views.SignUpTreballadorView, 'signup_treballadors')
router.register(r'signup/admins', views.SignUpAdminView, 'signup_admins')

urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls', namespace='rest_framework')),
]
