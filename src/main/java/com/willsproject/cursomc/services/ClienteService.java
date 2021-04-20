package com.willsproject.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willsproject.cursomc.domain.Cliente;
import com.willsproject.cursomc.repositories.ClienteRepository;

import javassist.tools.rmi.ObjectNotFoundException;

//classe reponsável em fazer consulta no repositório
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente buscar(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
