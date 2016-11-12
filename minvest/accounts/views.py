from django.http import HttpResponse
import requests

# Create your views here.
def index(request):
    r = requests.get('http://api.reimaginebanking.com/accounts?key=2cfd9e99a29ba1479a654765e2c2e521')
    return HttpResponse(r.text)