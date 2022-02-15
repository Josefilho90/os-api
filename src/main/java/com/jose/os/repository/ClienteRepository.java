package com.jose.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jose.os.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	@Query("SELECT obj FROM Cliente obj WHERE obj.cpf =:cpf")
	Cliente findByCPF(@Param("cpf") String cpf);

}
