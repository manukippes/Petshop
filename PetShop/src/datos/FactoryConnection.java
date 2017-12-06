package datos;
import java.io.Serializable;
import java.sql.*;

import org.apache.logging.log4j.Level;

import utilidades.ExcepcionEspecial;

public class FactoryConnection implements Serializable{
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="root";
	private String db="pet_chops";
	private Connection conn=null;
	private int cantConn=0;
	
	private static FactoryConnection instancia; 	//para tener solo y exclusivamente un único objeto de una clase.
	
	private FactoryConnection() 
	{
		try 
		{
			//para definir un driver y despues usarlo en una conexion.  
			//poder usar distintos driver solo cambiando el string entre las comillas.
			Class.forName(driver); 
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static FactoryConnection getinstancia() //devuelve la unica conexion
	{
		if (FactoryConnection.instancia == null){FactoryConnection.instancia=new FactoryConnection();}
		return FactoryConnection.instancia;
	} 
	
	
	public Connection getConn() throws SQLException, ExcepcionEspecial
	{
		
		try 
		{
			if(conn==null || conn.isClosed()){
			conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password+"&useSSL=false");}
		} 
		catch (SQLException e) 
		{
			throw new ExcepcionEspecial("Error al intentar conectarse a la Base de Datos", Level.ERROR);
		}
		cantConn++;
		return conn;
    }	
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}
}