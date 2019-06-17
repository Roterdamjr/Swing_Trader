package service;

import java.util.Date;

public class Celula {
	Date data;
	boolean valor;
	
	public Celula(Date data, boolean valor) {
		super();
		this.data = data;
		this.valor = valor;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public boolean isValor() {
		return valor;
	}
	public void setValor(boolean valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "Celula [data=" + data + ", valor=" + valor + "]";
	}
	
	
}
