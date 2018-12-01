package teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import modelo.Acao;
import modelo.AcaoFactory;
import modelo.Media;
import modelo.Negociacao;
import utilitarios.Utilitario;

public class Teste {
	
	public static void main(String[] args) {

		//teste(new Acao("AALL34"), "09/10/2018");

		
		
		ArrayList<Acao> acoes= AcaoFactory.getInstance().buscaAcoes();
		for(Acao ac:acoes){
			teste(ac,"09/10/2018");
		}
		

	}

	private static void teste(Acao acao, String dt){
		Date datareferencia=Utilitario.converteStringParaDate(dt);

		Media m= new Media(acao,17,datareferencia,null);
		if(acao.getCodigoNegociacao().equals("ABCB4")){
			System.out.println(acao.getCodigoNegociacao() + ",  "+ m.isCrescente());
		}
		System.out.println(acao.getCodigoNegociacao() + ",  "+ m.isCrescente());
	}
	
}
