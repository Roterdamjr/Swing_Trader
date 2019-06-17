package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.DaoBase;
import utilitarios.Utilitario;

public class LinhaDao extends DaoBase{
	
	 int[] comprimentoCampos= new int[]{2,8,2,12,3,12,10,3,4,13,13,13,13,13,13,13,5,18,18,13,1,8,7,13,12,3};
	 String linha;
	 static int contLinha=0;


	public void importarArquivo(String arquivo) throws Exception{
		
		System.out.println("Apagando dados de 2018");

		//PreparedStatement stmt1= connection.prepareStatement("delete from TB_HIST_COTACOES_final where to_char(data_pregao,'yyyy')=2018");
		//stmt1.execute();
		
		System.out.println("Importando dados");
		
		try {	
			String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/InsereLinhaFinal.sql");			    
		    PreparedStatement stmt= connection.prepareStatement(query);
			
			FileReader fr = new FileReader(arquivo);
		    BufferedReader buffReader = new BufferedReader(fr);
					    
		    boolean primeiraLinha=true;
		    
		    while ((linha = buffReader.readLine()) != null)	    { 

		    	if(!primeiraLinha){	
		    		salvaLinhaNoBanco(stmt);
		    	}
		    	primeiraLinha=false;
		    }
		    fr.close();		   	    
		    
		} catch (SQLException e) {  //Erro no Select
			System.out.println(e.getMessage());
			System.out.println(linha);
			e.printStackTrace();
			throw new Exception();
		}finally{ 
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
    
		System.out.println("fim");

	}
	
	private void apagaTabela() throws Exception{
		
		try{
			PreparedStatement stmt= connection.prepareStatement("delete from TB_HIST_COTACOES");
			stmt.execute();
		} catch (SQLException e) {  //Erro no Select
			System.out.println(e.getMessage());
			throw new Exception();
		}finally{ 
			if (stmt != null) {
				stmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	
	private void salvaLinhaNoBanco(PreparedStatement stmt) throws Exception{
		String campo,resto,dataLida;
		
		dataLida=linha.substring(2,10);
		if(++contLinha%10000==0){			
			System.out.println(dataLida);
		}
		
		campo=linha.substring(0,2);
		
		if(!campo.equals("99")){ //se não for rodapé
			for(int i=0; i<comprimentoCampos.length; i++){
				campo=linha.substring(0,comprimentoCampos[i]);
				stmt.setString(i+1,campo);
				resto=linha.substring(comprimentoCampos[i]);
				linha=resto;										
			}
			stmt.execute();
		}
		

	}
}
