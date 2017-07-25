package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Admin;
import util.TemplatesHtml;

@SuppressWarnings("serial")
public class ServletAdminDelete extends HttpServlet{

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set response content type
				response.setContentType("text/html");
						
								
				PrintWriter out = response.getWriter();
				String title = "Resultado de la operación";
				
				String id = request.getParameter("n");
				
				
				dbConnector = new DBAdmin();
				Admin admin = new Admin();
				admin.setId(Integer.valueOf(id));
				
				dbConnector.removeAdmin(admin);
				
				out.println(TemplatesHtml.head(title) + 
						TemplatesHtml.menu() + 
						"<div class=\"container\">\r\n"+
						"		<div class=\"row\">" + 
						"<h1 align = \"center\">" + title + "</h1>\n" +
						"<h2 align = \"center\">Usuario Admin eliminado exitosamente!</h2>\n" +
						"</div>\r\n" + 
						"	</div>" + 
						TemplatesHtml.footer());
		
		
	}

	
	
}
