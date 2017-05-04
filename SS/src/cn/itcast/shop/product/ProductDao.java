package cn.itcast.shop.product;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.Utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport{

	public List<Product> findHot() {
		return this.getHibernateTemplate().executeFind(
				new PageHibernateCallback<Product>
				("from Product where is_hot=?", new Object[]{0}, 0,10));
	}

	public List<Product> findNew() {
		return this.getHibernateTemplate().executeFind( new PageHibernateCallback
			<Product>("from Product order by pdate desc", null, 0, 10));
	}
}
