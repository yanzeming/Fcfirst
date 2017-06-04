package cn.itcast.shop.categorysecond;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.Utils.PageBean;
import cn.itcast.shop.category.Category;
import cn.itcast.shop.category.CategoryService;
public class CategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{
	private CategorySecond categorySecond=new CategorySecond();
	private CategorySecondService categorySecondService;
	private CategoryService categoryService;
	private Integer page;
	private Integer cid;
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public CategoryService getCategoryService() {
		return categoryService;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public CategorySecondService getCategorySecondService() {
		return categorySecondService;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public CategorySecond getCategorySecond() {
		return categorySecond;
	}
	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}
	public CategorySecond getModel() {
		return categorySecond;
	}
	public String adminFindAll(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		System.out.println(pageBean.getList().get(0).getCsname());
		return "adminFindAllSuccess";
	}
	public String addPage(){
		List<Category> categoryList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("categoryList", categoryList);
		return "addPageSuccess";
	}
	public String save(){
		Category category=new Category();
		category.setCid(cid);
		categorySecond.setCategory(category);
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	public  String delete(){
		categorySecondService.delete(categorySecond.getCsid());
		System.out.println("csid="+categorySecond.getCsid());
		return "deleteSuccess";
	}
	public String edit(){
		categorySecond =categorySecondService.findByCsid(categorySecond.getCsid());
		ActionContext.getContext().getValueStack().set("categorySecond", categorySecond);
		return "editSuccess";
	}
	public String update(){
		String ccc=ServletActionContext.getRequest().getParameter("id");
		String cname=ServletActionContext.getRequest().getParameter("name");
		//除了id和name之外还需要 
		System.out.println(ccc+cname);
		categorySecond=categorySecondService.findByCsid(Integer.parseInt(ccc));
		categorySecond.setCsname(cname);//延迟加载和持久化问题
		//System.out.println(categorySecond.toString());
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
