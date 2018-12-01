package modelo;

import java.util.ArrayList;

import dao.AcaoDao;

public class AcaoFactory {
	 
	private static AcaoFactory uniqueInstance = new AcaoFactory();
	 
	private AcaoFactory() {
	  }
	 
	public static AcaoFactory getInstance() {
	     return uniqueInstance;
	}
	
	public ArrayList<Acao> buscaAcoes() {
		ArrayList<Acao> datasDeNegociacao=new ArrayList<Acao>();
		
		try {
			datasDeNegociacao=new AcaoDao().buscaTodasAcoes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datasDeNegociacao;
	}
}
