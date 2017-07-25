package test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import dao.DBAdmin;
import model.Admin;
import model.Gallery;


public class TestAdmin {
	
	DBAdmin dbConnector; 

	@Before
	public void init(){
		dbConnector =  new DBAdmin(); 	
		dbConnector.connect();
		dbConnector.deleteAll(Admin.class);
		dbConnector.close();
	}
	
	@Test(expected=RuntimeException.class)
	public void testInsertAdminNull() {
		dbConnector.createAdmin(null);	
	}
	
	@Test
	public void testCreateAdmin() {		
		
		Admin admin = getMockAdmin("Administrador");	
		
		dbConnector.createAdmin(admin);	
		
		dbConnector.connect();
			Admin adminRecovered = dbConnector.find(Admin.class, admin.getId());
		dbConnector.close();		
		
		Assert.assertNotNull(adminRecovered);
		Assert.assertEquals(true,adminRecovered.getId()>0);	
		Assert.assertEquals("Administrador",adminRecovered.getName());	
	}
	
	
	
	@Test
	public void testRemoveAdmin() {		
		Admin admin = getMockAdmin("Administrador");		
		
		dbConnector.connect();
			dbConnector.getEntityManager().getTransaction().begin();
			dbConnector.getEntityManager().persist(admin);
			dbConnector.getEntityManager().getTransaction().commit();
		dbConnector.close();
				
		dbConnector.removeAdmin(admin);
				
		dbConnector.connect();
		Admin adminRecovered = dbConnector.find(Admin.class, admin.getId());
		dbConnector.close();

		Assert.assertNull(adminRecovered);
	}
	
	
	
	
	
	@Test
	public void testUpdateAdmin() {		
		
		Admin admin = getMockAdmin("Administrador");
		
		dbConnector.connect();
			dbConnector.getEntityManager().getTransaction().begin();
			dbConnector.getEntityManager().persist(admin);
			dbConnector.getEntityManager().getTransaction().commit();
		dbConnector.close();
		
		dbConnector.connect();
		Admin adminUpdate= dbConnector.find(Admin.class, admin.getId());
		dbConnector.close();
				
		adminUpdate.setName("AdminUpdate");
		
		dbConnector.updateAdmin(adminUpdate);
				
		dbConnector.connect();
			Admin adminRecovered = dbConnector.find(Admin.class, admin.getId());
		dbConnector.close();		
		
		Assert.assertNotNull(adminRecovered);
		Assert.assertEquals(true,adminRecovered.getId()>0);	
		Assert.assertEquals("AdminUpdate",adminRecovered.getName());
		
	}
	
	@Test
	public void testAllAdmin() {	
		Admin admin1 = getMockAdmin("Admin1");		
		Admin admin2 = getMockAdmin("Admin2");
		Admin admin3 = getMockAdmin("Admin3");
		
		dbConnector.connect();
			dbConnector.getEntityManager().getTransaction().begin();
			dbConnector.getEntityManager().persist(admin1);
			dbConnector.getEntityManager().persist(admin2);
			dbConnector.getEntityManager().persist(admin3);
			dbConnector.getEntityManager().getTransaction().commit();
		dbConnector.close();
		
		
		HashSet<Admin> recoveredAllAdmin = (HashSet<Admin>) dbConnector.getAllAdmin();
		
		Assert.assertEquals(3, recoveredAllAdmin.size());
		
		Assert.assertNotNull(findByNameAdmin(recoveredAllAdmin,"Admin1"));
		
	}
	
	
	
	@Test
	public void testGetGalleries() {	
		Admin admin = getMockAdmin("Administrador");
		
		Gallery gallery1 = getMockGallery("Galeria 1", "Comentario Galeria 1");
		Gallery gallery2 = getMockGallery("Galeria 2", "Comentario Galeria 2");
		
		
		dbConnector.connect();
			dbConnector.getEntityManager().getTransaction().begin();
			
			dbConnector.getEntityManager().persist(admin);
			
			gallery1.setAdmin(admin);
			admin.getGalleries().add(gallery1);
			
			gallery2.setAdmin(admin);
			admin.getGalleries().add(gallery2);
			
			dbConnector.getEntityManager().getTransaction().commit();
		dbConnector.close();
		
		
		dbConnector.connect();
			Admin adminRecovered = dbConnector.find(Admin.class, admin.getId());
		dbConnector.close();
		
		
		Set<Gallery> galleriasAdmin = dbConnector.getGalleries(adminRecovered.getId());
		
		Assert.assertEquals(2, galleriasAdmin.size());
		
		Assert.assertNotNull(findGallery(galleriasAdmin, "Galeria 1"));
		Assert.assertNotNull(findGallery(galleriasAdmin, "Galeria 2"));
		
		
	}
	
	
	private static Gallery findGallery(Set<Gallery> set, String nameMascota){ 
		for(Gallery mascota: set){
				if(mascota.getName().equals(nameMascota))
				  return mascota; 
		}
		return null;
	}
	
	
	public Admin getMockAdmin(String name){
		Admin admin = new Admin();
		admin.setName(name);
		return admin;
	}
	
	public Gallery getMockGallery(String name, String galeria){
		Gallery gallery = new Gallery();
		gallery.setName(name);
		gallery.setDescription(galeria);
		return gallery;
	}
	
	
	private static Admin findByNameAdmin(HashSet<Admin> set, String nameAdmin){ 
		for(Admin admin: set){
				if(admin.getName().equals(nameAdmin))
				  return admin; 
		}
		return null;
	}
	
}
