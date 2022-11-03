package br.edu.ifrn.s3.springs3;


import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Controller
public class ItemController{
    @GetMapping("/itens")
    public String itens(Model model){
        model.addAttribute("inventario", getItens());
        return "inventario";
    }

    private List<Item> getItens(){
       List<Item> lista=null;
        try {
            final AmazonS3 s3=AmazonS3ClientBuilder.standard().withRegion("us-east-1").build();//Cria o cliente para o serviço
            S3Object objeto=s3.getObject("aws-lab-tsi", "inventario.json"); //Recupera o objeto
            Gson gson=new Gson(); //Utilitário para permitir operações JSON
    
            TypeToken<List<Item>> listType=new TypeToken<List<Item>>(){};//Cria tipo para simplificar a conversão
            String inventarioJson="";
            //Lê o conteúdo do arquivo e carrega em uma String JSON
            try(S3ObjectInputStream s3is=objeto.getObjectContent()){
                inventarioJson=StreamUtils.copyToString(s3is, StandardCharsets.UTF_8);
            }catch (Exception e){
                e.printStackTrace();
            }
            
            //Converte o JsOn para List de Itens
            lista=gson.fromJson(inventarioJson, listType);
        }catch(AmazonServiceException e){
            e.printStackTrace();
        }
        return lista;

        
    }

}