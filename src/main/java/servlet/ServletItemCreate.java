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
public class ServletItemCreate extends HttpServlet {

	DBAdmin dbConnector;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// Set response content type
		response.setContentType("text/html");
				
		PrintWriter out = response.getWriter();
		String title = "Add Item";
		
		
		dbConnector = new DBAdmin();
		
		 Set<Gallery> listGallery = dbConnector.getAllGallery();
		 StringBuilder builder = new StringBuilder();
		 		 
		 for(Gallery gallery: listGallery){			 
			 String rows = "<option value=\""+gallery.getId()+"\">"+gallery.getName()+" - "+ gallery.getAdmin().getName() +"</option>";
			 builder.append(rows);			 
		 }
		
		
		
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"col-md-12 text-center\">\r\n" + 
		"			<h1>Add Item</h1>\r\n" + 
		"		</div>\r\n" + 
		"		<form action=\"/itemadd\" method=\"post\" class=\"form-horizontal\">\r\n" + 
		"		\r\n" + 
		"			<div class=\"form-group\"> \r\n" + 
		"                <div class=\"col-md-6 col-md-offset-4\">\r\n" +
		"			<label for=\"admin_id\">Select Amin:</label>\r\n" + 
		"			  <select class=\"form-control\" id=\"gallery_id\" name=\"gallery_id\">\r\n" + 
		builder.toString() +
		"			  </select>\r\n" +
		"				</div>\r\n" + 
		"			</div>\r\n" + 
		"		\r\n" + 
		"			<div class=\"form-group\">\r\n" + 
		"                <label for=\"name_item\" class=\"col-md-4 control-label\">Name: </label>\r\n" + 
		"                <div class=\"col-md-6\">\r\n" + 
		"                    <input type=\"text\" id=\"name_item\"class=\"form-control\" name=\"name_item\" value=\"\" autofocus>\r\n" + 
		"                </div>\r\n" + 
		"            </div>\r\n" + 
		"			\r\n" + 
		"				<div class=\"form-group\">\r\n" + 
				"                <label for=\"des_item\" class=\"col-md-4 control-label\">Description: </label>\r\n" + 
				"                <div class=\"col-md-6\">\r\n" + 
				"                    <input type=\"text\" id=\"des_item\"class=\"form-control\" name=\"des_item\" value=\"\" autofocus required>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" +
				"				<div class=\"form-group\">\r\n" + 
				"                <label for=\"price_item\" class=\"col-md-4 control-label\">Price: </label>\r\n" + 
				"                <div class=\"col-md-6\">\r\n" + 
				"                    <input type=\"text\" id=\"price_item\"class=\"form-control\" name=\"price_item\" value=\"\" required>\r\n" + 
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
		String name_item = request.getParameter("name_item");
		String des_item = request.getParameter("des_item");
		float price_item = Float.valueOf(request.getParameter("price_item"));
				
		int gallery_id = Integer.valueOf(request.getParameter("gallery_id"));
		
		Gallery gallery = dbConnector.findGallery(gallery_id);
		
		Item item = new Item();
		item.setName(name_item);
		item.setDescription(des_item);
		item.setPrice(price_item);
		
		dbConnector.createItem(gallery, item);
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<h2 align = \"center\">Gallery creado exitosamente!</h2>\n" +
				"  <h2 align = \"center\"><b>Nombre :</b> " + 
				item.getName() + 
				"</h2>\n" + 
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());
		
	}	
	
}
