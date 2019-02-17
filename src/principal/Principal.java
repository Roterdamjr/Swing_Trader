package principal;

import java.util.ArrayList;

import modelo.Acao;
import dao.AcaoDao;

public class Principal {
	public static void main(String[] args) {
		// Carregar ações
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
