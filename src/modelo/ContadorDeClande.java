package modelo;

import java.util.Date;

public class ContadorDeClande {
	private int quantidade;
	private double valorExtremo;
	private Date data;
	
	
	public String toString(){
		return ("Candle: " +data + ", "+ valorExtremo+ ", "+quantidade);
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorExgtremo() {
		return valorExtremo;
	}
	public void setValorExgtremo(double valorExgtremo) {
		this.valorExtremo = valorExgtremo;
	}
	
	
}
