package com.jung.productexchangerecord.dao.impl;

import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.jung.productexchangerecord.dao.ProductExchangePeriodDao;
import com.jung.productexchangerecord.model.ProductExchangePeriod;

public class ProductExchangePeriodDaoImpl extends HibernateBaseDao implements ProductExchangePeriodDao {

	@Override
	public Class getEntityType() {
		return ProductExchangePeriod.class;
	}

}
