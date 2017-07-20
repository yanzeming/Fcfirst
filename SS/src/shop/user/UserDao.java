package cn.itcast.shop.user;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.Utils.PageBean;
import cn.itcast.shop.Utils.PageHibernateCallback;

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

	public List<User> adminFindByPage(Integer start, Integer limit) {
		String hql="from User";
		List<User> list=this.getHibernateTemplate().executeFind(new PageHibernateCallback<User>
		(hql, null, start,limit));
		return list;
	}

	public Integer findAllCount() {
		List<Long> list=this.getHibernateTemplate().find("select count(*) from User" );
		if(list.size()==0) return 0;
		return list.get(0).intValue();
	}

	public User findByUid(Integer uid) {
		return this.getHibernateTemplate().get(User.class, uid);
	}

	public void adminDeleteUser(User u) {
		this.getHibernateTemplate().delete(u);
	}
	
}
