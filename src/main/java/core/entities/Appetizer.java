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
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
public class Appetizer {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=true)
	private String price;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="appetizer", cascade={CascadeType.PERSIST})
	private List<AppetizerSize> appitizerSizes;
	
	public Appetizer() {}
	public Appetizer(String name, String price) {
		this.name = name;
		this.price = price;
	}
	public Appetizer(String name, List<AppetizerSize> appitizerSizes) {
		this.name = name;
		this.setAppitizerSizes(appitizerSizes);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public List<AppetizerSize> getAppitizerSizes() {
		return appitizerSizes;
	}
	public void setAppitizerSizes(List<AppetizerSize> appySizes) {
		if(appitizerSizes == null){
			appitizerSizes = new ArrayList<AppetizerSize>();
		}
		for(AppetizerSize appy : appySizes){
			appitizerSizes.add(appy);
			appy.setAppitizer(this);
		}
		//this.appitizerSizes = appitizerSizes;
	}
	public void addAppetizerSize(AppetizerSize appetizer){
		if(appitizerSizes == null){
			appitizerSizes = new ArrayList<AppetizerSize>();
		}
		appitizerSizes.add(appetizer);
		appetizer.setAppitizer(this);
	}
	
}
