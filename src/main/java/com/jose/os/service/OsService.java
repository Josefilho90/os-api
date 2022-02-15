package com.jose.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jose.os.domain.Cliente;
import com.jose.os.domain.OS;
import com.jose.os.domain.Tecnico;
import com.jose.os.domain.enuns.Prioridade;
import com.jose.os.domain.enuns.Status;
import com.jose.os.dto.OSDTO;
import com.jose.os.repository.OsRepository;
import com.jose.os.service.exceptions.ObjectNotFoundException;

@Service
public class OsService {

	@Autowired
	private OsRepository ordemServicoRepository;

	@Autowired
	private TecnicoService tecnicoService;

	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {
		Optional<OS> obj = ordemServicoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado!  Id:  " + id + ", Tipo: " + OS.class.getName()));
	}

	public List<OS> findAll() {
		return ordemServicoRepository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}

	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	public void delete(@Valid Integer id) {
		OS obj = findById(id);
		ordemServicoRepository.delete(obj);
		
	}

	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));
		newObj.setStatus(Status.toEnum(obj.getStatus()));

		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());

		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		if(newObj.getStatus().getCod().equals(2)) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
		
		return ordemServicoRepository.save(newObj);
	}

}
