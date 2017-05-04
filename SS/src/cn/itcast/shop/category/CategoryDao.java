package cn.itcast.shop.category;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CategoryDao extends HibernateDaoSupport{
	public List<Category> findAll() {
		return this.getHibernateTemplate().find("from Category");
	}
}
