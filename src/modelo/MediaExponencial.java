package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class MediaExponencial {

	Acao acao;
	int periodo;	
	List<ValorData> medias;
	
	
	public MediaExponencial(Acao acao, int periodo) {
		super();
		this.acao = acao;
		this.periodo = periodo;
		medias=getMedias();
	}

	public List<ValorData> getMedias() {
		List<ValorData> medias=new ArrayList<ValorData>();
		
		/*
		 * busca negociacoes em ordem crescente
		 * */
		List<Negociacao> negociacoes=null;
		try {
			negociacoes = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		Negociacao.ordenarPorDataCrescente(negociacoes);
		
		//carrrega valores de negociacoes
		List<ValorData> valores=new ArrayList<ValorData>();
		for(Negociacao n:negociacoes){
			valores.add( new ValorData(n.getData(),n.getPrecoUltimo()));
		}
		/*fim busca negociacoes*/
		
		
		
		/*
		 * calcula médias
		 * */		
		
		BigDecimal total=new BigDecimal(0),valor=null,valorAnterior=null;
		int i=1;		
		BigDecimal k= BigDecimal.valueOf(2.0/(periodo+1));
		
		for(ValorData valorData:valores){			
			if(i<=periodo){
				total=total.add(valorData.getValor());
			}
			
			if(i==periodo){				
				//busco a media inicial:  entre o primeiro preço existente e o preço-base
				valor=total.divide(new BigDecimal(periodo),2);
				ValorData me= new ValorData(valorData.getDataReferencia(),valor);
				medias.add(me);
				valorAnterior=valor;
			}
			
			if(i>periodo){	
				valor=valorData.getValor();
				valor=valor.subtract(valorAnterior);
				valor=valor.multiply(k);
				valor=valor.setScale(2, BigDecimal.ROUND_DOWN);
				valor=valor.add(valorAnterior);		
				ValorData me= new ValorData(valorData.getDataReferencia(),valor);
				medias.add(me);
				valorAnterior=valor;
			}

			i++;

		}	

		return medias;
	}

	public boolean isCrescente(Date dataReferencia){
		//boolean ret=false;
				
		ordenarPorDataDecrescente();
		ValorData mp1=buscaMediaNaData(dataReferencia);
		System.out.println("media: " + mp1);
		ValorData mp2=buscaMediaNaData(Negociacao.buscaDataAnteriorNegociacao(dataReferencia,2));
		System.out.println("media: " + mp2);
		ValorData mp3=buscaMediaNaData(Negociacao.buscaDataAnteriorNegociacao(dataReferencia,4));
		System.out.println("media: " + mp3);

		if(mp1.comparar(mp2)==1&&mp2.comparar(mp3)==1)
			return true;
		else
			return false;
		
	}
	
	
	public ValorData buscaMediaNaData(Date dataReferencia){
		ValorData ret=null;
		for(ValorData mp:medias){
			if(mp.getDataReferencia().equals(dataReferencia)){
				ret=mp;
				break;
			}
		}
		return ret;
	}
	
	public void imprimirMedias(){
		for(ValorData media:medias){			
			System.out.println(media.getDataReferencia()+",  "+ media.getValor());
		}

	}
	public void ordenarPorDataCrescente(){
		medias.sort(new Comparator<ValorData>() {
		    @Override
		    public int compare(ValorData o1, ValorData o2) {
		        return o1.getDataReferencia().compareTo(o2.getDataReferencia());
		    }
		});
	}
	
	public void ordenarPorDataDecrescente(){
		System.out.println("ordenando");
		medias.sort(new Comparator<ValorData>() {
		    @Override
		    public int compare(ValorData o1, ValorData o2) {
		        return -o1.getDataReferencia().compareTo(o2.getDataReferencia());
		    }
		});
		System.out.println("fim ordenando");
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

	

	public void setMedias(List<ValorData> medias) {
		this.medias = medias;
	}
		
}

