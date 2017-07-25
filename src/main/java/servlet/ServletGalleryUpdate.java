package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Gallery;
import util.TemplatesHtml;

@SuppressWarnings("serial")
public class ServletGalleryUpdate extends HttpServlet {

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String n = request.getParameter("n");
		
		// Set response content type
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		String title = "Formulario Update";
		
		int id = Integer.valueOf(n);
		
		dbConnector = new DBAdmin();
		
		Gallery galery = dbConnector.findGallery(id);
		
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +				
				"<form action=\"/galleryupdate\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+galery.getId()+"\"/>\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"                <label for=\"name_gallery\" class=\"col-md-4 control-label\">Name: </label>\r\n" + 
				"                <div class=\"col-md-6\">\r\n" + 
				"                    <input type=\"text\" id=\"name_gallery\"class=\"form-control\" name=\"name_gallery\" value=\""+galery.getName()+"\" autofocus>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"			\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
						"                <label for=\"des_gallery\" class=\"col-md-4 control-label\">Description: </label>\r\n" + 
						"                <div class=\"col-md-6\">\r\n" + 
						"                    <input type=\"text\" id=\"des_gallery\"class=\"form-control\" name=\"des_gallery\" value=\""+galery.getDescription()+"\" autofocus required>\r\n" + 
						"                </div>\r\n" + 
						"            </div>\r\n" +
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

		String name_galley = request.getParameter("name_gallery");
		String des_gallery = request.getParameter("des_gallery");
		String id = request.getParameter("id");

		dbConnector = new DBAdmin();
		Gallery gallery = new Gallery();
				
		gallery.setId(Integer.valueOf(id));
		gallery.setName(name_galley);
		gallery.setDescription(des_gallery);
		
		dbConnector.update(gallery);
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<h2 align = \"center\">Usuario Admin Actualizado exitosamente!</h2>\n" +
				"  <h2 align = \"center\"><b>Nombre :</b> " + 
				gallery.getName() + 
				"</h2>\n" + 
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());
		
	}

	
	
}
