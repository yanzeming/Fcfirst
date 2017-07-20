package cn.itcast.shop.product;
import cn.itcast.shop.categorysecond.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.junit.Test;

import cn.itcast.shop.Utils.*;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import cn.itcast.shop.category.*;
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product model=new Product();
	private Integer csid;
	private Integer cid;
	private ProductService productService;
	private CategoryService categoryService;
	private List<Category> categoryList;
	private Integer page;
	private PageBean<Product> pageBean;
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	private CategorySecondService categorySecondService;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public Product getModel() {
		return model;
	}
	public void setModel(Product model) {
		this.model = model;
	}
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public PageBean<Product> getPageBean() {
		return pageBean;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String findByCid(){
		categoryList=categoryService.findAll();
		System.out.println("1查找"+categoryList.size());
		pageBean =productService.findByPage(cid,page);
		return "findByCidSuccess";
	}
	public String findByCsid(){
		categoryList=categoryService.findAll();
		String s="";
		s=ServletActionContext.getRequest().getParameter("csid");
		csid=Integer.parseInt(s);
		pageBean=productService.findByCsid(csid,page);
		return "findByCsidSuccess";
	}
	public String findByPid(){
		//System.out.println("pid="+product.getPid());
		
		 model=productService.findByPid(model.getPid());
		System.out.println(model.getPname()+model.getShop_price());
		//ActionContext.getContext().getSession().put("model", model);
		return "findByPidSuccess";
	}
	public String adminFindAll(){
		//System.out.println("进入商品查询函数");
		PageBean<Product> pageBean=productService.adminFindAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//System.out.println("pageBean中的数据"+pageBean.getList().size());
		return "adminFindAllSuccess";
	}
	public String addPage(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPageSuccess";
	}
	public String save(){
		String cs=ServletActionContext.getRequest().getParameter("cscs").trim();
		Integer csid=Integer.parseInt(cs);
		String path=ServletActionContext.getServletContext().getRealPath("/products");
		String realPath=path+csid+"\\"+uploadFileName;///文件真实路径
		//System.out.println(realPath);
		File diskFile=new File(realPath);
		try {
			FileUtils.copyFile(upload, diskFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.setImage("products/"+csid+"/"+uploadFileName);
		model.setPdate(new Date());
		//System.out.println("设置csid="+csid);
		model.setCsid(csid);
		CategorySecond categorySecond=categorySecondService.findByCsid(csid);
		model.setCategorySecond(categorySecond);
		System.out.println(model.toString());
		productService.save(model);
		return "saveSuccess";
	}
	public String delete(){
		productService.deleteByPid(model.getPid());
		return "deleteSuccess";
	}
	public String edit(){///商品和二级分类中的级联存在问题
		model=productService.findByPid(model.getPid());
		System.out.println(model.getCategorySecond());
		System.out.println(model.getCsid());
		System.out.println(model.toString());
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}
	public String update(){
		Product update=productService.findByPid(model.getPid());
		String cscs=ServletActionContext.getRequest().getParameter("cscs").trim();
		Integer csid=Integer.parseInt(cscs);
		CategorySecond categorySecond=categorySecondService.findByCsid(csid);
		update.setCategorySecond(categorySecond);
		if(uploadFileName==null){
			System.out.println("使用之前的图片");
		}else {
			System.out.println("使用新的图片");
			String path=ServletActionContext.getServletContext().getRealPath("/products");
			String realPath=path+csid+"\\"+uploadFileName;
			File diskFile=new File(realPath);
			try {
				FileUtils.copyFile(upload, diskFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			update.setImage("products/"+csid+"/"+uploadFileName);
		}
		productService.update(model,update);
		return "updateSuccess";
	}
}
