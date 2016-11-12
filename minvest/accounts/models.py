from __future__ import unicode_literals

from django.db import models
from django.contrib.auth.models import AbstractUser

class CustomUser(AbstractUser):
    customer_id = models.TextField(max_length=30, blank=True, default='')
    account_id = models.TextField(max_length=30, blank=True, default='')