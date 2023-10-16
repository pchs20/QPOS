from django.utils.translation import gettext_lazy as _
from django.db import models


class Proveidor(models.Model):
    id = models.AutoField(primary_key=True, verbose_name=_('Identificador'))
    nom = models.CharField(max_length=100, null=False, blank=False, verbose_name=_('Nom'))
    descripcio = models.CharField(max_length=1000, null=True, blank=True, verbose_name=_('Descripció'))


class Producte(models.Model):
    id = models.AutoField(primary_key=True, verbose_name=_('Identificador'))
    nom = models.CharField(max_length=100, null=False, blank=False, verbose_name=_('Nom'))
    descripcio = models.CharField(max_length=1000, null=True, blank=True, verbose_name=_('Descripció'))
    preu = models.FloatField(null=True, blank=True, verbose_name=_('Preu'))
    proveidor = models.ForeignKey(Proveidor, related_name='productes', null=False, on_delete=models.CASCADE, verbose_name=_('Proveidor'))
    codiBarres = models.CharField(max_length=50, null=True, blank=True, verbose_name=_('Codi de barres'))
    estoc = models.IntegerField(default=0, verbose_name=_('Estoc'))
    imatge = models.ImageField(null=True, blank=True, verbose_name=_('Imatge'))
