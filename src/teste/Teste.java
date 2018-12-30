package teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import modelo.Acao;
import modelo.AcaoFactory;
import modelo.MediaAritimetica;
import modelo.MediaExponencial;
import modelo.ValorData;
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
	
	private static void MME(Acao acao,int numeroDePeriodos){
		MediaExponencial me=new MediaExponencial(acao,numeroDePeriodos);	
		for(ValorData media:me.getMedias()){			
			System.out.println(media.getDataReferencia()+",  "+ media.getValor());
		}

		//media: data: 2018-10-30  valor:22.08   data: 2018-10-26  valor:21.80   data: 2018-10-24  valor:21.51 
	
		//System.out.println(me.isCrescente(Utilitario.converteStringParaDate("16/08/2018")));
	}
	
}
