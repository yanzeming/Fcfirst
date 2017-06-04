package cn.itcast.shop.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

import cn.itcast.shop.Utils.PageBean;

public class UserAction implements ModelDriven<User>{
	private UserService userService;
	private User user=new User();
	private String checkcode;
	public ValueStack stack=ActionContext.getContext().getValueStack();
	public User u=new User();
	private Integer page;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String registPage(){
		return "registPageSuccess";
	}
	public String checkCode(){
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String secode=null;
		try {
			secode=ServletActionContext.getRequest().
				getSession().getAttribute("checkcode").toString();
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
		HttpServletRequest request=ServletActionContext.getRequest();
		String token=(String) request.getSession().getAttribute("token");
		request.getSession().removeAttribute("token");
		String _token=request.getParameter("token");
		if(token==null||_token==null||!token.equals(_token)){
			System.out.println("表单重复提交");
			return "submitFormTimeAfterTime";
		}
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
		String checkBox=ServletActionContext.getRequest().getParameter("isRememberUsername");
		System.out.println("是否保存"+checkBox);
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
			if(checkBox!=null&&checkBox.equals("true")){
				User rememberUser=userService.login(user);
				HttpSession session=ServletActionContext.getRequest().getSession();
				session.setAttribute("rememberUser", rememberUser);
				System.out.println("已经保存到session");
				session.setMaxInactiveInterval(60*60*24);
			}
			/*System.out.println("登陆成功username="+user.getUsername()
			+"password="+user.getPassword());*/
			ServletActionContext.getRequest().getSession().setAttribute("existUser", userExist);
			return "loginSuccess";
		}
	}
	public User getModel() {
		return user;
	}
	public String adminFindAll(){
		PageBean<User> pageBean=userService.adminFindAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean",pageBean);
		return "adminFindAllSuccess";
	}
	public String delete(){
		User u=userService.findByUid(user.getUid());
		userService.adminDeleteUser(u);
		return "deleteSuccess";
	}
	public String edit(){
		user=userService.findByUid(user.getUid());
		System.out.println(user.toString());
		ActionContext.getContext().getValueStack().set("user",user);
		return "editSuccess";
	}
	public String update(){
		System.out.println(user.toString());
		User update=userService.findByUid(user.getUid());
		
		userService.update(user,update);
		return "updateSuccess";
	}
	public String findPassWordPage(){
		return "findPassWordPageSuccess";
	}
	public String getYzm(){
		String yzm=userService.getQqYzm(user);
		System.out.println("验证码是"+yzm);
		//ServletActionContext.getRequest().getSession().setAttribute("yzm", yzm);
		try {
			ServletActionContext.getResponse().getWriter().print(yzm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "none";
	}
	public String updatePassWord(){
		//System.out.println(user.getPassword()+"新的密码"+"邮箱"+user.getEmail());
		String userName=userService.updatePassword(user);
		u.setName("重置密码成功，你的用户名是:   "+userName);
		return "updatePassWordSuccess";
	}
}
