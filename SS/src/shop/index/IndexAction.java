package cn.itcast.shop.index;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.*;
import cn.itcast.shop.product.*;

public class IndexAction extends ActionSupport{
	private CategoryService categoryService;
	private ProductService productService;
	private List<Product>  newList;
 	public List<Product> getNewList() {
		return newList;
	}

	private List<Product> hotList;
	public List<Product> getHotList() {
		return hotList;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String execute(){
		System.out.println("index");
		List<Category> categorylist=categoryService.findAll();
		ActionContext.getContext().getSession().put("categorylist", categorylist);
		this.hotList=productService.findHot();
		this.newList=productService.findNew();
		return "indexSuccess";
	}
}
