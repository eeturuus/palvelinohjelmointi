package fi.haagahelia.course.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Date {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long dateid;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "date")
	private List<Booking> books;
	
	public Date() {}
	
	public Date(String name) {
		super();
		this.name = name;
	}
	
	public Long getDateid() {
		return dateid;
	}
	
	public void setDateid(Long dateid) {
		this.dateid = dateid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Booking> getBooks() {
		return books;
	}

	public void setBooks(List<Booking> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Date [dateid=" + dateid + ", name=" + name + "]";
	}
}
