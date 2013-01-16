package com.jung.doctor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.hp.util.ListPage;
import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;
import com.jung.doctor.dao.DoctorDao;
import com.jung.doctor.dao.DoctorHibernateDao;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.model.DoctorVO;
import com.jung.doctor.model.Rank;
import com.jung.doctor.service.DoctorService;

public class DoctorServiceImpl implements DoctorService {

	private DoctorDao doctorDao;
	private DoctorHibernateDao doctorHibernateDao;

	@Override
	public boolean addDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.addDoctor(doctor);
	}

	@Override
	public boolean deleteDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.deleteDoctor(doctor);
	}

	public boolean deleteDoctorByID(int doctorID) {
		return doctorHibernateDao.deleteDoctorByID(doctorID);
	}

	@Override
	public Doctor getDoctorById(int doctorId) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.getDoctorById(doctorId);
	}

	@Override
	public List<Doctor> getDoctors() {
		// TODO Auto-generated method stub
		String hql="from Doctor";
		return doctorHibernateDao.getDoctors(hql);
	}

	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
//		SimpleQuery query = doctorDao.getSimpleQuery();
//		if(queryConditions!=null){
//			String doctorName = queryConditions.get("doctorName").trim();
//			if(StringUtils.hasLength(doctorName)){
//				query.addFilter(Restrictions.like("doctorName", "%" + doctorName
//						+ "%"));
//			}
//		}
//		if (orderProperty != null && !"".equals(orderProperty)) {
//			if (orderMode.equals("asc")) {
//				query.addOrder(orderProperty, true);
//			} else if (orderMode.equals("desc")) {
//				query.addOrder(orderProperty, false);
//			}
//		}
//		Page page = doctorDao.find(query, pageContext.getPageNumber(),
//				pageContext.getPageSize());
//		pageContext.setPage(page);
//		return pageContext;
		StringBuffer hql = new StringBuffer();
		hql.append("from Doctor as doctor ");
		if(queryConditions!=null){
			String doctorName = queryConditions.get("doctorName");
			String doctorMobile = queryConditions.get("doctorMobile");
			hql.append(" where ");
			hql.append("  doctor.doctorName like '%" + doctorName + "%'");
			if (StringUtils.hasLength(doctorMobile)) {
				hql.append("  and  doctor.doctorMobile like '%" + doctorMobile
						+ "%'");
			}
		}
		List<Doctor> doctors=doctorHibernateDao.getDoctors(hql.toString());
		List<DoctorVO> vos=new ArrayList<DoctorVO>();
		if(doctors!=null){
			for(Doctor doctor:doctors){
				DoctorVO doctorVO=new DoctorVO();
				doctorVO.setDoctorID(doctor.getUserID());
				doctorVO.setDoctorName(doctor.getDoctorName());
				doctorVO.setDoctorPoints(doctor.getDoctorPoints());
				doctorVO.setDoctorMobile(doctor.getDoctorMobile());
				doctorVO.setDoctorStatus(doctor.getDoctorStatus());
				doctorVO.setDoctorJobTitle(doctor.getDoctorJobTitle());
				doctorVO.setDoctorTargetDept(doctor.getDoctorTargetDept());
				if(doctor.getEmployeeMR()!=null){
					doctorVO.setEmployeeName(doctor.getEmployeeMR().getEmployeeName());
				}
				if(doctor.getRegion()!=null){
					doctorVO.setRegionName(doctor.getRegion().getRegionName());
				}
				vos.add(doctorVO);
			}
		}
		Page page = new ListPage(vos, pageContext.getPageNumber(), pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
		
	}

	@Override
	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(Doctor.REF)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param doctorDao
	 *            the doctorDao to set
	 */
	@Resource
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}

	/**
	 * @param doctorHibernateDao
	 *            the doctorHibernateDao to set
	 */
	@Resource
	public void setDoctorHibernateDao(DoctorHibernateDao doctorHibernateDao) {
		this.doctorHibernateDao = doctorHibernateDao;
	}

	@Override
	public boolean updateDoctorPoints(int doctorID, int newPoints) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.updateDoctorPoints(doctorID, newPoints);
	}

	public int getDoctorPointsByPhone(String phone) {
		return doctorHibernateDao.getDoctorPointsByPhone(phone);
	}
	 public List<Rank> getDoctorRank(){
		 return doctorHibernateDao.getDoctorRank();
	 }

	@Override
	public String activeDoctor(int doctorID, String doctorActivateOption) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.activeDoctor(doctorID, doctorActivateOption);
	}

	@Override
	public boolean lockDoctor(int docotrID) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.lockDoctor(docotrID);
	}

	@Override
	public boolean unLockDoctor(int doctorID) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.unLockDoctor(doctorID);
	}

	@Override
	public boolean updatePassword(int doctorID, String newPassword) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.updatePassword(doctorID, newPassword);
	}

	@Override
	public boolean updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.updateDoctor(doctor);
	}

	@Override
	public boolean deleteDoctorByRegionID(int regionID) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.deleteDoctorByRegionID(regionID);
	}
	 public Doctor getDoctorByRegionID(int regionID){
		 return doctorHibernateDao.getDoctorByRegionID(regionID);
	 }

	@Override
	public int getDoctorPointsByID(int doctorID) {
		// TODO Auto-generated method stub
		return doctorHibernateDao.getDoctorPointsByID(doctorID);
	}
	 

}
