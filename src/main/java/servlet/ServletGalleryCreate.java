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
public class ServletGalleryCreate extends HttpServlet {

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Set response content type
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		String title = "Add Gallery";
		
		
		dbConnector = new DBAdmin();
		 Set<Admin> listAdmin = dbConnector.getAllAdmin();
		 StringBuilder builder = new StringBuilder();
		 		 
		 for(Admin admin: listAdmin){			 
			 String rows = "<option value=\""+admin.getId()+"\">"+admin.getName()+"</option>";
			 builder.append(rows);			 
		 }
		
		
		
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"col-md-12 text-center\">\r\n" + 
		"			<h1>Add Gallery</h1>\r\n" + 
		"		</div>\r\n" + 
		"		<form action=\"/servletgallerycreate\" method=\"post\" class=\"form-horizontal\">\r\n" + 
		"		\r\n" + 
		"			<div class=\"form-group\"> \r\n" + 
		"                <div class=\"col-md-6 col-md-offset-4\">\r\n" +
		"			<label for=\"admin_id\">Select Amin:</label>\r\n" + 
		"			  <select class=\"form-control\" id=\"admin_id\" name=\"admin_id\">\r\n" + 
		builder.toString() +
		"			  </select>\r\n" +
		"				</div>\r\n" + 
		"			</div>\r\n" + 
		"		\r\n" + 
		"			<div class=\"form-group\">\r\n" + 
		"                <label for=\"name_gallery\" class=\"col-md-4 control-label\">Name: </label>\r\n" + 
		"                <div class=\"col-md-6\">\r\n" + 
		"                    <input type=\"text\" id=\"name_gallery\"class=\"form-control\" name=\"name_gallery\" value=\"\" autofocus required>\r\n" + 
		"                </div>\r\n" + 
		"            </div>\r\n" + 
		"			\r\n" + 
		"				<div class=\"form-group\">\r\n" + 
				"                <label for=\"des_gallery\" class=\"col-md-4 control-label\">Description: </label>\r\n" + 
				"                <div class=\"col-md-6\">\r\n" + 
				"                    <input type=\"text\" id=\"des_gallery\"class=\"form-control\" name=\"des_gallery\" value=\"\" required>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" +
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
		String title = "Resultado de la operación";
		dbConnector = new DBAdmin();		
		String name_gallery = request.getParameter("name_gallery");
		String des_gallery = request.getParameter("des_gallery");
		
		int admin_id = Integer.valueOf(request.getParameter("admin_id"));
		
		Admin admin = dbConnector.findAdmin(admin_id);
		
		Gallery gallery = new Gallery();
		gallery.setName(name_gallery);
		gallery.setDescription(des_gallery);
		
		dbConnector.createGallery(admin, gallery);
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<h2 align = \"center\">Gallery creado exitosamente!</h2>\n" +
				"  <h2 align = \"center\"><b>Nombre :</b> " + 
				gallery.getName() + 
				"</h2>\n" + 
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());
		
	}	
	
}
