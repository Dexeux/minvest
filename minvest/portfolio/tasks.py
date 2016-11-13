import json

from yahoo_finance import Share
from django.conf import settings


def set_ETF_data():
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
            'year high': fund.get_year_high(),
            'year low': fund.get_year_low(),
            '50 day moving average': fund.get_50day_moving_avg(),
            '200 day moving average': fund.get_200day_moving_avg(),
            'average_daily_volume': fund.get_avg_daily_volume()
        }

        etf_dict['fields'] = fields
        etf_data.append(etf_dict)
    json_data = json.dumps(etf_data)

    # print(json_data)

    output_dict = [y for y in etf_data if y['fields']['dividend_yield'] > 1]

    output_dict = [x for x in output_dict if x['fields']['average_daily_volume'] > 100000]

    output_dict = [z for z in output_dict if z['fields']['200 day moving average'] < z['fields']['last_trade']]

    sorted_list = sorted(output_dict, key=lambda k: k['fields']['dividend_yield'], reverse=True)

    for etf in sorted_list[:3]:
        pass
