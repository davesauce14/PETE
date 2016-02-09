package core.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class AppetizerSize {
	
	@Id
	@Column(nullable=false)
	private String size;
	
	private String price;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name="appetizer_id",updatable = true, insertable = true)
	private Appetizer appetizer;
	
	@JsonIgnore
	public Appetizer getAppitizer() {
		return appetizer;
	}
	public void setAppitizer(Appetizer appetizer) {
		this.appetizer = appetizer;
	}
	public AppetizerSize() {}
	public AppetizerSize(String size, String price) {
		this.size = size;
		this.price = price;
	}

	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}


}
