package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Acao;
import modelo.Negociacao;
import utilitarios.Utilitario;
import dao.NegociacaoDao;

public class ProbabilidadeService {
	
	//inicializa o RANGE
	int range=6;
	
	public static void main(String[] args) {
		new ProbabilidadeService().probabilidade();
	}
	
	public void probabilidade(){
		//"negociacoes" recebe em ordem crescente de data 
		Acao acao=new Acao("VALE3");
		List<Negociacao> negociacoes=null;
		try {
			negociacoes = new NegociacaoDao().buscaNegociacaoPorAcao(acao);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(int i=1;i<negociacoes.size();i++){
			System.out.println(historicoRelativoUmDia(negociacoes,i));
		}


	}
	
	
	public char historicoRelativoUmDia(List<Negociacao> negociacoes,int indiceReferencia){
	
		Negociacao.ordenarPorDataDecrescente(negociacoes);
	
		// constroi array de celulas com negociacoes dentro do range				
		ArrayList<Celula> celulas= new ArrayList<Celula>();
		int cont=0;
		boolean valorReferencia=true;
		
		for(Negociacao neg:negociacoes){
			if(cont==indiceReferencia){	
				valorReferencia=neg.isAlta();
			}
			
			if(cont>=indiceReferencia){							
				if(cont>0){					
					celulas.add(new Celula(neg.getData(),neg.isAlta()));					
					
					if(cont==(indiceReferencia+range-1)){
						break;
					}
				}
			}			
			cont++;
		}
		
		// varre celulas por determinado numero de vezes
		//System.out.println("\nvalorReferencia: "+valorReferencia);
		int contAlta=0;
		for(Celula cel:celulas){
			if(cel.isValor()){
				contAlta++;
			}
			//System.out.println(cel.toString());
		} 
		/*
		se maioria for de Alta e na data de referência também OU
		se maioria for de Baixa e na data de referência também 
		retorna T
		*/
		char retorno='F';
		if(Utilitario.indicePercentagem(contAlta, range)>50 && valorReferencia){
			retorno='T';
		}
		if(Utilitario.indicePercentagem(contAlta, range)==50){
			retorno='I';
		}
		if(Utilitario.indicePercentagem(contAlta, range) < 50&&!valorReferencia){
			retorno='T';
		}
		//System.out.println("Qtde Alta: " +contAlta+ "  perc(%): "+Utilitario.indicePercentagem(contAlta, range));

		return retorno;
	}
	
	private class Celula {
		Date data;
		boolean valor;
		
		public Celula(Date data, boolean valor) {
			super();
			this.data = data;
			this.valor = valor;
		}
		public Date getData() {
			return data;
		}
		public void setData(Date data) {
			this.data = data;
		}
		public boolean isValor() {
			return valor;
		}
		public void setValor(boolean valor) {
			this.valor = valor;
		}
		public String toString() {
			return "Celula [data=" + data + ", valor=" + valor + "]";
		}
	}
}
