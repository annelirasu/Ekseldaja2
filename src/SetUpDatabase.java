

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class SetUpDatabase
 */
public class SetUpDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			setupDatabase();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	 public void init() throws ServletException{
		 try{
			 Class.forName("org.hsqldb.jdbcDriver");
		 } catch (ClassNotFoundException e){
			 throw new RuntimeException(e);
		 }
	 }

	private void setupDatabase() throws Exception {

		
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:${user.home}//bearDb;shutdown=true");

		Statement stmt = null;
		ResultSet rset = null; // et midagi välja printida, praegu pole vaja

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS bear (id IDENTITY, name VARCHAR(50), weight INT) ");
			stmt.executeUpdate(" INSERT INTO bear VALUES (1, 'bear 1', 3)");
			stmt.executeUpdate(" INSERT INTO bear VALUES (2, 'bear 2', 6)");
			stmt.executeUpdate(" INSERT INTO bear VALUES (3, 'bear 3', 8)");
			stmt.executeUpdate(" INSERT INTO bear VALUES (4, 'bear 4', 7)");
			stmt.executeUpdate(" INSERT INTO bear VALUES (5, 'bear 5', 10)");

		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}

	}

}
