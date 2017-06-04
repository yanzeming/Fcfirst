package cn.itcast.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport{
	public List<Category> findAll() {
		return this.getHibernateTemplate().find("from Category");
	}

	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	public void delete(Category category) {
		//必须先查询一次，否则该对象中只有id没有集合无法关联
		category =this.getHibernateTemplate().load(Category.class, category.getCid());
		this.getHibernateTemplate().delete(category);
	}

	public Category findByCid(Integer cid) {
		Category list=this.getHibernateTemplate().get(Category.class, cid);
		return list;
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
}
