from rest_framework import routers
from django.urls import path, include
from . import views

router = routers.DefaultRouter()
router.register(r'proveidors', views.ProveidorsView, basename='proveidors')
router.register(r'productes', views.ProductesView, basename='productes')
router.register(r'clients', views.ClientsView, basename='clients')
router.register(r'treballadors', views.TreballadorsView, basename='treballadors')
router.register(r'admins', views.AdminsView, basename='admins')
router.register(r'login/clients', views.LoginClientView, 'log in clients')
router.register(r'login/treballadors', views.LoginTreballadorView, 'log in treballadors')
router.register(r'login/admins', views.LoginAdminView, 'log in admins')
router.register(r'signup/clients', views.SignUpClientView, 'sign up clients')
router.register(r'signup/treballadors', views.SignUpTreballadorView, 'sign up treballadors')

urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls', namespace='rest_framework')),
]
