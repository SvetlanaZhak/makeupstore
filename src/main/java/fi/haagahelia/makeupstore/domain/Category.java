package fi.haagahelia.makeupstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryid;
	private String categoryname;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Makeup> makeups;

	public Category() {}
	
	public Category(String categoryname) {
		super();
		this.categoryname = categoryname;
	}
	
	public Long getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getCategoryname() {
		return categoryname;
	}
	
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public List<Makeup> getMakeups() {
		return makeups;
	}

	public void setMakeups(List<Makeup> makeups) {
		this.makeups = makeups;
	}

	@Override
	public String toString() {
		return "Category [categoryid=" + categoryid + ", categoryname=" + categoryname + "]";
	}
}