package principal;

import java.util.ArrayList;

import service.DifusorFluxoService;
import modelo.Acao;
import dao.AcaoDao;

public class Principal {
	public static void main(String[] args) {
		/* 
		popula tb_acao com os ativos lidos da tb_historico_cotacao. 
		 Informa nessa tabela se o ativo tem liquidez par swing trade conforme lista do Andre Moraes
		*/ 
		System.out.println("carregando ações");
		try {
			new AcaoDao().insereAcaoNoBanco();	
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Fim carregando ações");
		
		
		
		
	}
}
