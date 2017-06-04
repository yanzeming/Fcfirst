package cn.itcast.shop.category;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category=new Category();
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public Category getModel() {
		return  category;
	}
	
	public Category getCategory() {
		return category;
	}
	public String adminFindAll(){
		List<Category> clist=categoryService.adminFindAll();
		ActionContext.getContext().getValueStack().set("clist", clist);
		return "adminFindAllSuccess";
	}
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	public String delete(){
		categoryService.delete(category);
		return "deleteSuccess";
	}
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	public String update(){
		categoryService.update(category);
		return "updateSuccess";
	}
}
