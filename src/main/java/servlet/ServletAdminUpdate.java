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
public class ServletAdminUpdate extends HttpServlet{

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String show = request.getParameter("show");
		String n = request.getParameter("n");
		
		// Set response content type
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		String title = "Formulario Update";
		
		int id = Integer.valueOf(n);
		
		dbConnector = new DBAdmin();
		
		Admin admin = dbConnector.findAdmin(id);
		
				
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +				
				"<form action=\"/adminupdate\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+admin.getId()+"\"/>\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"                <label for=\"name_admin\" class=\"col-md-4 control-label\">Name: </label>\r\n" + 
				"                <div class=\"col-md-6\">\r\n" + 
				"                    <input type=\"text\" id=\"name_admin\"class=\"form-control\" name=\"name_admin\" value=\""+admin.getName()+"\" autofocus required>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"			\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"                <div class=\"col-md-6 col-md-offset-4\">\r\n" + 
				"					<input type=\"submit\" class=\"btn btn-primary\" role=\"button\" value=\"Submit\" />\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"		</form>"+			
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set response content type
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		String title = "Resultado de la operación";
		
		String name = request.getParameter("name_admin");
		String id = request.getParameter("id");
		
		
		dbConnector = new DBAdmin();
		Admin admin = new Admin();
		admin.setId(Integer.valueOf(id));
		admin.setName(name);
		
		
		dbConnector.updateAdmin(admin);
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<h2 align = \"center\">Usuario Admin Actualizado exitosamente!</h2>\n" +
				"  <h2 align = \"center\"><b>Nombre :</b> " + 
				admin.getName() + 
				"</h2>\n" + 
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());
		
	}
	
	
}
