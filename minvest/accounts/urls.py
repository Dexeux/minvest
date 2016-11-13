from django.conf.urls import url, include
from . import views

urlpatterns = [
    url(r'^register', views.create_user),
    url(r'^login', views.login_user),
    url(r'^user', views.get_consumer_id)
]