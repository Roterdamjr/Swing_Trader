package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class ValorData {

	private Date dataReferencia;
	private double valor;
	
	public ValorData(Date dataReferencia, double valor) {
		super();
		this.dataReferencia = dataReferencia;
		this.valor = valor;
	}	
	
	public int comparar(ValorData mp){
		int ret=0;
		
		if(valor>mp.getValor())
			ret= 1;
		if(valor==mp.getValor())
			ret= 0;
		if(valor<mp.getValor())
			ret= -1;
		
		return ret;
				
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
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}


	
}

