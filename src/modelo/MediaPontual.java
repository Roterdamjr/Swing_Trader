package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class MediaPontual {

	private Date dataReferencia;
	private BigDecimal valor;
	Acao acao;
	int periodo;
	
	public MediaPontual(Date dataReferencia, BigDecimal valor) {
		super();
		this.dataReferencia = dataReferencia;
		this.valor = valor;
	}	
	
	public Date getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	


	

	
}

