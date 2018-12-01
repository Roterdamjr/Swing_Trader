package dao;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import jdbc.DaoBase;
import modelo.Acao;
import modelo.Negociacao;
import utilitarios.Utilitario;

public class NegociacaoDao extends DaoBase{

	public static void main(String[] args) {
		try {
			List<Negociacao> negociacoesDaAcao=new NegociacaoDao().buscaNegociacaoPorAcao(new Acao("ITUB4",null));
			
			//ordena a lista por data
			negociacoesDaAcao.sort(new Comparator<Negociacao>() {
			    @Override
			    public int compare(Negociacao o1, Negociacao o2) {
			        return o1.getData().compareTo(o2.getData());
			    }
			});
			
			for(Negociacao negociacao:negociacoesDaAcao){			
				System.out.println(negociacao.getData()+ " - "+ negociacao.getPrecoUltimo());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Negociacao> buscaNegociacaoPorAcao(Acao acao ) throws Exception{
		
		ArrayList<Negociacao> negociacoesDaAcao= new ArrayList<Negociacao>();		
		
	    try {						
			String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/consultaNegociacaoPorAcao.sql");	   

			PreparedStatement  stmt = connection.prepareStatement(query);
		    stmt.setString(1,acao.getCodigoNegociacao());
		    rs=stmt.executeQuery();
		    
		    while (rs.next()) {
		    	Negociacao negociacao= new Negociacao(rs.getDate(1), 
		    										rs.getBigDecimal(2),
		    										rs.getBigDecimal(3),
		    										rs.getBigDecimal(4),
		    										rs.getBigDecimal(5),
		    										rs.getBigDecimal(6),
		    										null
		    			);		    	
		    	negociacoesDaAcao.add(negociacao);
	    	
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
	    return negociacoesDaAcao;	    
	}
	
	
	public ArrayList<Date> buscaDatasaDeNegociacao() throws Exception{
		
		ArrayList<Date> datasDeNegociacoes= new ArrayList<Date>();		
		
	    try {						
			String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/consultaDatasDeNegociacao.sql");	   

			PreparedStatement  stmt = connection.prepareStatement(query);
		    rs=stmt.executeQuery();
		    
		    while (rs.next()) {
		    	datasDeNegociacoes.add(rs.getDate(1));
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
	    
	    return datasDeNegociacoes;
	    
	}
}
