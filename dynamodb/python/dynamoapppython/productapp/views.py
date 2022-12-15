from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader,Context
from .controllers import Products
import boto3

# Create your views here.
dynamodb=boto3.resource('dynamodb')
gerenciadorProdutos=Products(dynamodb)
def index(request):
    template=loader.get_template("index.html")
    c={'produtos':gerenciadorProdutos.list_products()}
    return HttpResponse(template.render(c))
