package shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders {
	private Long id;
	private Long shopuserId;
	private ReceivingAddress receivingAddress;
	private Date lastTime;
	
	private OrderState state;
	
	private List<OrderItem> items = new ArrayList<>();
	
	
	
	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getShopuserId() {
		return shopuserId;
	}
	public void setShopuserId(Long shopuserId) {
		this.shopuserId = shopuserId;
	}
	public ReceivingAddress getReceivingAddress() {
		return receivingAddress;
	}
	public void setReceivingAddress(ReceivingAddress receivingAddress) {
		this.receivingAddress = receivingAddress;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	
	public int totalCost() {
		int result = 0;
		for (OrderItem item : items) {
            result += item.getCellphone().getPrice() * item.getQuantity();
        }
        return result;
	}
	
	public String stateText() {
		switch (state) {
		case Created:
			return "待支付";
		case Paid:
			return "待发货";
		case Shipped:
			return "已发货";
		case Delivered:
			return "已送达";
		case Commented:
			return "已评论";
		case Canceled:
			return "已取消";
		default:
			return "?" + state;
		}
    }

}
