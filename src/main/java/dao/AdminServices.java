package dao;

import java.util.Set;

import model.Admin;
import model.Gallery;
import model.Item;

public interface AdminServices {

	public Admin findAdmin(int id);	
	public Set<Admin> findAllAdmin();	
	public void insertAdmin(Admin admin);
	
	
	
	public Set<Admin> getAllAdmin();	
	public void createAdmin(Admin admin);	
	public void removeAdmin(Admin admin);	
	public void updateAdmin(Admin admin);
	
	
	public Gallery findGallery(int galleryId);
	
	public Set<Gallery> getGalleries(int adminId);	
	public void createGallery(Admin admin, Gallery gallery);	
	public void removeGallery(Gallery gallery);	
	public void update(Gallery gallery);	
	public Set<Item> getItems(int galleryId);

	
	
	public Item findItem(int itemId);
	public Set<Gallery> getAllGallery();
	
	
	public void createItem(Gallery gallery, Item item);	
	public void removeItem(Item item);	
	public void updateItem(Item item);
	
}
