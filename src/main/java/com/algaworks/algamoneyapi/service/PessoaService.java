package com.algaworks.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoaAtualizada) {
		Pessoa pessoaAtual = BuscarPessoaPeloCodigo(codigo);
		
		BeanUtils.copyProperties(pessoaAtualizada, pessoaAtual, "codigo");
		return pessoaRepository.save(pessoaAtual);
	}

	public void atualizaStatusAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaAtual = BuscarPessoaPeloCodigo(codigo);
		pessoaAtual.setAtivo(ativo);
		pessoaRepository.save(pessoaAtual);
	}
	
	
	private Pessoa BuscarPessoaPeloCodigo(Long codigo) {
		Pessoa pessoaAtual = pessoaRepository.findOne(codigo);
		
		if(pessoaAtual == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaAtual;
	}
	
}
