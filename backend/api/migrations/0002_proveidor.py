# Generated by Django 4.2.6 on 2023-10-16 10:34

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Proveidor',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False, verbose_name='Identificador')),
                ('nom', models.CharField(max_length=100, verbose_name='Nom')),
                ('descripcio', models.CharField(blank=True, max_length=1000, null=True, verbose_name='Descripció')),
            ],
        ),
    ]
