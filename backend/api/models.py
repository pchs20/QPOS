from django.utils.translation import gettext_lazy as _
from django.db import models
from django.contrib.auth.models import User


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


class Usuari(models.Model):
    # Del user farem servir:
    # 1. First name
    # 2. Last name
    # 3. Email address
    # 4. Password
    # PD: Definirem el username com: [First name]_[Last name] (i qualsevol espai que hi hagi també substituït per '_')
    user = models.OneToOneField(User, primary_key=True, on_delete=models.CASCADE, verbose_name=_('User'))
    # La resta de camps els definim aquí
    dni = models.CharField(max_length=20, null=True, blank=True, verbose_name=_('DNI'))
    bio = models.CharField(max_length=250, null=True, blank=True, verbose_name=_('Bio'))
    dataNaixement = models.DateField(null=True, blank=True, verbose_name=_('Data naixement'))
    telefon = models.IntegerField(null=True, blank=True, verbose_name=_('Telefon'))
    imatge = models.ImageField(null=True, blank=True, verbose_name=_('Imatge'))


class Client(models.Model):
    usuari = models.OneToOneField(Usuari, primary_key=True, on_delete=models.CASCADE, verbose_name=_('Usuari'))


class Treballador(models.Model):
    usuari = models.OneToOneField(Usuari, primary_key=True, on_delete=models.CASCADE, verbose_name=_('Usuari'))


class Admin(models.Model):
    usuari = models.OneToOneField(Usuari, primary_key=True, on_delete=models.CASCADE, verbose_name=_('Usuari'))


class Compra(models.Model):
    id = models.AutoField(primary_key=True, verbose_name=_('Identificador'))
    data = models.DateTimeField(auto_now_add=True, verbose_name=_('Data compra'))
    importFinal = models.FloatField(null=False, blank=False, verbose_name=_('Import final'))
    client = models.ForeignKey(Client, related_name='compres', null=False, on_delete=models.DO_NOTHING, verbose_name=_('Client'))
    treballador = models.ForeignKey(Treballador, related_name='compres', null=False, on_delete=models.DO_NOTHING, verbose_name=_('Treballador'))

    class Meta:
        verbose_name_plural = _('Compres')


class LiniaCompra(models.Model):
    id = models.AutoField(primary_key=True, verbose_name=_('Identificador'))
    quantitat = models.IntegerField(default=1, null=False, blank=False, verbose_name=_('Quantitat'))
    producte = models.ForeignKey(Producte, related_name='liniesCompra', null=False, on_delete=models.DO_NOTHING, verbose_name=_('Producte'))
    compra = models.ForeignKey(Compra, related_name='liniesCompra', null=False, on_delete=models.CASCADE, verbose_name=_('Compra'))

    class Meta:
        verbose_name_plural = _('Línies Compra')


class Esdeveniment(models.Model):
    id = models.AutoField(primary_key=True, verbose_name=_('Identificador'))
    nom = models.CharField(max_length=100, null=False, blank=False, verbose_name=_('Nom'))
    descripcio = models.CharField(max_length=1000, null=True, blank=True, verbose_name=_('Descripció'))
    dataCreacio = models.DateTimeField(auto_now_add=True, verbose_name=_('Data creació'))
    data = models.DateTimeField(null=True, blank=True, verbose_name=_('Data'))
    aforament = models.IntegerField(default=0, null=True, blank=True, verbose_name=_('Aforament'))
    durada = models.CharField(max_length=100, null=True, blank=True, verbose_name=_('Durada'))
    ubicacio = models.CharField(max_length=100, null=True, blank=True, verbose_name=_('Ubicació'))
    creador = models.ForeignKey(Admin, related_name='esdeveniments', null=False, on_delete=models.DO_NOTHING, verbose_name=_('Creador'))
    participants = models.ManyToManyField(Client, related_name='esdeveniments',  verbose_name=_('Participants'))
