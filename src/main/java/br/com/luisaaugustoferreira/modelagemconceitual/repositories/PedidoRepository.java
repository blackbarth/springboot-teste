package br.com.luisaaugustoferreira.modelagemconceitual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luisaaugustoferreira.modelagemconceitual.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer>{

}
