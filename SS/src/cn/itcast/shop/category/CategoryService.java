package cn.itcast.shop.category;

import java.util.List;

public class CategoryService {
	private CategoryDao categoryDao;
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao= categoryDao;
	}
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

}
