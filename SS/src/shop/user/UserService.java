package cn.itcast.shop.user;

import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Transactional;

import antlr.Utils;
import cn.itcast.shop.Utils.MailUtils;
import cn.itcast.shop.Utils.PageBean;
import cn.itcast.shop.Utils.QQEmailUtils;
import cn.itcast.shop.Utils.UUidUtils;
import cn.itcast.shop.Utils.*;
@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void regist(User user) {
		user.setState(0);
		String code=UUidUtils.getUUid();
		user.setCode(code);
		//MailUtils.sendMail(user.getEmail(),code );
		try {
			String subject="来自shop官网的激活邮件";
			String context="<h1>来自SHOP的官网激活邮件</h1><h3><a href='"+ConstUtils.url+"/"
					+ "SS/user_active?code="+code+"'>"+ConstUtils.url+"/SS/"
					+ "user_active?code="+code+"</a></h3>";
			QQEmailUtils.send_mail(user.getEmail(),subject,context);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		userDao.save(user);
	}

	public User findByCode(String code) {
		
		return userDao.findByCode(code);
	}

	public void update(User use) {
		userDao.update(use);
	}

	public User findByUsername(User user) {
		User use=userDao.findbyUsername( user);
		if(use!=null) return use;
		return null;
	}

	public User login(User user) {
		return userDao.login(user);
	}

	public User findByEmail(User user) {
		return userDao.findBuEmail(user);
	}

	public PageBean<User> adminFindAllByPage(Integer page) {
		PageBean<User> pageBean=new PageBean();
		pageBean.setPage(page);
		Integer limit=10;
		pageBean.setLimit(limit);
		Integer totalCount=userDao.findAllCount();
		pageBean.setTotalCount(totalCount);
		Integer totalPage=totalCount/limit;
		if(totalCount%limit!=0) totalPage++;
		pageBean.setTotalPage(totalPage);
		Integer start=(page-1)*limit;
		List<User> list=userDao.adminFindByPage(start,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public User findByUid(Integer uid) {
		return userDao.findByUid(uid);
	}

	public void adminDeleteUser(User u) {
		userDao.adminDeleteUser(u);
	}

	public void update(User user, User update) {
		System.out.println("到达service");
		update.setAddr(user.getAddr());
		update.setEmail(user.getEmail());
		update.setUsername(user.getUsername());
		update.setPassword(user.getPassword());
		update.setState(user.getState());
		update.setSex(user.getSex());
		update.setName(user.getName());
		update.setPhone(user.getPhone());
		System.out.println("到达update");
		System.out.println(update.toString());
		userDao.update(update);
	}


	public String getQqYzm(User user) {
		User findUser=userDao.findBuEmail(user);
		String subject="重置密码的邮件";
		String yzm="";
		Random rand=new Random();
		for(int i=0;i<4;i++){
			Integer num=rand.nextInt()%10;
			if(num<0)num+=10;
			yzm+=num;
		}
		String context="<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>邮箱验证码</title>"
				+"</head><body>收到验证码<font color=\"red\">"+yzm+"</font>请去界面中填写</body></html>";	
		try {
			QQEmailUtils.send_mail(findUser.getEmail(), subject, context);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return yzm;
	}

	public String updatePassword(User user) {
		User update=userDao.findBuEmail(user);
		update.setPassword(user.getPassword());
		userDao.update(update);
		return update.getUsername();
	}
}
