package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.DaoBase;
import modelo.Acao;
import utilitarios.Utilitario;

public class AcaoDao extends DaoBase{
	
	public ArrayList<Acao> buscaTodasAcoes() throws Exception{
		
		ArrayList<Acao> acoes= new ArrayList<Acao>();		
		
	    try {						
			String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/consultaTodasAcoes.sql");	   

			PreparedStatement  stmt = connection.prepareStatement(query);
		    rs=stmt.executeQuery();
		    
		    while (rs.next()) {
		    	Acao acao=new Acao(rs.getString(1));
		    	acoes.add(acao);
		    }

		} catch (SQLException e) {  //Erro no Select
			
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
	    
	    return acoes;
	    
	}
}
