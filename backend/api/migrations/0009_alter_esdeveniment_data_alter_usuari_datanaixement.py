# Generated by Django 4.2.6 on 2023-11-28 10:03

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0008_remove_esdeveniment_participants_and_more'),
    ]

    operations = [
        migrations.AlterField(
            model_name='esdeveniment',
            name='data',
            field=models.CharField(blank=True, max_length=50, null=True, verbose_name='Data'),
        ),
        migrations.AlterField(
            model_name='usuari',
            name='dataNaixement',
            field=models.CharField(blank=True, max_length=50, null=True, verbose_name='Data naixement'),
        ),
    ]