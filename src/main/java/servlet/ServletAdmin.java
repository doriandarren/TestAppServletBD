package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Admin;
import util.TemplatesHtml;

@SuppressWarnings("serial")
public class ServletAdmin extends HttpServlet {

	DBAdmin dbConnector;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Set response content type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String title = "Resultado de la operación";
		
		String name_admin = request.getParameter("name_admin");
				
		dbConnector = new DBAdmin();
		Admin admin = new Admin();
		admin.setName(name_admin);
		
		dbConnector.createAdmin(admin);
				
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<h2 align = \"center\">Usuario Admin creado exitosamente!</h2>\n" +
				"  <h2 align = \"center\"><b>Nombre :</b> " + 
				admin.getName() + 
				"</h2>\n" + 
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//String show = request.getParameter("show");
		
		
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			String title = "Lista de ADMINS";
			
			dbConnector = new DBAdmin();
			
			Set<Admin> listAdmin = dbConnector.getAllAdmin();
			
			StringBuilder builder = new StringBuilder();

			for (Admin admin : listAdmin) {
				String rows = "<tr> " +
							"<td>" 
								+ admin.getName() +
							"</td>"	+ 
							"<td>" +
							"<a href=\"adminupdate?show=edit&n="+admin.getId()+"\" class=\"btn btn-info\">Editar</a>\r\n" + 
							" <a href=\"admindelete?show=delete&n="+admin.getId()+" \""
									+ "class=\"btn btn-danger\" "
									+ "onclick=\"return confirm('Eliminar este Registro?')\">Eliminar</a>"+
							"</td>"	+
							"</tr>";
				builder.append(rows);
			}
			
			
			
			out.println(TemplatesHtml.head(title) + 
					TemplatesHtml.menu() + 
					"<div class=\"container\">\r\n"+
					"		<div class=\"row\">" + 
					"<h1 align = \"center\">" + title + "</h1>\n" +
					"<h2 align = \"center\">Lista Admin</h2>\n\n" +
					"<table class=\"table\"> " + 
					"<tr>  " + 
					"<th style=\"font-weight:bold;\">NAME</th> " + 
					"<th style=\"font-weight:bold;\"> ACTIONS</th> " +
					"</tr>"+
					"</div>\r\n" + 
					"	</div>"+
					builder.toString() + "</table>"+					
					"      </div>\r\n" + 
					"	</div>" + 
					TemplatesHtml.footer());		
		
	}

	
	
	
}
