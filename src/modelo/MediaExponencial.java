package modelo;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import utilitarios.Utilitario;
import dao.NegociacaoDao;

public class MediaExponencial {

	int periodo;	
	List<ValorData> medias;
	List<ValorData> valores;
	
	public MediaExponencial (int periodo,List<ValorData> vals) {
		super();
		this.periodo = periodo;		
		this.valores=vals;
		calculaMedias();
	}
	
	private void calculaMedias(){		
	
		List<ValorData> medias=new ArrayList<ValorData>();
		//ordena
		valores.sort(new Comparator<ValorData>() {
		    @Override
		    public int compare(ValorData o1, ValorData o2) {
		        return o1.getDataReferencia().compareTo(o2.getDataReferencia());
		    }
		});
		
		double total=(0),valor=0,valorAnterior=0;
		int i=1;		
		double k= (2.0/(periodo+1));
		
		for(ValorData valorData:valores){			
			if(i<=periodo){
				total+=valorData.getValor();
			}
			
			if(i==periodo){				
				//busco a media inicial:  entre o primeiro preço existente e o preço-base
				valor=total/periodo;
				ValorData me= new ValorData(valorData.getDataReferencia(),valor);
				medias.add(me);
				valorAnterior=valor;
			}
			
			if(i>periodo){	
				valor=valorData.getValor();
				valor-=valorAnterior;
				valor*=k;
				valor+=valorAnterior;		
				ValorData me= new ValorData(valorData.getDataReferencia(),valor);
				medias.add(me);
				valorAnterior=valor;
			}

			i++;
		}	

		this.medias=medias;
	}

	public boolean isCrescente(Date dataReferencia){
		//boolean ret=false;
				
		ordenarPorDataDecrescente();
		ValorData mp1=buscaMediaNaData(dataReferencia);
		//System.out.println("media: " + mp1);
		ValorData mp2=buscaMediaNaData(Negociacao.buscaDataAnteriorNegociacao(dataReferencia,2));
		//System.out.println("media: " + mp2);
		ValorData mp3=buscaMediaNaData(Negociacao.buscaDataAnteriorNegociacao(dataReferencia,4));
		//System.out.println("media: " + mp3);

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
/*	
	public ValorData buscaMediaAnteriorAData(Date dataReferencia){
		//busca a média imediatamente anterior anterior `a data de referência
		// utilizada em meédia semanal 
		
		for(Date d=Utilitario.converteStringParaDate("12/11/2018");
				!d.equals(Utilitario.converteStringParaDate("10/11/2018"));	
				d=Utilitario.adicionaDiasEmDate(d,-1)
				){
			System.out.println(d);
		}
		
		return null;
	}*/
	
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

	public void exibirMedias(){
		for(ValorData vd:medias){
			System.out.println(vd.getDataReferencia()+ ";"+vd.getValor());
		}
	}

	public int getPeriodo() {
		return periodo;
	}
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}
	public List<ValorData> getMedias() {
		return medias;
	}
	public void setMedias(List<ValorData> medias) {
		this.medias = medias;
	}
		
}

