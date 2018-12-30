package modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;

public class Negociacao {
	private Date data;
	private BigDecimal precoAbertura;
	private BigDecimal precoMaximo;
	private BigDecimal precoMinimo;
	private BigDecimal precoUltimo;
	private BigDecimal volume;
	private Acao acao;

			
	public Negociacao(Date data, BigDecimal precoAbertura, BigDecimal precoMaximo,
			BigDecimal precoMinimo, BigDecimal precoUltimo, BigDecimal volume,
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
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public BigDecimal getPrecoAbertura() {
		return precoAbertura;
	}
	public void setPrecoAbertura(BigDecimal precoAbertura) {
		this.precoAbertura = precoAbertura;
	}
	public BigDecimal getPrecoMaximo() {
		return precoMaximo;
	}
	public void setPrecoMaximo(BigDecimal precoMaximo) {
		this.precoMaximo = precoMaximo;
	}
	public BigDecimal getPrecoMinimo() {
		return precoMinimo;
	}
	public void setPrecoMinimo(BigDecimal precoMinimo) {
		this.precoMinimo = precoMinimo;
	}
	public BigDecimal getPrecoUltimo() {
		return precoUltimo;
	}
	public void setPrecoUltimo(BigDecimal precoUltimo) {
		this.precoUltimo = precoUltimo;
	}
	public BigDecimal getVolume() {
		return volume;
	}
	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	public Acao getAcao() {
		return acao;
	}
	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	
	
	
		
	
	


}
