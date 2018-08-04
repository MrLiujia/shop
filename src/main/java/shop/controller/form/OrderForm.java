package shop.controller.form;

import javax.validation.constraints.NotNull;

public class OrderForm {
	@NotNull(message = "请选择收货地址")
	private Long ReceivingAddressId;

	public Long getReceivingAddressId() {
		return ReceivingAddressId;
	}

	public void setReceivingAddressId(Long receivingAddressId) {
		ReceivingAddressId = receivingAddressId;
	}
	
	
	

}
