package cn.itcast.shop.product;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product=new Product();
	private ProductService productService;
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public Product getModel() {
		return product;
	}
	
	
}
