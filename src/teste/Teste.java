package teste;

import java.util.ArrayList;
import java.util.Date;

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
		Negociacao.buscaDatasSemanal();
		
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
	
	private static void semanal(){
		
		//loop para buscar cada domingo
		Date dataLimite=new Date();//data corrente					
		
		for(Date dt = Utilitario.converteStringParaDate("30/09/2018"); dt.before(dataLimite);
				dt=Utilitario.adicionaDiasEmDate(dt, 7)){
			//System.out.println(dt+"; " + dataLimite+dt.compareTo(dataLimite));
			
			Date primeiraData=null,ultimaData=null;
			boolean achou=false;
			for(Date dn:DataDeNegociacaoFactory.getDatasDeNegociacao()){
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
			System.out.println(primeiraData+ "; " +ultimaData);

		}

	}
	
}
