package model;

public class Onibus {

	private int idOnibus;
	private String localizacao;
	private int idFuncionario;
	private int idLinha;
	private int capacidade;

	public int getIdOnibus() {
		return idOnibus;
	}

	public void setIdOnibus(int idOnibus) {
		this.idOnibus = idOnibus;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(int idLinha) {
		this.idLinha = idLinha;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

}
