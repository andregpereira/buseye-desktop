package model;

import java.sql.Time;

public class Linha {

	private int idLinha;
	private String nome;
	private Time duracaoPercurso;
	private int idFuncionario;

	public int getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(int idLinha) {
		this.idLinha = idLinha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Time getDuracaoPercurso() {
		return duracaoPercurso;
	}

	public void setDuracaoPercurso(Time tempo) {
		this.duracaoPercurso = tempo;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

}
