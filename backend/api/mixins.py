from rest_framework import filters, pagination, response
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


class PaginationClass(pagination.LimitOffsetPagination):
    max_limit = 1000  # default max limit

    def get_limit(self, request):
        if 'limit' in request.query_params:
            try:
                return int(request.query_params['limit'])
            except ValueError:
                pass
        return self.max_limit

    def get_paginated_response(self, data):
        return response.Response(data)
