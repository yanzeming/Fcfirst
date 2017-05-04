package cn.itcast.shop.Utils;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email163Utils{
	/**
    * 发送邮件
   * @param to 给谁发
     * @param text 发送内容
     */
	//qq邮箱授权码   tdvbapvhwvmfbfbb
	public static String username="c1343497749@163.com";
	public static String password="cao123456";
	public static void send_mail(String to,String code) throws MessagingException {
//创建连接对象 连接到
		Properties properties = new Properties();
//设置发送邮件的基本参数
//发送邮件服务        
		properties.put("mail.smtp.host", "smtp.qq.com");
//发送端口
		//properties.put("mail.smtp.port", "25");
		properties.put("mail.smtp.auth", "true");
		
		///sll
		
//      QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
		/*final String smtpPort = "465";
		properties.setProperty("mail.smtp.port", smtpPort);
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.socketFactory.port", smtpPort);*/
		
		
	/*	properties.put("mail.smtp.socketFactory.class", "javax.NET.ssl.SSLSocketFactory"); 
		properties.put("mail.smtp.port", "465"); 
		properties.put("mail.smtp.socketFactory.port", "465"); */
		//设置发送邮件的账号和密码     
		Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
//两个参数分别是发送邮件的账户和密码
            	return new PasswordAuthentication(username,password);//("chaoergou@163.com","chaoergou123456");
           }
        });
//创建邮件对象
		Message message = new MimeMessage(session);
//设置发件人
		message.setFrom(new InternetAddress(username));//("chaoergou@163.com"));
//设置收件人
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
//设置主题
		message.setSubject("来自SHOP的官网激活邮件");
//设置邮件正文  第二个参数是邮件发送的类型42  
		message.setContent("<h1>来自SHOP的官网激活邮件</h1><h3><a href='http://10.0.0.27:8080/"
				+ "SS/user_active?code="+code+"'>http://10.0.0.27:8080/SS/"
				+ "user_active?code="+code+"</a></h3>","text/html;charset=UTF-8");
//发送一封邮件   
		Transport.send(message);
    }
 }