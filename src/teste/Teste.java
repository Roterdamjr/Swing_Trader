package teste;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.NegociacaoDao;
import modelo.Acao;
import modelo.AcaoFactory;
import modelo.MediaAritimetica;
import modelo.MediaExponencial;
import modelo.Negociacao;
import modelo.Semana;
import modelo.ValorData;
import service.DifusorFluxoService;
import utilitarios.Utilitario;

public class Teste {
	
	public static void main(String[] args) {
		
		//new Service().analiseSemanal();
		
/*		Date dataRef=Utilitario.converteStringParaDate("12/11/2018");
		Date d=dataRef;
		d=Utilitario.adicionaDiasEmDate(d,-1);
		System.out.println(
				d.equals(Utilitario.converteStringParaDate("11/11/2018"))
		);*/
/*		
		for(Date d=Utilitario.converteStringParaDate("12/11/2018");
			!d.equals(Utilitario.converteStringParaDate("10/11/2018"));	
			d=Utilitario.adicionaDiasEmDate(d,-1)
			){
			System.out.println(d);
		}		
		*/
/*		
		MediaExponencial media=mediaExponencialSemanal();
		
		for(Date d=Utilitario.converteStringParaDate("12/11/2018");
				!d.equals(Utilitario.converteStringParaDate("10/11/2018"));	
				d=Utilitario.adicionaDiasEmDate(d,-1)
				){
			System.out.println(d);
		}*/
		
		int a=7,total=9;
		Utilitario.indicePercentagem(a, total);
	}
	
	private static MediaExponencial mediaExponencialSemanal(){
			
		ArrayList<Semana> datasSemanais=Negociacao.buscaDatasSemanal();
		
		List<ValorData> valores=new ArrayList<ValorData>();
		
		//para cada ação
		//popula MediaExponencial com dados da acao
		NegociacaoDao negociacaoDao=new NegociacaoDao();
		
		for(Semana semana:datasSemanais){
			//System.out.println("dia: "+semana.getDataInicial());
			Negociacao negociacao=null;
			try {
				negociacao=negociacaoDao.buscaNegociacaoPorAcaoPorDia(new Acao("PETR4"), 
						semana.getDataInicial());
			} catch (Exception e) {
				e.printStackTrace();
			}	
			valores.add( new ValorData(negociacao.getData(),negociacao.getPrecoUltimo()));
		}

		MediaExponencial me=new MediaExponencial(72, valores);	
		me.exibirMedias();
		return me;
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
		new DifusorFluxoService().difusorFluxo(acao);
	}
	

	
}
