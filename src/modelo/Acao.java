package modelo;

public class Acao {
	
	private String codigoNegociacao;
	private String nomeResumidoEmpresa;
	
	public Acao(String codigoNegociacao) {
		super();
		this.codigoNegociacao = codigoNegociacao;
	}	
	
	public Acao(String codigoNegociacao, String nomeResumidoEmpresa) {
		super();
		this.codigoNegociacao = codigoNegociacao;
		this.nomeResumidoEmpresa = nomeResumidoEmpresa;
	}
	
	
	public String getCodigoNegociacao() {
		return codigoNegociacao;
	}
	public void setCodigoNegociacao(String codigoNegociacao) {
		this.codigoNegociacao = codigoNegociacao;
	}
	public String getNomeResumidoEmpresa() {
		return nomeResumidoEmpresa;
	}
	public void setNomeResumidoEmpresa(String nomeResumidoEmpresa) {
		this.nomeResumidoEmpresa = nomeResumidoEmpresa;
	}

	
}
