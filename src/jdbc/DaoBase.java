package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoBase {
	protected ResultSet rs=null;
	protected PreparedStatement stmt;	
	protected Connection connection;
	
	public DaoBase(){
		try{
			FabricaDeConexao fabrica = FabricaDeConexao.getInstancia();
			connection=fabrica.getConnection();
			
		} catch (Exception e) {
			System.out.println("errodaobase");
			//e.printStackTrace();
			throw new RuntimeException(e);
		}			
		
	}
	
	protected void fechaRs(){
		try {
			rs.close();
			//stmt.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void executaDML(String query) throws Exception{

		System.out.println(query);
		PreparedStatement stmt;

		stmt = connection.prepareStatement(query);
		stmt.executeQuery();

	}
	
	protected ResultSet executaBusca(String query){

		System.out.println(query);
		PreparedStatement stmt;
				
		try {
			stmt = connection.prepareStatement(query);
			rs=stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	protected PreparedStatement getStatmentParam(String query) throws Exception{
		stmt = connection.prepareStatement(query);
		return stmt;
	}
	
	

	
}
