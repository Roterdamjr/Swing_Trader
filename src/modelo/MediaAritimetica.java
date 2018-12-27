package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import utilitarios.Utilitario;
import dao.NegociacaoDao;

public class MediaAritimetica {
	private int periodo;	
	private Date dataReferencia;
	private Acao acao;
	
	public MediaAritimetica(Acao acao,int periodo,  Date dataReferencia) {
		super();
		this.periodo = periodo;

		this.dataReferencia = dataReferencia;
		this.acao = acao;
	}

	public static void main(String[] args) {
		try {			 
			List<Negociacao> negociacoesDaAcao = new NegociacaoDao().buscaNegociacaoPorAcao(new Acao("PETR4"));
			
			MediaAritimetica media=new MediaAritimetica(
					new Acao("PETR4"),
					17,
					Utilitario.converteStringParaDate("09/11/2018")
				
					);
			
			System.out.println(media.buscaValor());
			//26.61
			
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

		Negociacao.ordenarPorDataDecrescente(negociacoesDaAcao);
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

/*		BigDecimal valor1=new Media(
				acao,periodo,Negociacao.buscaDataAnteriorNegociacao(dataReferencia,4),null
				).buscaValor();
		
		BigDecimal valor2=new Media(
				acao,periodo,Negociacao.buscaDataAnteriorNegociacao(dataReferencia,2),null
				).buscaValor();
				
		BigDecimal valor3=buscaValor();*/
		
		BigDecimal valor1=buscaValorComOffset(4);
		BigDecimal valor2=buscaValorComOffset(2);
		BigDecimal valor3=buscaValor();

		if(valor1.compareTo(valor2) < 0 && valor2.compareTo(valor3) < 0)
			return true;
		else
			return false;
	}
	
	public BigDecimal buscaValorComOffset(int offset){
		return new MediaAritimetica(
				acao,periodo,Negociacao.buscaDataAnteriorNegociacao(dataReferencia,offset)
				).buscaValor();
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

	public Date getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(Date dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	
	
}
