package shop.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReceivingAddress {
	private Long id;

	@Size(min = 1, max = 64, message = "1~64字")
	private String consigneeName;
    @Pattern(regexp = "1[0-9]{10}", message = "请输入11位手机号")
	private String cellphoneNumber;
    @Size(min = 3, max = 128, message = "3~128字")
	private String detailedAddress;
	
	private Shopuser shopuser; 
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getCellphoneNumber() {
		return cellphoneNumber;
	}
	public void setCellphoneNumber(String cellphoneNumber) {
		this.cellphoneNumber = cellphoneNumber;
	}
	public String getDetailedAddress() {
		return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}
	public Shopuser getShopuser() {
		return shopuser;
	}
	public void setShopuser(Shopuser shopuser) {
		this.shopuser = shopuser;
	} 
	  
}	
	
