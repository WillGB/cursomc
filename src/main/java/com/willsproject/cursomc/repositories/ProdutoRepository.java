package com.willsproject.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willsproject.cursomc.domain.Produto;

//buscar, alterar, deletar = essa é a função do Repository salvar os dados no banco de dados
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	
	
}
