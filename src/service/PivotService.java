package service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import utilitarios.Utilitario;
import modelo.Acao;
import modelo.ContadorDeClande;
import modelo.Negociacao;
import modelo.ValorData;
import dao.NegociacaoDao;

public class PivotService {
	
	public static void main(String[] args) {
		PivotService obj =new PivotService();
		obj.executar();
		
	}
	
	public void executar(){
		List<Negociacao> negociacoes= carregaLista(new Acao("PETR4"));
		
		
		for(Negociacao n: negociacoes){
			System.out.println(Utilitario.converteDateParaString(n.getData())+ "	"+ n.getPrecoUltimo());
		}
		//busca 1o fundo
		ContadorDeClande contador=buscaFundo(negociacoes, Utilitario.converteStringParaDate("01/01/2017"));
		
		//busca 1o topo
		
		
/*		while(true){
			// busca prox fundo
			
			//se distancia pro fundo anterior >17 guarda fundo2 e sai
			
			//senão, continua 
		}*/
		
		
		
	}
	private List<Negociacao> carregaLista(Acao acao){
		List<Negociacao> negociacoes=null;
		try {
			negociacoes = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//ordena
		negociacoes.sort(new Comparator<Negociacao>() {
		    @Override
		    public int compare(Negociacao o1, Negociacao o2) {
		        return o1.getData().compareTo(o2.getData());
		    }
		});
		
		return negociacoes;
	}
	
	private ContadorDeClande buscaFundo(List<Negociacao> negociacoes,Date dataInicial){
		ContadorDeClande contador = new ContadorDeClande();
		double precoBase=0,precoMinimo=0;
		
		for(Negociacao n: negociacoes){
			if(n.getData().compareTo(dataInicial)>=0 && precoBase==0){
				precoBase=n.getPrecoMinimo();
			}
			
			if(n.getData().compareTo(dataInicial)>0 && precoBase>0){
				contador.setValorExgtremo(n.getPrecoMinimo());
				contador.setData(n.getData());
				precoMinimo=n.getPrecoMinimo();
				if(precoMinimo>precoBase){
					break;
				}
			}
			return contador;
			//System.out.println(n.getData()+ "	"+ n.getPrecoUltimo());
		}
		
		return contador;
	}
		
}
