from django.conf.urls import url, include
from . import views

urlpatterns = [
    url(r'^api/v1/register', views.create_user),
    url(r'^api/v1/login', views.login_user),
    url(r'^api/v1/session', views.test_session),
]