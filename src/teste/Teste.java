package teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;
import modelo.Acao;
import modelo.AcaoFactory;
import modelo.DataDeNegociacaoFactory;
import modelo.MediaAritimetica;
import modelo.MediaExponencial;
import modelo.Negociacao;
import modelo.ValorData;
import service.service;
import utilitarios.Utilitario;

public class Teste {
	
	public static void main(String[] args) {
		MME(new Acao("KROT3"));	
		
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
				new service().difusorFluxo(acao);
	}
	
}
