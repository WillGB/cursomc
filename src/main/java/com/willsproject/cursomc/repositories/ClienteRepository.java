package com.willsproject.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willsproject.cursomc.domain.Cliente;

//buscar, alterar, deletar = essa é a função do Repository
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
	
}
