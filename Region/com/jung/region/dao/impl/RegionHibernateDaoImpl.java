package com.jung.region.dao.impl;

import java.util.List;

import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;
import com.jung.region.dao.RegionHibernateDao;
import com.jung.region.model.Region;

public class RegionHibernateDaoImpl  extends HibernateEntityManagerImpl<Region> implements RegionHibernateDao{

	@Override
	public Class<Region> getEntityType() {
		// TODO Auto-generated method stub
		return Region.class;
	}
	public boolean saveRegion(Region region) {
		try {
			super.saveOrUpdate(region);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateRegion(Region region){
		try {
			super.update(region);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return	false;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Region> getRegions() {
		// TODO Auto-generated method stub
		String hql="from Region";
		try {
			List regions=super.executeHql(hql);
			return regions;
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean deleteRegionByID(int regionID){
		String sql="delete from region where regionID="+regionID;
		try {
			super.executeSQLUpdate(sql);
			return true;
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	public Region getRegionByID(int regionID){
		String hql="from Region as region where region.regionID="+regionID;
		try {
			List regions=super.executeHql(hql);
			if(regions!=null&&regions.size()>0){
				return (Region) regions.get(0);
			}
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
