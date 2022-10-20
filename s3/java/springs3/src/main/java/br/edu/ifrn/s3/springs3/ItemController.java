package br.edu.ifrn.s3.springs3;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController{
    @GetMapping("/itens")
    public List<Item> all(){
        
    }
}