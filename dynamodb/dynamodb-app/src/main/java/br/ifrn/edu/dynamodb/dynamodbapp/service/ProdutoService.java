package br.ifrn.edu.dynamodb.dynamodbapp.service;

import java.util.List;
import java.util.Optional;

import br.ifrn.edu.dynamodb.dynamodbapp.domain.Produto;

public interface ProdutoService {
    Produto create(Produto produto);
    Produto update(Produto produto, String id);
    Optional<Produto> getById(String id);
    List<Produto> getAll();
    void delete(String id);
}
