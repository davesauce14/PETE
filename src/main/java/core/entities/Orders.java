package core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

import rest.vo.OrderVo;

@Entity
public class Orders {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="account_id",updatable = true, insertable = true)
	private Account account;
	
	@JsonIgnore
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="order", cascade={CascadeType.PERSIST})
	private List<OrderItem> orderItems;
	
	@Column(nullable=false)
	private String total;
	
	public Orders() {}
	
	public OrderVo convertToVo(){
		OrderVo orderVo = new OrderVo();
		orderVo.setTotal(this.total);
		orderVo.setItems(this.orderItems);
		orderVo.setAddress(this.account.getAddress());
		orderVo.setPhone(this.account.getPhone());
		orderVo.setFullName(this.account.getFullName());
		return orderVo;
	}
	
	public void setOrderItemToParent(){
		for(OrderItem item : orderItems){
			item.setOrder(this);
		}
	}
	
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public List<OrderItem> getItems() {
		return orderItems;
	}
	public void setItems(List<OrderItem> items) {
		if(orderItems == null){
			orderItems = new ArrayList<OrderItem>();				
		}
		for(OrderItem newItem : items){
			orderItems.add(newItem);
			newItem.setOrder(this);
		}
		
	}
}
