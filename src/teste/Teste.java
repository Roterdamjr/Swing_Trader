package teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Acao;
import modelo.AcaoFactory;
import modelo.MediaAritimetica;
import modelo.MediaExponencial;
import service.Service;
import utilitarios.Utilitario;

public class Teste {
	
	public static void main(String[] args) {
		MME(new Acao("PETR4"),10);	
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

	private static void cruzamentoDeMedias(){
		
	
	}
	
	private static void MME(Acao acao,int numeroDePeriodos){
		Service serv=new Service();
		List<MediaExponencial>medias=serv.buscaValores(acao, numeroDePeriodos);
		for(MediaExponencial media:medias){
			
			System.out.println( media.getValor());
		}
		
	}
	
}
