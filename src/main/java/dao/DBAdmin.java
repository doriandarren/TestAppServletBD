package dao;

import java.util.HashSet;
import java.util.Set;

import model.Admin;
import model.Gallery;
import model.Item;


public class DBAdmin extends DBManager implements AdminServices {
	
	DBManager admin;
	
	
	public Admin findAdminSimulation(int id) {		
		return getMockAdmin( (int)(Math.random()*10000), "Pedro");
	}
	
	

	@Override
	public Set<Admin> findAllAdmin() {
		int size = (int) (Math.random()*100);
		HashSet<Admin> list = new HashSet<>(size);
		
		for(int i=0; i<size; i++)
			list.add(getMockAdmin(i,"NoName" + i));		
		return list;
	}

	
	private Admin getMockAdmin(int i, String name) {
		Admin admin = new Admin();
		admin.setName(name);
		return admin;
	}
	
	@Override
	public Admin findAdmin(int id) {	
		connect();
		entitymanager.getTransaction().begin();
			Admin admin = entitymanager.find(Admin.class,id);
		entitymanager.getTransaction().commit();
		close();
		return admin;
	}
	
	
	@Override
	public void insertAdmin(Admin admin) {
		// La persona no tiene que ser NULL
		if (admin == null)
			throw new RuntimeException("Error: El admin no puede ser NULL");
		
		connect();
		entitymanager.getTransaction().begin();
			if (admin.getId() > 0) {
				Admin ownerSearch = entitymanager.find(Admin.class, admin.getId());
				getEntityManager().persist(ownerSearch);				
			}else{
				getEntityManager().persist(admin);
			}
				
		entitymanager.getTransaction().commit();
		close();
	}
	
	@Override
	public Set<Admin> getAllAdmin() {
		HashSet<Admin> list =null;			
		connect();		
			list = new HashSet<Admin>(selectAll(Admin.class));			
		close();	
		return list;
	}

	@Override
	public void createAdmin(Admin admin) {
		// La persona no tiene que ser NULL
		if (admin == null)
			throw new RuntimeException("Error: El Admin no puede ser NULL");

		if (admin.getId()!= 0)
			throw new RuntimeException("Error: El Admin ya existe");
		
		connect();
		entitymanager.getTransaction().begin();
		if(admin.getId()==0) {
			getEntityManager().persist(admin);	
		}			
		entitymanager.getTransaction().commit();
		close();

	}

	@Override
	public void removeAdmin(Admin admin) {
		if (admin == null)
			throw new RuntimeException("Error: El Admin no puede ser NULL.");
		
		connect();		
		entitymanager.getTransaction().begin();
			Admin adminFind = entitymanager.find(Admin.class, admin.getId());
			entitymanager.remove(adminFind);		
		entitymanager.getTransaction().commit();		
		close();
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		if (admin == null)
			throw new RuntimeException("Error: El Admin no puede ser NULL.");
		connect();		
		entitymanager.getTransaction().begin();		
			Admin adminFind = entitymanager.find(Admin.class, admin.getId());
			adminFind.setName(admin.getName());			
		entitymanager.getTransaction().commit();
		close();
		
	}

	@Override
	public Set<Gallery> getGalleries(int adminId) {
		Set<Gallery> list = null;
		connect();		
		entitymanager.getTransaction().begin();		
			Admin adminFind = entitymanager.find(Admin.class, adminId);		
			list = new HashSet<Gallery>(adminFind.getGalleries());	
		entitymanager.getTransaction().commit();
		close();		
		return list;
	}

	
	@Override
	public Gallery findGallery(int galleryId) {
		connect();
		entitymanager.getTransaction().begin();
			Gallery gallery = entitymanager.find(Gallery.class,galleryId);
		entitymanager.getTransaction().commit();
		close();
		return gallery;
	}
	
		
	@Override
	public void createGallery(Admin admin, Gallery gallery) {
		if (admin == null)
			throw new RuntimeException("Error: El Admin no puede ser NULL.");
		
		if(gallery!=null && gallery.getId()!=0)
			throw new RuntimeException("Error: la Gallery tiene que tener un ID diferente de cero."
					+ " Verfique la gallery, ya existe un registro");
		
		connect();
		entitymanager.getTransaction().begin();				
			Admin adminFind = entitymanager.find(Admin.class, admin.getId());			
			getEntityManager().persist(gallery);	
			gallery.setAdmin(adminFind);
			adminFind.getGalleries().add(gallery);
		entitymanager.getTransaction().commit();
		close();
	}

