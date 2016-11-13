import json

from yahoo_finance import Share

ETF_master_list = [
    'AADR',
    'AAXJ',
    'ACIM',
    'ACTX',
    'ACWF',
    'ACWI',
    'ACWV',
    'ACWX',
    'ADRA',
    'ADRD',
    'ADRE',
    'ADRU',
    'ADZ',
    'AFK',
    'AFTY',
    'AGA',
    'AGF',
    'AGG',
    'AGGY',
    'AGND',
    'AGQ',
    'AGZ',
    'AGZD',
    'AIA',
    'AIRR',
    'ALD',
    'ALFA',
    'ALFI',
    'ALTS',
    'ALTY',
    'AMJ',

]

etf_data = []

for index, ETF in enumerate(ETF_master_list):

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
        'dividend_yield': fund.get_dividend_yield() ,
        'absolute_change': fund.get_change(),
        'percentage_change': fund.get_percent_change(),
        # 'currency' = fund.get_,
        'pe': fund.get_price_earnings_ratio(),
        'pb': fund.get_price_book(),
        # 'peg': fund.get_price_earnings_growth(),
        'eps_current': fund.get_earnings_share(),
        # 'eps_next_quarter' = fund.get_,
        'average_daily_volume': fund.get_avg_daily_volume()
    }
    
    etf_dict['fields'] = fields
    etf_data.append(etf_dict)
json_data = json.dumps(etf_data)

print(json_data)