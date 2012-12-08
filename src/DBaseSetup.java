import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

public class DBaseSetup extends HttpServlet  {
	
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

		
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:${user.home}//piirivalveDb;shutdown=true");

		Statement stmt = null;
		ResultSet rset = null; // et midagi välja printida, praegu pole vaja

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RIIGI_ADMIN_YKSUSE_LIIK (riigi_admin_yksuse_liik_id INTEGER IDENTITY PRIMARY KEY NULL, kood VARCHAR(10) NOT NULL ) ");
					//nimetus VARCHAR(100) NOT NULL,kommentaar LONGVARCHAR NOT NULL,avaja VARCHAR(32) NOT NULL,avatud DATE NOT NULL,muutja VARCHAR(32) NOT NULL,muudetud DATE NOT NULL,sulgeja VARCHAR(32) NULL,suletud DATE NOT NULL ) ");
			
			stmt.executeUpdate(" INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (1, 'mk')");
					//'maakond','suurim võimalik administratiivüksus Eesti vabariigis, sisaldab linnu ja valdasid','An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31' )");
				
		
		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}

	}

}
