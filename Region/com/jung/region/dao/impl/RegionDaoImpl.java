package com.jung.region.dao.impl;

import java.util.List;

import com.hp.util.Page;
import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.hp.xquery.SimpleQuery;
import com.jung.region.dao.RegionDao;
import com.jung.region.model.Region;

public class RegionDaoImpl extends HibernateBaseDao  implements RegionDao{

	@Override
	public Class getEntityType() {
		// TODO Auto-generated method stub
		return Region.class;
	}


}
