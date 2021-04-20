package com.willsproject.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willsproject.cursomc.domain.Endereco;

//buscar, alterar, deletar = essa é a função do Repository
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	
	
	
}
