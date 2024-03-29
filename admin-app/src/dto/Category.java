package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable{
	private Integer id;
	private String name;
	private List<String> attributes;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Category() {
		attributes = new ArrayList<String>();
	}
	
	public Category(Integer id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	
	public Category(Integer id, String name, List<String> attributes) {
		super();
		this.id = id;
		this.name = name;
		this.attributes = attributes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String buildAttributes() {
		StringBuilder sb = new StringBuilder();
		for (String a : attributes) {
			sb.append(a);
			sb.append(", ");
		}
		return sb.substring(0, sb.length() - 2);
	}
}
