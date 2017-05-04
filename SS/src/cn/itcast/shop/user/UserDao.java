package cn.itcast.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDao extends HibernateDaoSupport{

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public User findByCode(String code) {
		List<User> list=this.getHibernateTemplate().find("from User where code=?", code);
		System.out.println("数据库查询中出现的问题");
		if(list.size()==0) return null;
		else return list.get(0);
	}

	public void update(User use) {
		this.getHibernateTemplate().update(use);
	}

	public User findbyUsername(User user) {
		List<User> list=this.getHibernateTemplate().find("from User where username=?",user.getUsername());
		if(list.size()==0) return null;
		else return list.get(0);
	}

	public User login(User user) {
		List<User> list=this.getHibernateTemplate().find("from User where username=? and password=?",
			user.getUsername(),user.getPassword());
		if(list.size()==0) return null;
		else return list.get(0);
	}

	public User findBuEmail(User user) {
		List<User> list=this.getHibernateTemplate().find("from User where email=?", user.getEmail());
		if(list.size()!=0) return list.get(0);
		return null;
	}
	
}
