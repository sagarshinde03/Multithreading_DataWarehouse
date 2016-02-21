/**
 * 
 */

/**
 * @author Sagar Shinde
 *
 */

public class Product{
	
	String productId;
	int quantity;
	String location;	// stores the location of the product in a warehouse
	
	Product(String productId, int quantity, String location){
		this.productId=productId;
		this.quantity=quantity;
		this.location=location;
	}
	
	int updateQuantityAfterPick(int amountToPick){
		this.quantity=this.quantity-amountToPick;
		return this.quantity;
	}
	
	int updateQuantityAfterRestock(int amountToRestock){
		this.quantity=this.quantity+amountToRestock;
		return this.quantity;
	}
	
	// to change location of a product within warehouse
	void changeLocationWithinWarehouse(String newLocation){
		this.location=newLocation;
	}
}
class PickingResult{
	String productId;
	int quantity;
	String location;
	PickingResult(String productId, int quantity, String location){
		this.productId=productId;
		this.quantity=quantity;
		this.location=location;
	}
}
class RestockingResult{
	String productId;
	int quantity;
	String location;
	RestockingResult(String productId, int quantity, String location){
		this.productId=productId;
		this.quantity=quantity;
		this.location=location;
	}
}