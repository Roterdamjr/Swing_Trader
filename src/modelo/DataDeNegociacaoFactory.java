package modelo;

import java.util.ArrayList;
import java.util.Date;

import dao.NegociacaoDao;

public class DataDeNegociacaoFactory {
	 
	private static DataDeNegociacaoFactory uniqueInstance = new DataDeNegociacaoFactory();
	 
	private DataDeNegociacaoFactory() {
	  }
	 
	public static DataDeNegociacaoFactory getInstance() {
	     return uniqueInstance;
	}
	
	public ArrayList<Date> buscaDatasDeNegociacao() {
		ArrayList<Date> datasDeNegociacao=new ArrayList<Date>();
		
		try {
			datasDeNegociacao=new NegociacaoDao().buscaDatasaDeNegociacao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasDeNegociacao;
	}
}
