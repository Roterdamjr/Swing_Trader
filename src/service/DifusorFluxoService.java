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
import dao.AcaoDao;
import dao.NegociacaoDao;

public class DifusorFluxoService {

	
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
			valores.add( new ValorData(n.getData(),n.getPrecoUltimo()));
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
		 
		//busca acoes com liquidez
		ArrayList<Acao> listaComLiquidez=null;
		try {
			listaComLiquidez = new AcaoDao().buscaAcoesComLiquidez();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		new NegociacaoDao().analiseSemanal(listaComLiquidez);
		//busca dias das semanas 
		/*ArrayList<Semana> lista=Negociacao.buscaDatasSemanal();

		
		NegociacaoDao negociacaoDao=new NegociacaoDao();
		
		for(Acao acao:listaComLiquidez){
			System.out.println();
			List<ValorData> valores=new ArrayList<ValorData>();
			
			for(Semana semana:lista){
				Negociacao negociacao=null;
				try {
					negociacao=negociacaoDao.buscaNegociacaoPorAcaoPorDia(new Acao(acao.getCodigoNegociacao()), 
							semana.getDataInicial());
				} catch (Exception e) {
					e.printStackTrace();
				}	
				valores.add( new ValorData(negociacao.getData(),negociacao.getPrecoUltimo().doubleValue()));
			}
			//calcula ME
			MediaExponencial me=new MediaExponencial(72, valores);	
			me.exibirMedias();
			
		}*/
		
		

	}
}
