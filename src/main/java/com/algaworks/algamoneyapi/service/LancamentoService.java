package com.algaworks.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.model.Lancamento;
import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.LancamentoRepository;
import com.algaworks.algamoneyapi.repository.PessoaRepository;
import com.algaworks.algamoneyapi.service.exeption.PessoaInativaOuInexistenteException;

@Service
public class LancamentoService {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		if(pessoa.isInativo() || pessoa == null) {
			throw new PessoaInativaOuInexistenteException();
		}
		
		return lancamentoRepository.save(lancamento);
		
	}
	
	public Lancamento atualizar(Lancamento lancamentoAtualizado, Long codigo) {
		Lancamento lancamentoAtual = buscarLancamentoPeloCodigo(codigo);
		BeanUtils.copyProperties(lancamentoAtualizado, lancamentoAtual);
		return lancamentoRepository.save(lancamentoAtual);
		
	}
	public Lancamento buscarLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoAtual = lancamentoRepository.findOne(codigo);
		if(lancamentoAtual == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return lancamentoAtual;
		
	}
	
	
}
