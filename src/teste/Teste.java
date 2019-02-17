package teste;

import java.util.ArrayList;
import java.util.Date;

import dao.AcaoDao;
import modelo.Acao;
import modelo.AcaoFactory;
import modelo.DataDeNegociacaoFactory;
import modelo.MediaAritimetica;
import modelo.Negociacao;
import service.Service;
import utilitarios.Utilitario;

public class Teste {
	
	public static void main(String[] args) {
		//MME(new Acao("BBAS3"));
		//semanal();
		//new Service().analiseSemanal();
		try {
			System.out.println("buscando");
			new AcaoDao().buscaTodasAcoes();
			System.out.println("buscando");
			new AcaoDao().buscaTodasAcoes();
			System.out.println("buscando");
			new AcaoDao().buscaTodasAcoes();
			System.out.println("buscando");;
			new AcaoDao().buscaTodasAcoes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private static void mediaCrescente(){
		//exibe acoes com media crescente
		ArrayList<Acao> acoes= AcaoFactory.getInstance().buscaAcoes();
		for(Acao acao:acoes){
			Date datareferencia=Utilitario.converteStringParaDate("09/10/2018");

			MediaAritimetica m= new MediaAritimetica(acao,17,datareferencia);
			//if( m.isCrescente()){
				System.out.println(acao.getCodigoNegociacao() + ":  "+m.buscaValor()+ ":  "+ m.isCrescente());
			//}
		}
	}
	
	private static void MME(Acao acao){
		new Service().difusorFluxo(acao);
	}
	

	
}
