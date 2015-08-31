package main.java.br.unip.seadc.beans;

public class Diagnostico {
	
	private String nomeDoenca;
	private String recomendacao;
	
	public Diagnostico(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}
	
	public Diagnostico(String nomeDoenca, String recomendacao) {
		this.nomeDoenca = nomeDoenca;
		this.recomendacao = recomendacao;
	}
	
	public String getNomeDoenca() {
		return nomeDoenca;
	}

	public void setNomeDoenca(String nomeDoenca) {
		this.nomeDoenca = nomeDoenca;
	}

	public String getRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(String recomendacao) {
		this.recomendacao = recomendacao;
	}

	public String toString(){
		return "Nome da doença: " + this.getNomeDoenca() + 
				"\n" + 
				"Recomendação: " + this.getRecomendacao();
	}
}