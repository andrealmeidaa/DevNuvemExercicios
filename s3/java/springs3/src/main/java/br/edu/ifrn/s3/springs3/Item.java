package br.edu.ifrn.s3.springs3;

public class Item {

    private final long id;
    private final String item;
    private final String url;

    public Item(long id,String item,String url){
        this.id=id;
        this.item=item;
        this.url=url;
    }

    public long getId(){
        return this.id;
    }

    public String getItem(){
        return this.item;
    }

    public String getUrl(){
        return Utils.getPresignedUrl("aws-lab-tsi", "assets/0"+this.id+".jpg");
    }

    



    
}
