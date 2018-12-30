package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Acao;
import modelo.DataDeNegociacaoFactory;
import modelo.MediaExponencial;
import modelo.Negociacao;
import modelo.ValorData;
import dao.NegociacaoDao;

public class service {

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
		MediaExponencial me21=new MediaExponencial(21,valores);
		MediaExponencial me55=new MediaExponencial(55,valores);
		
		BigDecimal bd1=null,bd2=null,dif=null;
				
		for(Date dt:DataDeNegociacaoFactory.getDatasDeNegociacao()){
			if(me21.buscaMediaNaData(dt)!=null && me55.buscaMediaNaData(dt)!=null){
				bd1=me21.buscaMediaNaData(dt).getValor();
				bd2=me55.buscaMediaNaData(dt).getValor();
				dif=bd1.subtract(bd2);
				valoreDaDifeenca.add(new ValorData(dt,dif));
			}

			//System.out.println(dt+ ", "  +bd1+ ", "  +bd2+ ", "  +dif +", "  );
		}

		//calcula ME34 da diferença
		MediaExponencial me34=new MediaExponencial(34,valoreDaDifeenca);
		me34.exibirMedias();
	}
}
