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
import model.Gallery;
import util.TemplatesHtml;

@SuppressWarnings("serial")
public class ServletGallery extends HttpServlet{

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Find Gallery";

		dbConnector = new DBAdmin();
		Set<Admin> listAdmin = dbConnector.getAllAdmin();
		StringBuilder builder = new StringBuilder();

		for (Admin admin : listAdmin) {
			String rows = "<option value=\"" + admin.getId() + "\">" + admin.getName() + "</option>";
			builder.append(rows);
		}
				
		
		
		out.println(TemplatesHtml.head(title) + 
						TemplatesHtml.menu() + 
						"<div class=\"col-md-12 text-center\">\r\n" + 
				"			<h1>"+title+"</h1>\r\n" + 
				"		</div>\r\n" + 
				"		<form action=\"/servletgallery\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"		\r\n" + 
				"			<div class=\"form-group\"> \r\n" + 
				"                <div class=\"col-md-6 col-md-offset-4\">\r\n" +
				"			<label for=\"admin_id\">Select Amin:</label>\r\n" + 
				"			  <select class=\"form-control\" id=\"admin_id\" name=\"admin_id\">\r\n" + 
				builder.toString() +
				"			  </select>\r\n" +
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"                <div class=\"col-md-6 col-md-offset-4\">\r\n" + 
				"					<input type=\"submit\" class=\"btn btn-primary\" role=\"button\" value=\"Submit\" />\r\n" + 
				"				</div>\r\n" + 
				"			</div>\r\n" + 
				"		</form>"+
				TemplatesHtml.footer());		
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Find Gallery";
		
		int adminId = Integer.valueOf(request.getParameter("admin_id"));
		
		
		Set<Gallery> listGalley = dbConnector.getGalleries(adminId);
		
		StringBuilder builder = new StringBuilder();

		for (Gallery gallery : listGalley) {
			String rows = "<tr> " +
						"<td>" 
							+ gallery.getName() +
						"</td>"	+ 
						"<td>" +
						"<a href=\"galleryupdate?show=edit&n="+gallery.getId()+"\" class=\"btn btn-info\">Editar</a>\r\n" + 
						" <a href=\"gallerydelete?show=delete&n="+gallery.getId()+" \""
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
				"<h2 align = \"center\">Lista Gallery</h2>\n\n" +
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
