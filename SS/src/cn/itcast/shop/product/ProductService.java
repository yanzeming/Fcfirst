package cn.itcast.shop.product;

import java.util.ArrayList;
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
	/*public List<Product> findAll(){
		
	}*/
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	public PageBean<Product> adminFindAll(Integer page) {
		PageBean<Product> pageBean=new PageBean();
		Integer limit=10;
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		Integer totalCount=productDao.adminFindAll();
		pageBean.setTotalCount(totalCount);
		Integer totalPage=totalCount/limit;
		if(totalCount%limit!=0) totalPage++;
		pageBean.setTotalPage(totalPage);
		Integer begin=(page-1)*limit;
		List<Product> list=productDao.adminFindAllByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public void save(Product model) {
		productDao.save(model);
	}
	public void deleteByPid(Integer pid) {
		Product p=productDao.findByPid(pid);
		productDao.deleteByPid(p);
	}
	public void update(Product model, Product update) {
		update.setPname(model.getPname());
		update.setShop_price(model.getShop_price());
		update.setMarket_price(model.getMarket_price());
		update.setNum(model.getNum());
		update.setIs_hot(model.getIs_hot());
		update.setPdesc(model.getPdesc());
		productDao.update(update);
	}
	public List<Product> findAllProduct() {
		return productDao.findAllProduct();
	}
	public PageBean<Product> findByLucenePage(List<Integer> goodsIdList,Integer page) {
		PageBean<Product> pageBean=new PageBean();
		pageBean.setPage(page);
		Integer totalCount=goodsIdList.size();
		pageBean.setTotalCount(totalCount);
		Integer limit=10;
		pageBean.setLimit(limit);
		Integer totalPage=0;
		totalPage=totalCount/limit;
		if(totalCount%limit!=0) totalPage++;
		pageBean.setTotalPage(totalPage);
		List<Product> list=new ArrayList();
		Integer start=(page-1)*limit;
		if(totalCount!=0){
			Integer min=Math.min(limit, goodsIdList.size()-start);
			for(int i=start;i<start+min;i++){
				Product p=productDao.findByPid(goodsIdList.get(i));
				//System.out.println("service中的操作"+p.getPid()+"   "+p.getPname());
				list.add(p);
			}
		}
		//System.out.println(list.size());
		pageBean.setList(list);
		return pageBean;
	}
	
	
}
