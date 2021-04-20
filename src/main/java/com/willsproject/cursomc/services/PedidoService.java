package com.willsproject.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willsproject.cursomc.domain.Pedido;
import com.willsproject.cursomc.repositories.PedidoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

//classe reponsável em fazer consulta no repositório
@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}
