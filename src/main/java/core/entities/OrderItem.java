package core.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class OrderItem {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(nullable=false)
	private Long id;
	
	private String name;
	
	@ManyToOne(cascade={CascadeType.ALL}) 
	@JoinColumn(name="order_id",updatable = true, insertable = true)
	private Orders order;
	
	private String comments;
	
	private String price;
	
	private String size;
	
	@Column
	@ElementCollection(targetClass=String.class,fetch = FetchType.EAGER)
	private List<String> toppings;
	
	public List<String> getToppings() {
		return toppings;
	}

	public void setTopping(List<String> toppings) {
		this.toppings = toppings;
	}

	public OrderItem() {}
	
	@JsonIgnore
	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
