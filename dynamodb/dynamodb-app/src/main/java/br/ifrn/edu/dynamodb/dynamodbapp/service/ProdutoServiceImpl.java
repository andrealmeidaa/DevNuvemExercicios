package br.ifrn.edu.dynamodb.dynamodbapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ifrn.edu.dynamodb.dynamodbapp.domain.Produto;
import br.ifrn.edu.dynamodb.dynamodbapp.repository.ProdutoRepository;
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repositorio;

    public ProdutoServiceImpl(ProdutoRepository repositorio){
        this.repositorio=repositorio;
    }

    @Override
    public Produto create(Produto produto) {
       return repositorio.save(produto);
    }

    @Override
    public Produto update(Produto produto, String id) {
        Optional<Produto> produtoOld=repositorio.findById(id);
        if(produtoOld.isPresent()){
            produtoOld.get().setDescricao(produto.getDescricao());
            produtoOld.get().setValor(produto.getValor());
            return repositorio.save(produtoOld.get());
        }
        return null;
    }

    @Override
    public Optional<Produto> getById(String id) {
        
        return repositorio.findById(id);
    }

    @Override
    public List<Produto> getAll() {
        
        return (List<Produto>)repositorio.findAll();
    }

    @Override
    public void delete(String id) {
        repositorio.deleteById(id);
        
    }
    
}
