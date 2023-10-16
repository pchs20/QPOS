# Generated by Django 4.2.6 on 2023-10-16 10:30

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Producte',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False, verbose_name='Identificador')),
                ('nom', models.CharField(max_length=100, verbose_name='Nom')),
                ('descripcio', models.CharField(blank=True, max_length=1000, null=True, verbose_name='Descripció')),
                ('preu', models.FloatField(blank=True, null=True, verbose_name='Preu')),
                ('codiBarres', models.CharField(blank=True, max_length=50, null=True, verbose_name='Codi de barres')),
                ('estoc', models.IntegerField(default=0, verbose_name='Estoc')),
                ('imatge', models.ImageField(blank=True, null=True, upload_to='', verbose_name='Imatge')),
            ],
        ),
    ]