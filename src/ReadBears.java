

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;

/**
 * Servlet implementation class ReadBears
 */
public class ReadBears extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//siin moodustatakse karude nimekiri showBears klassi abil
		List<Bear> bears=null;
		try {
			bears=showBears();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	
	request.setAttribute("bears", bears);//karud lähevad requesti külge
	request.getRequestDispatcher("showBears.jsp").forward(request, response);
	}
	
	
	@Override
	 public void init() throws ServletException{
		//uurime, kas andmebaasi moodustamise draiver on üldse olemas
		 try{
			 Class.forName("org.hsqldb.jdbcDriver");
		 } catch (ClassNotFoundException e){
			 throw new RuntimeException(e);
		 }
	 }

	//see ongi DAO?
	private List<Bear> showBears() throws SQLException {

		List<Bear> bears= new ArrayList<Bear>();
		
		Connection conn = DriverManager.getConnection("jdbc:hsqldb:file:${user.home}//bearDb;shutdown=true");

		Statement stmt = null;
		ResultSet rset = null; // et midagi välja printida

		try {
			stmt = conn.createStatement();
			rset=stmt.executeQuery("select * from bears;");
			
			while (rset.next()){
				Bear bear=new Bear();
				bear.setId(rset.getInt(1));
				bear.setName(rset.getString(2));
				bear.setWeight(rset.getInt(3));//numbrid on siin columnindexid
				
				bears.add(bear);
			}
			return bears;

		} finally {
			DbUtils.closeQuietly(rset);
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(conn);
		}

	}
	}


	
	


