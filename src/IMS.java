/**
 * 
 */

/**
 * @author Sagar Shinde
 *
 */
import java.util.HashSet;
import java.util.Set;

public class IMS implements InventoryManagementSystem{
	// IMS stands for a warehouse
	// This hashset stores all the products that are available in a warehouse
	HashSet<Product> productList=new HashSet<Product>();
	
	IMS(){
	}
	
	IMS(Set<Product> products){	// to create a warehouse with list of products
		this.productList.addAll(products);
	}
	
	void addNewProductToWarehouse(Product p){
		this.productList.add(p);
	}
	
	Product getProductFromProductId(String pId) throws NoProductWithSuchIDException{
		for(Product prd:productList){
			if(prd.productId.equals(pId)){
				return prd;
			}
		}
		throw new NoProductWithSuchIDException("Wrong Product ID");
	}
	
	@Override
	public PickingResult pickProduct(String productId, int amountToPick) {
		try{
			Product prod=getProductFromProductId(productId);	// to get the product from product id
			synchronized(prod){	// synchronized on prod object because any other thread should not make operations on same object
				int newQuantity=prod.updateQuantityAfterPick(amountToPick);
				PickingResult pr=new PickingResult(productId, newQuantity, prod.location);
				return pr;
			}
		}catch(NoProductWithSuchIDException ex){
			System.out.println("Please verify the product id entered!");
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public RestockingResult restockProduct(String productId, int amountToRestock) {
		try{
			Product prod=getProductFromProductId(productId);	// to get the product from product id
			synchronized(prod){	// synchronized on prod object because any other thread should not make operations on same object
				int newQuantity=prod.updateQuantityAfterRestock(amountToRestock);
				RestockingResult rr=new RestockingResult(productId, newQuantity, prod.location);
				return rr;
			}
		}catch(NoProductWithSuchIDException ex){
			System.out.println("Please verify the product id entered!");
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
}

/*
 * This is a user defined exception which is thrown when we a user 
 * enters a product is not present in the warehouse
 * */
class NoProductWithSuchIDException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	NoProductWithSuchIDException(String s){
		super(s);
	}
}