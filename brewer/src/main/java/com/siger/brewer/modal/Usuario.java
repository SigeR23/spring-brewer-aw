package com.siger.brewer.modal;

import java.time.LocalDate;

import org.hibernate.validator.constraints.NotBlank;

public class Usuario {
	
	@NotBlank(message = "nome é obrigatorio")
	private String nome;
	@NotBlank(message = "email é obrigatorio")
	private String email;
	@NotBlank(message = "senha é obrigatorio")
	private String senha;
	@NotBlank(message = "data de nascimento é obrigatorio")
	private LocalDate dataNascimento;
	private boolean ativo;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
}
