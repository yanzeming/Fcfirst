package cn.itcast.shop.categorysecond;
import java.util.Set;

import cn.itcast.shop.category.*;
import cn.itcast.shop.product.*;
public class CategorySecond {
	private Integer csid;
	private String csname;
	private Category category;
	private Set<Product> products;
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
}
