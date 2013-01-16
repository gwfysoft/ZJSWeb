package com.jung.productexchangerecord.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.admin.action.AdminAction;
import com.jung.common.HibernateEntityManagerImpl;
import com.jung.doctor.model.Doctor;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;
import com.jung.productexchangerecord.dao.ProductExchangeRecordHiberanteDao;
import com.jung.productexchangerecord.model.ProductExchangeRecord;
import com.jung.productexchangerecord.model.ProductExchangeRecordStatistics;
import com.jung.region.model.Region;
import com.jung.region.model.RegionType;

public class ProductExchangeRecordHibernateDaoImpl extends HibernateEntityManagerImpl<ProductExchangeRecord> implements
		ProductExchangeRecordHiberanteDao {
	private static final Log logger = LogFactory.getLog(ProductExchangeRecordHibernateDaoImpl.class);
	@Override
	public boolean addProductExchangeRecord(ProductExchangeRecord productExchangeRecord) {
		try {
			super.saveOrUpdate(productExchangeRecord);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductExchangeRecord(ProductExchangeRecord productExchangeRecord) {
		try {
			super.remove(productExchangeRecord);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteProductExchangeRecordById(int productExchangeRecordID) {
		String sql = "delete from productexchangerecord where productExchangeRecordID=" + productExchangeRecordID;
		try {
			super.executeSQLUpdate(sql);
			return true;
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ProductExchangeRecord getProductExchangeRecordById(int productExchangeRecordID) {
		// TODO Auto-generated method stub
		String hql = "from ProductExchangeRecord where productExchangeRecordID=" + productExchangeRecordID;
		try {
			List productExchangeRecordList = super.executeHql(hql);
			if (productExchangeRecordList != null && productExchangeRecordList.size() > 0) {
				return (ProductExchangeRecord) productExchangeRecordList.get(0);
			}
		} catch (SkeletonException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductExchangeRecordStatistics> getProductExchangeRecordStatisticsChildsByRecursion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<ProductExchangeRecord> getEntityType() {
		// TODO Auto-generated method stub
		return ProductExchangeRecord.class;
	}


	@Override
	public boolean addProductExchangeRecordStatistics(ProductExchangeRecordStatistics productExchangeRecordStatistics) {
		try {
			super.saveOrUpdate(productExchangeRecordStatistics);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateProductExchangeRecordStatistics(ProductExchangeRecordStatistics productExchangeRecordStatistics) {
		try {
			super.update(productExchangeRecordStatistics);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteProductExchangeRecordByProductID(int productID){
		String sql="delete from productexchangerecord  where productID="+productID;
		try {
			super.executeSQL(sql);
			return true;
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 单次兑换积分累加统计数据至统计表,
	 * 限定为一个医生只能属于一个科室的情况。不能一个医生同时属于多个科室或者多个医院.
	 * 把医生作为参考点进行统计.确保每一个原子记录按照期数累计.科室以上暂时根据SQL查询统计.
	 * @param productExchangeRecord
	 * @return
	 */
	@Override
	public boolean appendOneProductExchangeRecordStatistics(ProductExchangeRecord productExchangeRecord) {
		//TODO 这里还未实现.
		Integer _doctorPoints = productExchangeRecord.getDoctor().getDoctorPoints();//此医生的现有积分
		Integer _productPoints = productExchangeRecord.getProduct().getProductPoints();// 如果限定每次兑换一个product，那么这里只需要取product所需的兑换积分
		Region _region = productExchangeRecord.getDoctor().getRegion();//这里的region应该总是是科室.
		Integer _regionId =_region.getRegionID();//这里的region应该总是是科室.
		if(_region.getRegionType()!=3){
			logger.warn("ZJS-----:医生只能属于科室");
		}
		ProductExchangeRecordStatistics productExchangeRecordStatistics = this.getProductExchangeRecordStatisticsById(_regionId);// 这里的regionId永远是科室.业务限制.暂时没有做程序业务限制.测试后后期改进.暂时只做假设

		return false;
	}

	@Override
	public List<ProductExchangeRecordStatistics> getProductExchangeRecordStatisticsAll() {
		String hql = "from ProductExchangeRecordStatistics";
		try {
			List productExchangeRecordStatisticsList = super.executeHql(hql);
			if (productExchangeRecordStatisticsList != null && productExchangeRecordStatisticsList.size() > 0) {
				return productExchangeRecordStatisticsList;
			}
		} catch (SkeletonException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductExchangeRecordStatistics getProductExchangeRecordStatisticsById(int productExchangeRecordStatisticsId) {
		String hql = "from ProductExchangeRecordStatistics where id=" + productExchangeRecordStatisticsId;
		try {
			List productExchangeRecordStatisticsList = super.executeHql(hql);
			if (productExchangeRecordStatisticsList != null && productExchangeRecordStatisticsList.size() > 0) {
				return (ProductExchangeRecordStatistics) productExchangeRecordStatisticsList.get(0);
			}
		} catch (SkeletonException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductExchangeRecordStatistics getProductExchangeRecordStatisticsByRegionIdAndExchangeDate(int productExchangeRecordStatisticsRegionId,
			Date exchangeDate) {
		//TODO 这里需要改进.是否需要限定为每一个医生在某一期只能兑换一次.如果兑换多次.需要对医生的某一期进行统计累加.
		String hql = "from ProductExchangeRecordStatistics where regionID=" + productExchangeRecordStatisticsRegionId + " and exchangeDate='"
				+ exchangeDate + "'";//业务限定每期的地区与期数只能唯一.可能异常...
		try {
			List productExchangeRecordStatisticsList = super.executeHql(hql);
			if (productExchangeRecordStatisticsList != null && productExchangeRecordStatisticsList.size() > 0) {
				return (ProductExchangeRecordStatistics) productExchangeRecordStatisticsList.get(0);
			}
		} catch (SkeletonException e) {
			e.printStackTrace();
		}
		return null;
	}
}
