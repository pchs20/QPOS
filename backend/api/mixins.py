from rest_framework import filters
from datetime import datetime


class FilterBackend(filters.BaseFilterBackend):
    def filter_queryset(self, request, queryset, view):
        data = request.query_params.get('data')
        dataRang = request.query_params.get('data__range')
        if data:
            dataFormatted = datetime.strptime(data, '%d/%m/%y')
            queryset = queryset.filter(data__date=dataFormatted)
        elif dataRang:
            start_date_str, end_date_str = dataRang.split(',')
            start_date = datetime.strptime(start_date_str, '%d/%m/%y')
            end_date = datetime.strptime(end_date_str, '%d/%m/%y')

            queryset = queryset.filter(data__date__range=(start_date, end_date))
        return queryset
