package cn.test;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.*;
import cn.itcast.shop.product.*;

public class TestIndexAction extends ActionSupport{
	
	public String execute(){
		System.out.println("TestIndexAction");
		
		return "succcess";
	}
}
