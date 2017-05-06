package cn.itcast.shop.product;

import java.util.List;

import cn.itcast.shop.Utils.PageBean;

public class ProductService {
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public List<Product> findHot() {
		return productDao.findHot();
	}
	public List<Product> findNew() {
		return productDao.findNew();
	}
	public PageBean<Product> findByPage(Integer cid, Integer page) {
		int limit=12;
		PageBean<Product> pageBean=new PageBean();
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		Integer totalCount =productDao.findCount(cid);
		///System.out.println(totalCount);
		pageBean.setTotalCount(totalCount);
		int totalPage=totalCount/limit;
		if(totalCount%limit!=0) totalPage++;
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		System.out.println(cid+"  "+begin+"  "+limit);
		List<Product> list=productDao.findByPage(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		int limit=8;
		int totalPage=0;
		PageBean<Product> pageBean=new PageBean();
		pageBean.setLimit(limit);
		pageBean.setPage(page);
		Integer totalCount=productDao.findCountByCsid(csid);
		pageBean.setTotalCount(totalCount);
		totalPage=totalCount/limit;
		if(totalCount%limit!=0) totalPage++;
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	
	
}
