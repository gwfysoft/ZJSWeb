package com.jung.productexchangerecord.dao.impl;

import java.util.List;

import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;
import com.jung.productexchangerecord.dao.ProductExchangePeriodHiberanteDao;
import com.jung.productexchangerecord.model.ProductExchangePeriod;

public class ProductExchangePeriodHiberanteDaoImpl extends HibernateEntityManagerImpl<ProductExchangePeriod> implements
		ProductExchangePeriodHiberanteDao {

	@Override
	public Class<ProductExchangePeriod> getEntityType() {
		return ProductExchangePeriod.class;
	}

	@Override
	public boolean addProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		try {
			super.saveOrUpdate(productExchangePeriod);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		try {
			super.remove(productExchangePeriod);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductExchangePeriodById(int productExchangePeriod) {
		String sql = "delete from productExchangePeriod where id=" + productExchangePeriod;
		try {
			super.executeSQLUpdate(sql);
			return true;
		} catch (SkeletonException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ProductExchangePeriod getProductExchangePeriodById(int productExchangePeriodID) {
		String hql = "from productExchangePeriod where id=" + productExchangePeriodID;
		try {
			List productExchangePeriodList = super.executeHql(hql);
			if (productExchangePeriodList != null && productExchangePeriodList.size() > 0) {
				return (ProductExchangePeriod) productExchangePeriodList.get(0);
			}
		} catch (SkeletonException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		try {
			super.update(productExchangePeriod);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

}
