package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import modelo.Acao;
import modelo.MediaExponencial;
import modelo.Negociacao;
import dao.NegociacaoDao;

public class Service {
	

	public List<MediaExponencial> buscaValores(Acao acao,int periodo){
		List<MediaExponencial> medias=new ArrayList<MediaExponencial>();
		
		List<Negociacao> negociacoesDaAcao=null;
		try {
			negociacoesDaAcao = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//busco a media inicial:  entre o primeiro preço existente e o preço-base
		Negociacao.ordenarPorDataCrescente(negociacoesDaAcao);
		BigDecimal total=new BigDecimal(0),valor=null,valorAnterior=null;
		int i=1;

		
		for(Negociacao negociacao:negociacoesDaAcao){			
			if(i<=periodo){
				total=total.add(negociacao.getPrecoUltimo());
			}
			
			if(i==periodo){				
				valor=total.divide(new BigDecimal(periodo),2);
				MediaExponencial me= new MediaExponencial(negociacao.getData(),valor);
				System.out.println(negociacao.getData()+" - "  + valor+" - "+total);
				medias.add(me);

				valorAnterior=valor;
			}
			
			if(i>periodo){	

				valor=negociacao.getPrecoUltimo();
				System.out.println(negociacao.getData()+" - "  + valor+ "valor ant "+valorAnterior );

				valor=valor.subtract(valorAnterior);
				System.out.println(negociacao.getData()+" - "  + valor);

				valor=valor.multiply(new BigDecimal(0.182));
				valor=valor.setScale(2, BigDecimal.ROUND_DOWN);
				System.out.println(negociacao.getData()+" - "  + valor);
				
				valor=valor.add(valorAnterior);
				System.out.println(negociacao.getData()+" - "  + valor);
				
				MediaExponencial me= new MediaExponencial(negociacao.getData(),valor);
				medias.add(me);

				valorAnterior=valor;
			}
			
			i++;
			if(i>15) break;
		}	
		
		//começo a calculara a media dia a dia a aprtir da inicial
		
		return medias;
	}
}
