package com.jung.region.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hp.util.PageContext;
import com.jung.region.dao.RegionDao;
import com.jung.region.dao.RegionHibernateDao;
import com.jung.region.model.Region;
import com.jung.region.service.RegionService;

public class RegionServiceImpl implements RegionService {

	private RegionHibernateDao regionHibernateDao;
	private RegionDao regionDao;
	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(Region.REF)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param regionHibernateDao the regionHibernateDao to set
	 */
	@Resource
	public void setRegionHibernateDao(RegionHibernateDao regionHibernateDao) {
		this.regionHibernateDao = regionHibernateDao;
	}

	/**
	 * @param regionDao the regionDao to set
	 */
	@Resource
	public void setRegionDao(RegionDao regionDao) {
		this.regionDao = regionDao;
	}

	@Override
	public List<Region> getRegions() {
		// TODO Auto-generated method stub
		return regionHibernateDao.getRegions();
	}

	@Override
	public Region getRegionByID(int regionID) {
		// TODO Auto-generated method stub
		return regionHibernateDao.getRegionByID(regionID);
	}

	@Override
	public boolean saveRegion(Region region) {
		// TODO Auto-generated method stub
		return regionHibernateDao.saveRegion(region);
	}

	@Override
	public boolean updateRegion(Region region) {
		// TODO Auto-generated method stub
		return regionHibernateDao.updateRegion(region);
	}

	@Override
	public boolean deleteRegionByID(int regionID) {
		// TODO Auto-generated method stub
		return regionHibernateDao.deleteRegionByID(regionID);
	}

}
