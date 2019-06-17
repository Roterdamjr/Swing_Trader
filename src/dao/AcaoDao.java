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
		}
	    
	    return acoes;
	    
	}
	
	public void insereAcaoNoBanco()  throws Exception{

			PreparedStatement stmt ;
			stmt= connection.prepareStatement("delete from tb_acao");
			stmt.execute();

			ArrayList<Acao> lista = buscaTodasAcoes();
			for(Acao acao:lista){
				//String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/insereAcoes.sql");	
				stmt= connection.prepareStatement("insert into tb_acao values(?,'N')");
				stmt.setString(1, acao.getCodigoNegociacao());
				stmt.execute();

				if (stmt != null) {
					stmt.close();
				}
			}
			
			stmt= connection.prepareStatement("select codneg from tb_swing_trade");
		    rs=stmt.executeQuery();
		    
		    while (rs.next()) {
		    	stmt= connection.prepareStatement("update tb_acao set in_swing_trade='S' where codneg=?");
		    	stmt.setString(1,rs.getString(1));
		    	stmt.execute();
		    }		
		
	}
	
	public ArrayList<Acao> buscaAcoesComLiquidez() throws Exception{
		ArrayList<Acao> acoes= new ArrayList<Acao>();		
		
	    try {						
			String query="select codneg from tb_acao where in_swing_trade ='S'";	   

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
		}
	    
	    return acoes;
	}

}
