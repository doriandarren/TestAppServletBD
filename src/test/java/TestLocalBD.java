import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;



public class TestLocalBD {

	 DBAdmin dbConnector;
	
	@Before
	public void init(){
		dbConnector =   new DBAdmin(); 	
		dbConnector.connect();
		dbConnector.deleteAll(Admin.class);
		dbConnector.close();
	}
	
	@Test
	public void testDBConection(){	
		dbConnector.connect();
		 	Assert.assertNotNull(dbConnector.getEntityManager()); 
		 	dbConnector.close();
	}
	
	
	@Test
	public void testInsert(){
		
		
		Admin admin = getMockAdmin("Administrador");		
		
		//Inserta Mascota 1
		dbConnector.insertAdmin(admin);		
		
		//Busca la mascota		
		dbConnector.connect();
		Admin adminRecovered = dbConnector.find(Admin.class, admin.getId());
		dbConnector.close();		
		
		Assert.assertEquals(true,adminRecovered.getId()>0);		
		Assert.assertEquals("Administrador",adminRecovered.getName());	
		
		
				
	}

	private Admin getMockAdmin(String name) {
		Admin admin = new Admin();
		admin.setName(name);
		return admin;
	}

	
	
	
	
	
}
