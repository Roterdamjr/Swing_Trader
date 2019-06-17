package modelo;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import utilitarios.Utilitario;

public class Negociacao {
	private Date data;
	private double precoAbertura;
	private double precoMaximo;
	private double precoMinimo;
	private double precoUltimo;
	private double volume;
	private Acao acao;

			
	public Negociacao(Date data, double precoAbertura, double precoMaximo,
			double precoMinimo, double precoUltimo, double volume,
			Acao acao) {
		super();
		this.data = data;
		this.precoAbertura = precoAbertura;
		this.precoMaximo = precoMaximo;
		this.precoMinimo = precoMinimo;
		this.precoUltimo = precoUltimo;
		this.volume = volume;
		this.acao = acao;				
	}
	
	public static ArrayList<Date> buscaDatasDeNegociacao(boolean ordemCrescente) {
		ArrayList<Date> lista= DataDeNegociacaoFactory.getDatasDeNegociacao();
		
		if(!ordemCrescente){
			lista.sort(new Comparator<Date>() {
			    @Override
			    public int compare(Date o1, Date o2) {
			        return -o1.compareTo(o2);
			    }
			});
		}

		return lista;
	}

	public static Date buscaDataAnteriorNegociacao(Date dataReferencia, int quantDias){
		
		Date dataRetornada=null;
		ArrayList<Date> lista=buscaDatasDeNegociacao(false);
		
		int cont=-1;
		boolean achou=false;
		for(Date dt:lista){
			if(!achou && dt.equals(dataReferencia)){				
				achou=true;
			}
			
			if(achou){
				dataRetornada=dt;
				cont++;
				if(cont==quantDias){
					break;
				}
			}
			//System.out.println(dt);
		}
		return dataRetornada;
		
	}
	
	public static void ordenarPorDataCrescente(List<Negociacao> negociacoesDaAcao){
		negociacoesDaAcao.sort(new Comparator<Negociacao>() {
		    @Override
		    public int compare(Negociacao o1, Negociacao o2) {
		        return o1.getData().compareTo(o2.getData());
		    }
		});
	}
	
	public static void ordenarPorDataDecrescente(List<Negociacao> negociacoesDaAcao){
		negociacoesDaAcao.sort(new Comparator<Negociacao>() {
		    @Override
		    public int compare(Negociacao o1, Negociacao o2) {
		        return -o1.getData().compareTo(o2.getData());
		    }
		});
	}	
	
	public static ArrayList<Semana> buscaDatasSemanal(){

		/*
		 * retorna datas de negociacao semanal		
		*/
		
		ArrayList<Semana> lista=new ArrayList<Semana> ();
		//loop para buscar cada domingo
		Date dataLimite=new Date();//data corrente					
		
		ArrayList<Date> datasNegociacao =DataDeNegociacaoFactory.getDatasDeNegociacao();
		
		for(Date dt = Utilitario.converteStringParaDate("01/01/2011"); 
				dt.before(dataLimite);
				dt=Utilitario.adicionaDiasEmDate(dt, 7)){
			
			Date primeiraData=null,ultimaData=null;
			boolean achou=false;
			for(Date dn:datasNegociacao){
			
				if(dn.after(dt)){
					if(!achou){
						achou=true;
						primeiraData=dn;
					}
					
					if(dn.after(Utilitario.adicionaDiasEmDate(dt, 6))){
						break;
					};
					ultimaData=dn;
				}								
			}
			if(primeiraData!=null){
				lista.add(new Semana(primeiraData, ultimaData));
				//System.out.println(primeiraData+ "; " +ultimaData);
			}
		}
		
		return lista;
	}
	
	public boolean isAlta(){
		return precoUltimo>precoAbertura;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getPrecoAbertura() {
		return precoAbertura;
	}
	public void setPrecoAbertura(double precoAbertura) {
		this.precoAbertura = precoAbertura;
	}
	public double getPrecoMaximo() {
		return precoMaximo;
	}
	public void setPrecoMaximo(double precoMaximo) {
		this.precoMaximo = precoMaximo;
	}
	public double getPrecoMinimo() {
		return precoMinimo;
	}
	public void setPrecoMinimo(double precoMinimo) {
		this.precoMinimo = precoMinimo;
	}
	public double getPrecoUltimo() {
		return precoUltimo;
	}
	public void setPrecoUltimo(double precoUltimo) {
		this.precoUltimo = precoUltimo;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	
	
	
		
	
	


}
