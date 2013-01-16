package com.jung.product.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;
import com.jung.product.dao.ProductDao;
import com.jung.product.dao.ProductHibernateDao;
import com.jung.product.model.Product;
import com.jung.product.service.ProductService;
import com.jung.question.model.Question;

public class ProductServiceImpl implements ProductService {

	private ProductHibernateDao productHibernateDao;
	private ProductDao productDao;

	/**
	 * @param productHibernateDao the productHibernateDao to set
	 */
	@Resource
	public void setProductHibernateDao(ProductHibernateDao productHibernateDao) {
		this.productHibernateDao = productHibernateDao;
	}
	
	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		SimpleQuery query = productDao.getSimpleQuery();
		if (queryConditions != null) {
			String productName = queryConditions.get("productName");
			if (StringUtils.hasLength(productName)) {
				query.addFilter(Restrictions.like("productName", "%" + productName
						+ "%"));
			}
		}
		if (orderProperty != null && !"".equals(orderProperty)) {
			if (orderMode.equals("asc")) {
				query.addOrder(orderProperty, true);
			} else if (orderMode.equals("desc")) {
				query.addOrder(orderProperty, false);
			}
		}
		Page page = productDao.find(query, pageContext.getPageNumber(),
				pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
	}


	@Override
	public boolean addProduct(Product product) {
		// TODO Auto-generated method stub
		return productHibernateDao.addProduct(product);
	}

	@Override
	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return productHibernateDao.deleteProduct(product);
	}

	@Override
	public boolean delteProductByID(int productID) {
		// TODO Auto-generated method stub
		return productHibernateDao.delteProductByID(productID);
	}

	@Override
	public Product findProductByID(int productID) {
		// TODO Auto-generated method stub
		return productHibernateDao.getProductByID(productID);
	}

	/**
	 * @param productDao the productDao to set
	 */
	@Resource
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return productHibernateDao.updateProduct(product);
	}

	@Override
	public boolean support(String entityName) {
		// TODO Auto-generated method stub
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(Product.REF)) {
				return true;
			}
		}
		return false;
	}

}
