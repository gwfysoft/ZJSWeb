package com.jung.region.dao;

import java.util.List;

import com.jung.region.model.Region;

public interface RegionHibernateDao {
	public boolean saveRegion(Region region);
	public boolean updateRegion(Region region);
	public List<Region> getRegions();
	public Region getRegionByID(int regionID);
	public boolean deleteRegionByID(int regionID);
}
