package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Emp;

public class EmpDao {
	static String url="jdbc:mysql://localhost:3306/employeeManagement";
    static String name="root";
    static String pass="abc123";
    static String driver="com.mysql.cj.jdbc.Driver";
    
    Connection con;
    private static Connection getconnect() throws ClassNotFoundException, SQLException{
    Class.forName(driver);
    Connection con=DriverManager.getConnection(url, name, pass);
    return con;
    }
    
   
  	public List<Emp> getPaginatedEmployees(int start, int total) throws ClassNotFoundException, SQLException {
  	    List<Emp> le = new ArrayList<>();
  	    String sql = "SELECT * FROM emp LIMIT ?, ?";  // Correct SQL for pagination
  	    con = getconnect(); // Ensure this method establishes a valid DB connection
  	    PreparedStatement ps = con.prepareStatement(sql);
  	    
  	    // Set parameters for the LIMIT clause
  	    ps.setInt(1, start);  // Starting record (offset)
  	    ps.setInt(2, total);  // Number of records to fetch

  	    ResultSet rs = ps.executeQuery();
  	    
  	    while (rs.next()) {
  	        Emp e = new Emp(rs.getInt(1), rs.getString(2), rs.getFloat(3));
  	        le.add(e);
  	    }
  	    return le;
  	}



}
