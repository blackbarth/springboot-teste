package br.com.luisaaugustoferreira.modelagemconceitual.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luisaaugustoferreira.modelagemconceitual.domain.Categoria;
import br.com.luisaaugustoferreira.modelagemconceitual.repositories.CategoriaRepository;
import br.com.luisaaugustoferreira.modelagemconceitual.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
