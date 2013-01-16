package com.jung.region.service;

import java.util.List;

import com.jung.common.JqueryGridService;
import com.jung.region.model.Region;

public interface RegionService extends JqueryGridService {

	public boolean saveRegion(Region region);
	public boolean updateRegion(Region region);
	public List<Region> getRegions();
	public Region getRegionByID(int regionID);
	public boolean deleteRegionByID(int regionID);
}
