package cn.itcast.shop.product;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.Utils.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.shop.category.*;
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product model=new Product();
	private Integer csid;
	private Integer cid;
	private ProductService productService;
	private CategoryService categoryService;
	private List<Category> categoryList;
	private Integer page;
	private PageBean<Product> pageBean;
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public PageBean<Product> getPageBean() {
		return pageBean;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String findByCid(){
		categoryList=categoryService.findAll();
		System.out.println("1查找"+categoryList.size());
		pageBean =productService.findByPage(cid,page);
		return "findByCidSuccess";
	}
	public String findByCsid(){
		String s="";
		s=ServletActionContext.getRequest().getParameter("csid");
		csid=Integer.parseInt(s);
		pageBean=productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}
	public String findByPid(){
		//System.out.println("pid="+product.getPid());
		
		 model=productService.findByPid(model.getPid());
		System.out.println(model.getPname()+model.getShop_price());
		//ActionContext.getContext().getSession().put("model", model);
		return "findByPidSuccess";
	}
	@Override
	public Product getModel() {
		return model;
	}
	public void setModel(Product model) {
		this.model = model;
	}
}
