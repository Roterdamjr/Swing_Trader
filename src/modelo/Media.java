package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import utilitarios.Utilitario;
import dao.NegociacaoDao;

public class Media {
	private int periodo;
	private String tipo;
	private Date dataReferencia;
	private Acao acao;
	
	public Media(Acao acao,int periodo,  Date dataReferencia, String tipo) {
		super();
		this.periodo = periodo;
		this.tipo = tipo;
		this.dataReferencia = dataReferencia;
		this.acao = acao;
	}

	public static void main(String[] args) {
		try {			 
			List<Negociacao> negociacoesDaAcao = new NegociacaoDao().buscaNegociacaoPorAcao(new Acao("PETR4"));
			
			Media media=new Media(
					new Acao("PETR4"),
					17,
					Utilitario.converteStringParaDate("09/11/2018"),
					null
					);
			
			System.out.println(media.buscaValor());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BigDecimal buscaValor(){
		/*
		 Calcula a média de determinado tipo (arimtmetica ou exponencial 
		em determinada data. Esse parâmtetros são passado no construtor
		*/
			
		List<Negociacao> negociacoesDaAcao=null;
		try {
			negociacoesDaAcao = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		}

		ordenarPorDataDecrescente(negociacoesDaAcao);
		ArrayList<Integer>  lista=new ArrayList<Integer>();
		
		BigDecimal soma=new BigDecimal("0.00");
		int cont=0;
		boolean achou=false;
		
		for(Negociacao negociacao:negociacoesDaAcao){			
			String data1=Utilitario.converteDateParaString(negociacao.getData());			
			String data2=Utilitario.converteDateParaString(dataReferencia);
			
			if(!achou && data1.equals(data2)){				
				achou=true;
			}
			
			if(achou){
				//System.out.println(negociacao.getData()+ " - "+ negociacao.getPrecoUltimo());
				soma=soma.add(negociacao.getPrecoUltimo());
				cont++;
				if(cont==periodo){
					break;
				}
			}
			
		}

		return soma.divide(new BigDecimal(periodo),BigDecimal.ROUND_UP);
	}
	
	public boolean isCrescente(){

		BigDecimal valor1=new Media(
				acao,periodo,Negociacao.buscaDataAnteriorNegociacao(dataReferencia,4),null
				).buscaValor();
		
		BigDecimal valor2=new Media(
				acao,periodo,Negociacao.buscaDataAnteriorNegociacao(dataReferencia,2),null
				).buscaValor();
				
		BigDecimal valor3=buscaValor();

		if(valor1.compareTo(valor2) < 0 && valor2.compareTo(valor3) < 0)
			return true;
		else
			return false;
	}
	
	
	
	public void ordenarPorDataCrescente(List<Negociacao> negociacoesDaAcao){
		negociacoesDaAcao.sort(new Comparator<Negociacao>() {
		    @Override
		    public int compare(Negociacao o1, Negociacao o2) {
		        return o1.getData().compareTo(o2.getData());
		    }
		});
	}
	
	public void ordenarPorDataDecrescente(List<Negociacao> negociacoesDaAcao){
		negociacoesDaAcao.sort(new Comparator<Negociacao>() {
		    @Override
		    public int compare(Negociacao o1, Negociacao o2) {
		        return -o1.getData().compareTo(o2.getData());
		    }
		});
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	
	
}
