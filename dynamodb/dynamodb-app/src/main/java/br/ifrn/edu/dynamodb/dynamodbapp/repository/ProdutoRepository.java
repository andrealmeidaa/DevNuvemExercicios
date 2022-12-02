package br.ifrn.edu.dynamodb.dynamodbapp.repository;

import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.ifrn.edu.dynamodb.dynamodbapp.domain.Produto;
@EnableScan
@Repository
public interface ProdutoRepository extends CrudRepository<Produto,String> {

    Optional<Produto> findById(String id);
    
}
