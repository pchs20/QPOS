# Generated by Django 4.2.6 on 2023-12-03 11:27

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0010_client_punts'),
    ]

    operations = [
        migrations.AddField(
            model_name='compra',
            name='descompte',
            field=models.FloatField(blank=True, null=True, verbose_name='Descompte'),
        ),
        migrations.AddField(
            model_name='compra',
            name='dinersCanvi',
            field=models.FloatField(blank=True, null=True, verbose_name='Diners canvi'),
        ),
        migrations.AddField(
            model_name='compra',
            name='dinersEntregats',
            field=models.FloatField(blank=True, null=True, verbose_name='Diners entregats'),
        ),
        migrations.AddField(
            model_name='compra',
            name='metodePagament',
            field=models.CharField(blank=True, choices=[('Targeta', 'Targeta'), ('Efectiu', 'Efectiu')], max_length=8, null=True, verbose_name='Mètode pagament'),
        ),
    ]
