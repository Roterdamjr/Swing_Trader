package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class CopyOfMediaExponencial {

	int periodo;	
	List<ValorData> medias;
	List<ValorData> valores;
	
	public CopyOfMediaExponencial (int periodo,List<ValorData> vals) {
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

		this.medias=medias;
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

