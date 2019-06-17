package dao;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import jdbc.DaoBase;
import modelo.Acao;
import modelo.MediaExponencial;
import modelo.Negociacao;
import modelo.Semana;
import modelo.ValorData;
import utilitarios.Utilitario;

public class NegociacaoDao extends DaoBase{

	public static void main(String[] args) {
		try {
			Negociacao negociacao=new NegociacaoDao().buscaNegociacaoPorAcaoPorDia(new Acao("PETR4"),
					Utilitario.converteStringParaDate("05/01/2015"));
			
			
			
				System.out.println(negociacao.getData()+ " - "+ negociacao.getPrecoUltimo());

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Negociacao> buscaNegociacaoPorAcao(Acao acao) throws Exception{
		
		ArrayList<Negociacao> negociacoesDaAcao= new ArrayList<Negociacao>();		
		
	    try {						
			String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/consultaNegociacaoPorAcao.sql");	   

			PreparedStatement  stmt = connection.prepareStatement(query);
		    stmt.setString(1,acao.getCodigoNegociacao());
		    rs=stmt.executeQuery();
		    
		    while (rs.next()) {
		    	Negociacao negociacao= new Negociacao(rs.getDate(1), 
		    										rs.getDouble(2),
		    										rs.getDouble(3),
		    										rs.getDouble(4),
		    										rs.getDouble(5),
		    										rs.getDouble(6),
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
	
	public Negociacao buscaNegociacaoPorAcaoPorDia(Acao acao,Date dataNegociacao) throws Exception{	
		Negociacao negociacao=null;
	    try {						
			String query=Utilitario.lerTextoDeArquivo(Utilitario.pathCorrente+"queries/consultaNegociacaoPorAcaoPorDia.sql");	   

			//PreparedStatement  
			stmt = connection.prepareStatement(query);
		    stmt.setString(1,acao.getCodigoNegociacao());
		    stmt.setString(2,Utilitario.converteDateParaString(dataNegociacao));
		    rs=stmt.executeQuery();
		    
		    while (rs.next()) {
		    	negociacao= new Negociacao(rs.getDate(1), 
		    										rs.getDouble(2),
		    										rs.getDouble(3),
		    										rs.getDouble(4),
		    										rs.getDouble(5),
		    										rs.getDouble(6),
		    										null
		    			);		    		    	
		    }

		} catch (SQLException e) {  //Erro no Select			
			e.printStackTrace();
			throw new Exception();
		}finally{ 
			if (rs != null) {
				rs.close();
			}

			if (stmt != null) {
				stmt.close();
			}
		}	    
	    return negociacao;	
		
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
			if (rs != null) {
				rs.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
		}	    
	    return datasDeNegociacoes;
	}
	
	public void analiseSemanal(ArrayList<Acao> listaComLiquidez){
			
		ArrayList<Semana> datasSemanais=Negociacao.buscaDatasSemanal();

		
		NegociacaoDao negociacaoDao=new NegociacaoDao();
		
		for(Acao acao:listaComLiquidez){
			System.out.println();
			List<ValorData> valores=new ArrayList<ValorData>();
			
			//para cada ação
			//popula MediaExponencial com dados da acao
			for(Semana semana:datasSemanais){
				Negociacao negociacao=null;
				try {
					negociacao=negociacaoDao.buscaNegociacaoPorAcaoPorDia(new Acao(acao.getCodigoNegociacao()), 
							semana.getDataInicial());
				} catch (Exception e) {
					e.printStackTrace();
				}	
				valores.add( new ValorData(negociacao.getData(),negociacao.getPrecoUltimo()));
			}
	
			MediaExponencial me=new MediaExponencial(72, valores);	
			
			
			Date dataRef=Utilitario.converteStringParaDate("12/11/2018");
			//for
			System.out.println(acao.getCodigoNegociacao()+ "," +
					me.buscaMediaNaData(Utilitario.converteStringParaDate("12/11/2018")).getValor()
			);
			
			System.exit(0);
		}
	}
}
