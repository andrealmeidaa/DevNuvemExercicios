from flask import Flask, render_template
import boto3
from botocore.exceptions import ClientError
import json
app=Flask(__name__)
s3=boto3.resource('s3')

def url_imagem(nome_arquivo):
    s3_client=boto3.client('s3')
    try:
        url=s3_client.generate_presigned_url('get_object',Params={'Bucket': 'aws-lab-tsi','Key': nome_arquivo},ExpiresIn=1800)
        return url
    except ClientError as e:
        return None
                                            

@app.route("/")
def index():
    objeto=s3.Object(bucket_name='aws-lab-tsi',key='inventario.json')
    resposta=objeto.get()
    inventario=json.loads(resposta['Body'].read())
    print(inventario)
    for item in inventario:
        item['url']=url_imagem('assets/0{id}.jpg'.format(id=item['id']))
    return render_template('inventario.html',inventario=inventario)





