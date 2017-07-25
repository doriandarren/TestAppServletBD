package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Gallery;
import model.Item;
import util.TemplatesHtml;

@SuppressWarnings("serial")
public class ServletItem extends HttpServlet{

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Find Item";

		dbConnector = new DBAdmin();
		Set<Gallery> listGallery = dbConnector.getAllGallery();
		StringBuilder builder = new StringBuilder();

		for (Gallery gallery : listGallery) {
			String rows = "<option value=\"" + gallery.getId() + "\">" + gallery.getName() + "</option>";
			builder.append(rows);
		}
				
		
		
		out.println(TemplatesHtml.head(title) + 
						TemplatesHtml.menu() + 
						"<div class=\"col-md-12 text-center\">\r\n" + 
				"			<h1>"+title+"</h1>\r\n" + 
				"		</div>\r\n" + 
				"		<form action=\"/servletitem\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"		\r\n" + 
				"			<div class=\"form-group\"> \r\n" + 
				"                <div class=\"col-md-6 col-md-offset-4\">\r\n" +
				"			<label for=\"admin_id\">Select Gallery:</label>\r\n" + 
				"			  <select class=\"form-control\" id=\"gallery_id\" name=\"gallery_id\">\r\n" + 
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
		String title = "Find Item";
		
		int galleryId = Integer.valueOf(request.getParameter("gallery_id"));
		
		
		Set<Item> listItem = dbConnector.getItems(galleryId);
		
		StringBuilder builder = new StringBuilder();

		for (Item item : listItem) {
			String rows = "<tr> " +
						"<td>" +
							item.getName() +
						"</td>"	+ 
						"<td>"+ 
							item.getDescription() +
						"</td>"	+
						"<td>"+ 
							item.getPrice() +
						"</td>"	+
						"<td>" +
						"<a href=\"itemupdate?show=edit&n="+item.getId()+"\" class=\"btn btn-info\">Editar</a>\r\n" + 
						" <a href=\"itemdelete?show=delete&n="+item.getId()+" \""
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
				"<h2 align = \"center\">Lista Item</h2>\n\n" +
				"<table class=\"table\"> " + 
				"<tr>  " + 
				"<th style=\"font-weight:bold;\">NAME</th> " + 
				"<th style=\"font-weight:bold;\">DESCRIPCION</th> " +
				"<th style=\"font-weight:bold;\">PRICE</th> " +
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
