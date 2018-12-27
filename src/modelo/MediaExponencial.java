package modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class MediaExponencial {

	private Date dataReferencia;
	private BigDecimal valor;
	
	public MediaExponencial(Date dataReferencia, BigDecimal valor) {
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

	


	

	
}

