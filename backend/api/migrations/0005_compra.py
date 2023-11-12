# Generated by Django 4.2.6 on 2023-11-12 12:24

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0004_usuari_admin_client_treballador'),
    ]

    operations = [
        migrations.CreateModel(
            name='Compra',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False, verbose_name='Identificador')),
                ('data', models.DateTimeField(auto_now_add=True, verbose_name='Data compra')),
                ('importFinal', models.FloatField(verbose_name='Import final')),
                ('client', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, related_name='compres', to='api.client', verbose_name='Client')),
                ('treballador', models.ForeignKey(on_delete=django.db.models.deletion.DO_NOTHING, related_name='compres', to='api.treballador', verbose_name='Treballador')),
            ],
        ),
    ]