	@Override
	public void removeGallery(Gallery gallery) {
		if (gallery == null)
			throw new RuntimeException("Error: La gallery no puede ser NULL.");
		
		connect();		
		entitymanager.getTransaction().begin();
			Gallery galleryFind = entitymanager.find(Gallery.class, gallery.getId());
			
			Admin admin = galleryFind.getAdmin();
			
			admin.getGalleries().remove(galleryFind);
			
			entitymanager.remove(galleryFind);
			
		entitymanager.getTransaction().commit();
		close();
		
		
	}

	@Override
	public void update(Gallery gallery) {
		if (gallery == null)
			throw new RuntimeException("Error: La Gallery no puede ser NULL.");
		
		connect();		
		entitymanager.getTransaction().begin();
			Gallery galleryFind = entitymanager.find(Gallery.class, gallery.getId());						
			galleryFind.setName(gallery.getName());
			galleryFind.setDescription(gallery.getDescription());			
		entitymanager.getTransaction().commit();
		close();
		
	}

	
	@Override
	public Item findItem(int itemId) {
		connect();
		entitymanager.getTransaction().begin();
		Item gallery = entitymanager.find(Item.class,itemId);
		entitymanager.getTransaction().commit();
		close();
		return gallery;
	}
	
	
	
	
	
	
	@Override
	public Set<Item> getItems(int galleryId) {
		Set<Item> list = null; 
		connect();		
		entitymanager.getTransaction().begin();
			Gallery galleryFind = entitymanager.find(Gallery.class, galleryId);				
			list = new HashSet<Item>(galleryFind.getItems());	
		entitymanager.getTransaction().commit();
		close();		
		return list;
	}



	@Override
	public Set<Gallery> getAllGallery() {
		Set<Gallery> list =null;			
		connect();		
			list = new HashSet<Gallery>(selectAll(Gallery.class));			
		close();	
		return list;
	}



	@Override
	public void createItem(Gallery gallery, Item item) {
		if (gallery == null)
			throw new RuntimeException("Error: La Gallery no puede ser NULL.");
		
		if(item!=null && item.getId()!=0)
			throw new RuntimeException("Error: El Item tiene que tener un ID diferente de cero."
					+ " Verfique el Item, ya existe un registro");
		connect();
		entitymanager.getTransaction().begin();				
			Gallery galleryFind = entitymanager.find(Gallery.class, gallery.getId());			
			getEntityManager().persist(item);				
			item.setGallery(galleryFind);
			galleryFind.getItems().add(item);
		entitymanager.getTransaction().commit();
		close();
		
		
	}



	@Override
	public void removeItem(Item item) {
		if (item == null)
			throw new RuntimeException("Error: El Item no puede ser NULL.");
		
		connect();		
		entitymanager.getTransaction().begin();
			Item itemFind = entitymanager.find(Item.class, item.getId());			
			Gallery gallery = itemFind.getGallery();			
			gallery.getItems().remove(itemFind);			
			entitymanager.remove(itemFind);			
		entitymanager.getTransaction().commit();
		close();		
	}



	@Override
	public void updateItem(Item item) {
		if (item == null)
			throw new RuntimeException("Error: El Item no puede ser NULL.");
		
		connect();		
		entitymanager.getTransaction().begin();
			Item itemFind = entitymanager.find(Item.class, item.getId());				
			itemFind.setName(item.getName());
			itemFind.setDescription(item.getDescription());
			itemFind.setPrice(item.getPrice());		
		entitymanager.getTransaction().commit();
		close();
		
	}



	
	
	
}
