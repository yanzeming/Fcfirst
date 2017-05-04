package cn.itcast.shop.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class UserAction implements ModelDriven<User>{
	private UserService userService;
	private User user=new User();
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public ValueStack stack=ActionContext.getContext().getValueStack();
	public User u=new User();
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String registPage(){
		return "registPageSuccess";
	}
	public String checkCode(){
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String secode=ServletActionContext.getRequest().
				getSession().getAttribute("checkcode").toString();
		try {
			if(this.checkcode==null||!this.checkcode.equalsIgnoreCase(secode)){
				response.getWriter().print("0");
			}else {
				response.getWriter().print("1");
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return "none";
	}
	public String regist(){	
		/*String check=ServletActionContext.getRequest().getSession().
				getAttribute("checkcode").toString();
		if(check==null||!check.equalsIgnoreCase(this.checkcode)){
			return "none";
		}*/
		userService.regist(user);		
		u.setName("注册成功，请去邮箱激活");
		stack.set("u", u);
		return "registSuccess";
	}
	public String active(){
		User use=userService.findByCode(user.getCode());
		if(use!=null){
			use.setState(1);
			userService.update(use);
			u.setName("激活成功");
			return "actionMsg";
		}
		u.setName("激活失败，请检查激活码");
		return "actionMsg";
	}
	public String loginPage(){
		return "loginPageSuccess";
	}
	public String checkUserName(){
		//System.out.println("是否提交成功" +user.getUsername()+"username是否为空");
		User userExist=userService.findByUsername(user);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		
			// 用户名可以使用的
			try {
				if(userExist == null){//<font color='green'>用户名可用</font>
					response.getWriter().print("1");
				}else{
					// 用户名已经存在 <font color='red'>用户名已经存在</font>
					response.getWriter().print("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return  "none";
	}
	public String checkEmail(){
		User userExist=userService.findByEmail(user);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			if(userExist==null){
				response.getWriter().println("1");//<font color='green'>邮箱可用</font>
			}else {
				response.getWriter().println("0");//<font color='red'>该邮箱已注册</font>
			}
		}catch (IOException e) {
				e.printStackTrace();
		}
		return "none";
	}
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quitSuccess";
	}
	public String login(){
		User userExist=userService.login(user);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		if(userExist==null){
			try {
				response.getWriter().print("<font color='red'>用户名或密码错误</font>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "none";
		}
		else {
			/*System.out.println("登陆成功username="+user.getUsername()
			+"password="+user.getPassword());*/
			ServletActionContext.getRequest().getSession().setAttribute("existUser", userExist);
			return "loginSuccess";
		}
	}
	public User getModel() {
		return user;
	}
}
