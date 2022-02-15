package com.jose.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.os.domain.Cliente;
import com.jose.os.domain.OS;
import com.jose.os.domain.Tecnico;
import com.jose.os.domain.enuns.Prioridade;
import com.jose.os.domain.enuns.Status;
import com.jose.os.repository.ClienteRepository;
import com.jose.os.repository.OsRepository;
import com.jose.os.repository.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OsRepository ordemServicoRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "José Filho", "025.740.400-74", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Linus Torvalds", "599.824.640-35", "(88) 94545-4545");
		Cliente c1 = new Cliente(null, "Betina Campos", "600.322.700-11", "(88) 98888-7777");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OD", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		t1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));

	}

}
