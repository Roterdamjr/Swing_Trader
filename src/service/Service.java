package service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Acao;
import modelo.DataDeNegociacaoFactory;
import modelo.MediaExponencial;
import modelo.Negociacao;
import modelo.Semana;
import modelo.ValorData;
import dao.NegociacaoDao;

public class Service {

	public void difusorFluxo(Acao acao){
		/*
		 * busca negociacoes 
		 * */
		List<Negociacao> negociacoes=null;
		try {
			negociacoes = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<ValorData> valores=new ArrayList<ValorData>();
		for(Negociacao n:negociacoes){
			valores.add( new ValorData(n.getData(),n.getPrecoUltimo().doubleValue()));
		}	
		
		//calcula diferencas de ME21 e ME55 
		List<ValorData> valoreDaDifeenca=new ArrayList<ValorData>();
		
		MediaExponencial meCP=new MediaExponencial(21,valores);		
		MediaExponencial meLP=new MediaExponencial(55,valores);
		//meLP.exibirMedias();
		
		//calcula MACD
		double bd1=0,bd2=0,dif=0;
				
		for(Date dt:DataDeNegociacaoFactory.getDatasDeNegociacao()){
			if(meCP.buscaMediaNaData(dt)!=null && meLP.buscaMediaNaData(dt)!=null){
				bd1=meCP.buscaMediaNaData(dt).getValor();
				bd2=meLP.buscaMediaNaData(dt).getValor();
				dif=bd1-bd2;
				valoreDaDifeenca.add(new ValorData(dt,dif));
				//System.out.println(dt+" ;"+dif);
			}
		}
				
		//calcula sinais de curto prazo				
		MediaExponencial sinalCP=new MediaExponencial(34,valoreDaDifeenca);			
		MediaExponencial sinalLP=new MediaExponencial(89,valoreDaDifeenca);
				
		sinalCP.exibirMedias();
		//sinalLP.exibirMedias();
	}
	
	public void analiseSemanal(){
		 ArrayList<Semana> lista=Negociacao.buscaDatasSemanal();
		 
	}
}
