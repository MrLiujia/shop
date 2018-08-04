package shop.model;

public class ShoppingCartItem {
	private Cellphone cellphone;
	private int quantity;
	private Shopuser shopuser;
	
	
	public Shopuser getShopuser() {
		return shopuser;
	}
	public void setShopuser(Shopuser shopuser) {
		this.shopuser = shopuser;
	}
	public Cellphone getCellphone() {
		return cellphone;
	}
	public void setCellphone(Cellphone cellphone) {
		this.cellphone = cellphone;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
