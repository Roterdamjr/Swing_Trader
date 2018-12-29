package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class MediaExponencial {

	Acao acao;
	int periodo;	
	List<MediaPontual> medias;
	
	
	public MediaExponencial(Acao acao, int periodo) {
		super();
		this.acao = acao;
		this.periodo = periodo;
	}

	public List<MediaPontual> getMedias() {
		List<MediaPontual> medias=new ArrayList<MediaPontual>();
		
		/*
		 * busca negociacoes em ordem crescente
		 * */
		List<Negociacao> negociacoesDaAcao=null;
		try {
			negociacoesDaAcao = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		Negociacao.ordenarPorDataCrescente(negociacoesDaAcao);
		/*fim */
		
		/*
		 * calcula médias
		 * */
		BigDecimal total=new BigDecimal(0),valor=null,valorAnterior=null;
		int i=1;		
		BigDecimal k= BigDecimal.valueOf(2.0/(periodo+1));
		
		for(Negociacao negociacao:negociacoesDaAcao){			
			if(i<=periodo){
				total=total.add(negociacao.getPrecoUltimo());
			}
			
			if(i==periodo){				
				//busco a media inicial:  entre o primeiro preço existente e o preço-base
				valor=total.divide(new BigDecimal(periodo),2);
				MediaPontual me= new MediaPontual(negociacao.getData(),valor);
				medias.add(me);
				valorAnterior=valor;
			}
			
			if(i>periodo){	
				valor=negociacao.getPrecoUltimo();
				valor=valor.subtract(valorAnterior);
				valor=valor.multiply(k);
				valor=valor.setScale(2, BigDecimal.ROUND_DOWN);
				valor=valor.add(valorAnterior);		
				MediaPontual me= new MediaPontual(negociacao.getData(),valor);
				medias.add(me);
				valorAnterior=valor;
			}

			i++;

		}	

		return medias;
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

	

	public void setMedias(List<MediaPontual> medias) {
		this.medias = medias;
	}
		
}

