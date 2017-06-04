package cn.itcast.shop.categorysecond;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.Utils.PageHibernateCallback;
import cn.itcast.shop.category.Category;

public class CategorySecondDao extends HibernateDaoSupport{


	public Integer findCount() {
		List<Long> list=this.getHibernateTemplate().find("select count(*) from CategorySecond");
		if(list.size()==0) return null;
		return list.get(0).intValue();
	}

	public List<CategorySecond> findByPage(Integer begin, Integer limit) {
		String hql="from CategorySecond";
		List<CategorySecond> list=this.getHibernateTemplate().executeFind
			(new PageHibernateCallback<CategorySecond>(hql, null, begin, limit));
		return list;
	}

	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	public void delete(Integer csid) {
		CategorySecond categorySecond=this.getHibernateTemplate().get(CategorySecond.class, csid);
		this.getHibernateTemplate().delete(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	public Category findCategoryByCsid(Integer csid) {
		
		List<Category>list= this.getHibernateTemplate().find("from Category c where c.categorySeconds.csid=?",csid);
		return list.get(0);
	}

	public List<CategorySecond> findAll() {
		return this.getHibernateTemplate().find("from CategorySecond");
	}
}
