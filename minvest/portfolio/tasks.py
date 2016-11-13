import json

from yahoo_finance import Share
from django.conf import settings


def get_ETF_data():
    etf_data = []
    for index, ETF in enumerate(settings.ETF_MASTER_LIST):
        etf_dict = {
            'model': 'portfolio.ETF',
            'pk': index + 1,
            'fields': {},
        }

        fund = Share(ETF)

        fields = {
            'name': fund.get_name(),
            'symbol': ETF,
            'last_trade': fund.get_price(),
            'dividend_yield': fund.get_dividend_yield(),
            'absolute_change': fund.get_change(),
            'percentage_change': fund.get_percent_change(),
            'average_daily_volume': fund.get_avg_daily_volume()
        }

        etf_dict['fields'] = fields
        etf_data.append(etf_dict)
    return etf_data