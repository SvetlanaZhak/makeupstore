package fi.haagahelia.makeupstore.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Makeup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
		
		@NotNull
	    @Size(min=2, max=50)
	    
private String brand, name, size;
		 @NotNull

		    private Double price;
@ManyToOne
@JsonIgnore
  @JoinColumn(name = "categoryid")
  private Category category;

 public Makeup() {}


public Makeup(String brand, String name, String size, Double price, Category category) {
	super();
	this.brand = brand;
	this.name = name;
	this.size = size;
	this.price = price;
	this.category = category;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getBrand() {
	return brand;
}

public void setBrand(String brand) {
	this.brand = brand;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public  String getSize() {
	return size;
}

public void setSize(String size) {
	this.size = size;
}

public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;

}
@Override
public String toString() {
	if (this.category != null)
		return "Makeup [id=" + id + ", brand=" + brand + ", size=" + size + ", price=" + price + ",  category =" + this.getCategory() + "]";		
	else
		return "Makeup [id=" + id + ", name=" + name + ", size=" + size + ", price=" + price + "]";
}


public float[] getColumnCount() {
	// TODO Auto-generated method stub
	return null;
}


}
