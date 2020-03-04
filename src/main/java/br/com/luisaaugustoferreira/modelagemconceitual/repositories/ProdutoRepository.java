package br.com.luisaaugustoferreira.modelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luisaaugustoferreira.modelagemconceitual.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer>{

}
