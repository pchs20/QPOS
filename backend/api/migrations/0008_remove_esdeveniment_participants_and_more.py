# Generated by Django 4.2.6 on 2023-11-13 10:23

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0007_esdeveniment'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='esdeveniment',
            name='participants',
        ),
        migrations.CreateModel(
            name='AssistenciaAEsdeveniment',
            fields=[
                ('id', models.AutoField(primary_key=True, serialize=False, verbose_name='Identificador')),
                ('dataRegistre', models.DateTimeField(auto_now_add=True, verbose_name='Data registre')),
                ('client', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='assistencies', to='api.client', verbose_name='Client')),
                ('esdeveniment', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='assistencies', to='api.esdeveniment', verbose_name='Esdeveniment')),
            ],
            options={
                'unique_together': {('client', 'esdeveniment')},
            },
        ),
    ]
