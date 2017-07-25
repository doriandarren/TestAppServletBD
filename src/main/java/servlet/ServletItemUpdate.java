package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DBAdmin;
import model.Item;
import util.TemplatesHtml;

@SuppressWarnings("serial")
public class ServletItemUpdate extends HttpServlet{

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
		
		Item item = dbConnector.findItem(id);
		
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +				
				"<form action=\"/itemupdate\" method=\"post\" class=\"form-horizontal\">\r\n" + 
				"		<input type=\"hidden\" name=\"id\" value=\""+item.getId()+"\"/>\r\n" + 
				"			<div class=\"form-group\">\r\n" + 
				"                <label for=\"name_item\" class=\"col-md-4 control-label\">Name: </label>\r\n" + 
				"                <div class=\"col-md-6\">\r\n" + 
				"                    <input type=\"text\" id=\"name_item\"class=\"form-control\" name=\"name_item\" value=\""+item.getName()+"\" autofocus>\r\n" + 
				"                </div>\r\n" + 
				"            </div>\r\n" + 
				"			\r\n" + 
				"				<div class=\"form-group\">\r\n" + 
						"                <label for=\"des_item\" class=\"col-md-4 control-label\">Description: </label>\r\n" + 
						"                <div class=\"col-md-6\">\r\n" + 
						"                    <input type=\"text\" id=\"des_item\"class=\"form-control\" name=\"des_item\" value=\""+item.getDescription()+"\" autofocus required>\r\n" + 
						"                </div>\r\n" + 
						"            </div>\r\n" +
						"				<div class=\"form-group\">\r\n" + 
						"                <label for=\"price_item\" class=\"col-md-4 control-label\">Price: </label>\r\n" + 
						"                <div class=\"col-md-6\">\r\n" + 
						"                    <input type=\"text\" id=\"price_item\"class=\"form-control\" name=\"price_item\" value=\""+ item.getPrice() +"\" required>\r\n" + 
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

		String name_item = request.getParameter("name_item");
		String des_item = request.getParameter("des_item");
		String price_item = request.getParameter("price_item");
		String id = request.getParameter("id");

		dbConnector = new DBAdmin();
		Item item = new Item();
				
		item.setId(Integer.valueOf(id));
		item.setName(name_item);
		item.setDescription(des_item);
		item.setPrice(Float.valueOf(price_item));
		
		dbConnector.updateItem(item);
		
		out.println(TemplatesHtml.head(title) + 
				TemplatesHtml.menu() + 
				"<div class=\"container\">\r\n"+
				"		<div class=\"row\">" + 
				"<h1 align = \"center\">" + title + "</h1>\n" +
				"<h2 align = \"center\">Usuario Admin Actualizado exitosamente!</h2>\n" +
				"  <h2 align = \"center\"><b>Nombre :</b> " + 
				item.getName() + 
				"</h2>\n" + 
				"</div>\r\n" + 
				"	</div>" + 
				TemplatesHtml.footer());
		
	}
	
	
}
