package cn.itcast.shop.categorysecond;

import java.util.List;

import cn.itcast.shop.Utils.PageBean;
import cn.itcast.shop.category.Category;

public class CategorySecondService {
	private CategorySecondDao categorySecondDao;

	public CategorySecondDao getCategorySecondDao() {
		return categorySecondDao;
	}

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean=new PageBean();
		pageBean.setPage(page);
		Integer limit=10;
		pageBean.setLimit(limit);
		Integer totalCount=categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		Integer totalPage=totalCount/limit;
		if(totalCount%limit!=0) totalPage++;
		pageBean.setTotalPage(totalPage);
		Integer begin =(page-1)*limit;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public void delete(Integer csid) {
		categorySecondDao.delete(csid);
	}

	public CategorySecond findByCsid(Integer csid) {
		
		return categorySecondDao.findByCsid(csid);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
}
