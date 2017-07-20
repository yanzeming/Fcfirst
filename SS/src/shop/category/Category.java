package cn.itcast.shop.category;
import cn.itcast.shop.categorysecond.*;
import java.util.*;
public class Category {
	private Integer cid;
	private String cname;
	private Set<CategorySecond> categorySeconds=new HashSet();
	
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
}
