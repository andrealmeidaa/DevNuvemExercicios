package br.ifrn.edu.dynamodb.dynamodbapp.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ifrn.edu.dynamodb.dynamodbapp.domain.Produto;
import br.ifrn.edu.dynamodb.dynamodbapp.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    public  ProdutoController(ProdutoService produtoService){
        this.produtoService=produtoService;
    }
  
    @GetMapping
    public String viewHome(Model model){
        model.addAttribute("produtos", produtoService.getAll());
        return "index";
    }
    @GetMapping("/novoproduto")
    public String addNemProduto(Model model){
        Produto produto=new Produto();
        model.addAttribute("produto",produto);
        return "novoproduto";
    }

    @PostMapping("/salvarproduto")
    public String salvarProduto(@ModelAttribute("produto") Produto produto){

        if (produto.getId()!=null)
            produtoService.update(produto,produto.getId());
        else
            produtoService.create(produto);
        return "redirect:/produto";
    }

    @GetMapping("/atualizarproduto/{id}")
    public String atualizarProduto(@PathVariable(value="id") String id, Model model){
        Produto produto=produtoService.getById(id).get();
        model.addAttribute("produto", produto);
        return "atualizar_produto";
    }

}
