package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class ValorData {

	private Date dataReferencia;
	private BigDecimal valor;
	
	public ValorData(Date dataReferencia, BigDecimal valor) {
		super();
		this.dataReferencia = dataReferencia;
		this.valor = valor;
	}	
	
	public int comparar(ValorData mp){
		return  valor.compareTo(mp.getValor());
				
	}
	
	public String toString(){
		return ("data: "+ getDataReferencia()+ "  valor:"+getValor());
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

