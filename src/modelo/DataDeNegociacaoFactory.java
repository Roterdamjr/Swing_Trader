package modelo;

import java.util.ArrayList;
import java.util.Date;

import dao.NegociacaoDao;

public class DataDeNegociacaoFactory {
	 
	private static DataDeNegociacaoFactory uniqueInstance = new DataDeNegociacaoFactory();
	private static ArrayList<Date> datasDeNegociacao=buscaDatasDeNegociacao();
	
	private DataDeNegociacaoFactory() {
	  }
	 
	public static DataDeNegociacaoFactory getInstance() {
	     return uniqueInstance;
	}

	public static ArrayList<Date> getDatasDeNegociacao() {
		return datasDeNegociacao;
	}
	
	private static ArrayList<Date> buscaDatasDeNegociacao() {
		
		try {
			datasDeNegociacao=new NegociacaoDao().buscaDatasaDeNegociacao();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasDeNegociacao;
	}
	
	
}
