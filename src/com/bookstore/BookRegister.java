package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookRegister
 */
@WebServlet("/BookRegister")
public class BookRegister extends Books {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see Books#Books()
     */
    public BookRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter p=response.getWriter();
			PreparedStatement pre=connection.prepareStatement("insert into books values(?,?,?,?,?,?)");
			pre.setInt(1, ++id);
			pre.setString(2, request.getParameter("name"));
			pre.setString(3, request.getParameter("author"));
			pre.setString(4, request.getParameter("subject"));
			pre.setInt(5, Integer.parseInt(request.getParameter("price")));
			pre.setString(6, request.getParameter("file"));
			
			if(pre.executeUpdate()==1) {
				p.println("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>SignUp</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"	<h4>Book Inserted Successfully!!. Book Id is "+id+"</h4>\r\n"
					+ "<a href=\"adminpage.jsp\">Home</a>"
					+ "	\r\n" + 
					"</body>\r\n" + 
					"</html>");
			}
			else {
				p.println("<!DOCTYPE html>\r\n" + 
						"<html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"ISO-8859-1\">\r\n" + 
						"<title>SignUp</title>\r\n" + 
						"</head>\r\n" + 
						"<body>\r\n" + 
						"	<h4>Book was not Inserted</h4>\r\n"
						+ "<a href=\"BookRegister.jsp\">Please try again</a>"
						+ "	\r\n" + 
						"</body>\r\n" + 
						"</html>");
				id--;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	

}
