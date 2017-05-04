package cn.itcast.shop.product;

import java.util.List;

public class ProductService {
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public List<Product> findHot() {
		return productDao.findHot();
	}
	public List<Product> findNew() {
		return productDao.findNew();
	}
}
