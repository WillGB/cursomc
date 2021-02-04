package com.willsproject.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willsproject.cursomc.domain.Categoria;
import com.willsproject.cursomc.repositories.CategoriaRepository;

import javassist.tools.rmi.ObjectNotFoundException;

//classe reponsável em fazer consulta no repositório
@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
