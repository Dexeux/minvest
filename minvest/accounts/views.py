from django.http import HttpResponse
from django.contrib.auth import get_user_model
from django.contrib.auth import authenticate
from rest_framework import status
from rest_framework.decorators import api_view, authentication_classes, permission_classes
from rest_framework.response import Response
from rest_framework.authtoken.models import Token
from rest_framework.authentication import TokenAuthentication
from rest_framework.permissions import IsAuthenticated
import requests
from django.conf import settings

import json

User = get_user_model()

@api_view(['POST'])
def create_user(request):
    """
    Retrieve, update or delete a snippet instance.
    """
    if request.method == 'POST':
        data = json.loads(request.body)
        if data.get('username') and data.get('password'):
            headers = {'Content-Type': 'application/json'}
            try:
                user = User.objects.get_by_natural_key(data['username'])
            except User.DoesNotExist:
                user = User.objects.create_user(username=data['username'],
                                                password=data['password'])
                create_customer_data = {
                    'first_name': 'Josh',
                    'last_name': 'Kwok',
                    'address': {
                        'street_number': '231',
                        'street_name': 'Pinewood',
                        'city': 'Thornhill',
                        'state': 'NJ',
                        'zip': '11144'
                    }
                }
                r = requests.post('http://api.reimaginebanking.com/customers?key={0}'.format(settings.NESSIE_API_KEY),
                                  headers=headers,
                                  data=json.dumps(create_customer_data))
                customer_data = json.loads(r.text)
                customer_id = customer_data.get('objectCreated', {}).get('_id')
                nessie_data = {
                    'type': 'Checking',
                    'nickname': 'joshkwok',
                    'rewards': 0,
                    'balance': 1000,
                }
                r = requests.post('http://api.reimaginebanking.com/customers/{0}/accounts?key={1}'.format(customer_id,
                                                                                                          settings.NESSIE_API_KEY),
                                  headers=headers,
                                  data=json.dumps(nessie_data))
                account_data = json.loads(r.text)
                account_id = account_data.get('objectCreated', {}).get('_id')
                user.customer_id = customer_id
                user.account_id = account_id
                user.save()
                response_data = {
                    'message': 'Success',
                }
                response_status = status.HTTP_201_CREATED
            else:
                response_data = {
                    'message': 'Username {0} is taken.'.format(data['username'])
                }
                response_status = status.HTTP_409_CONFLICT
            return Response(data=response_data, status=response_status)
        return Response(data={'message': 'error'}, status=status.HTTP_404_NOT_FOUND)
    else:
        return Response(data={'message': 'error'}, status=status.HTTP_404_NOT_FOUND)


@api_view(['POST'])
def login_user(request):
    if request.method == 'POST':
        data = json.loads(request.body)
        user = authenticate(username=data.get('username'), password=data.get('password'))
        if user is not None:
            token, created = Token.objects.get_or_create(user=user)
            if not created:
                token.delete()
                token = Token.objects.create(user=user)
            response_data = {
                'message': 'Success',
                'token': token.key,
            }
            return Response(data=response_data, status=status.HTTP_202_ACCEPTED)
        return Response(data={'message': 'error'}, status=status.HTTP_404_NOT_FOUND)
    else:
        return Response(data={'message': 'error'}, status=status.HTTP_404_NOT_FOUND)


@api_view(['GET'])
@authentication_classes ((TokenAuthentication,))
@permission_classes ((IsAuthenticated,))
def get_consumer_id(request):
    return Response(data={'foo':request.user.username}, status=status.HTTP_200_OK)