package cn.itcast.shop.user;

import javax.mail.MessagingException;

import org.springframework.transaction.annotation.Transactional;

import antlr.Utils;
import cn.itcast.shop.Utils.MailUtils;
import cn.itcast.shop.Utils.QQEmailUtils;
import cn.itcast.shop.Utils.UUidUtils;

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
			QQEmailUtils.send_mail(user.getEmail(), code);
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
}
