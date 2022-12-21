from botocore.exceptions import ClientError

class Products:
    def __init__(self,dynamoresource):
        self.dynamoresource=dynamoresource
        self.table=self.dynamoresource.Table('Produto')
        self.table.load()
    def list_products(self):
        products=[]
        try:
            response=self.table.scan()
            products.extend(response.get('Items',[]))
        except ClientError as erro:
            print(erro)
        return products
    def insert_product(self,descricao,valor):
        pass
        