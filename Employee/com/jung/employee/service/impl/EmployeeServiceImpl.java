package com.jung.employee.service.impl;

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
import com.jung.doctor.model.Rank;
import com.jung.employee.dao.EmployeeDao;
import com.jung.employee.dao.EmployeeHibernateDao;
import com.jung.employee.model.Employee;
import com.jung.employee.model.EmployeeVO;
import com.jung.employee.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	private EmployeeHibernateDao employeeHibernateDao;

	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.addEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.deleteEmployee(employee);
	}

	public boolean deleteEmployeeById(int employeeId) {
		return employeeHibernateDao.deleteEmployeeById(employeeId);
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.getEmployeeById(employeeId);
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		String hql = "from Employee";
		return employeeHibernateDao.getEmployees(hql);
	}

	public int getEmployeePointsByPhone(String phone) {
		return employeeHibernateDao.getEmployeePointsByPhone(phone);
	}

	public List<Rank> getEmployeeRank() {
		return employeeHibernateDao.getEmployeeRank();
	}

	/**
	 * @param employeeDao
	 *            the employeeDao to set
	 */
	@Resource
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	/**
	 * @param employeeHibernateDao
	 *            the employeeHibernateDao to set
	 */
	@Resource
	public void setEmployeeHibernateDao(
			EmployeeHibernateDao employeeHibernateDao) {
		this.employeeHibernateDao = employeeHibernateDao;
	}

	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
		// SimpleQuery query = employeeDao.getSimpleQuery();
		// if(queryConditions!=null){
		// String employeeName = queryConditions.get("employeeName").trim();
		// if(StringUtils.hasLength(employeeName)){
		// query.addFilter(Restrictions.like("employeeName", "%" + employeeName
		// + "%"));
		// }
		// }
		// if (orderProperty != null && !"".equals(orderProperty)) {
		// if (orderMode.equals("asc")) {
		// query.addOrder(orderProperty, true);
		// } else if (orderMode.equals("desc")) {
		// query.addOrder(orderProperty, false);
		// }
		// }
		// Page page = employeeDao.find(query, pageContext.getPageNumber(),
		// pageContext.getPageSize());
		// pageContext.setPage(page);
		// return pageContext;
		StringBuffer hql = new StringBuffer();
		boolean isAdd=false;
		boolean isAddM=false;
		hql.append("from Employee as employee ");
		if(queryConditions!=null){
			String employeeName = queryConditions.get("employeeName");
			String employeeMobile = queryConditions.get("employeeMobile");
			String employeeType=queryConditions.get("employeeType");
			if(StringUtils.hasLength(employeeName)||StringUtils.hasLength(employeeMobile)||StringUtils.hasLength(employeeType)){
				hql.append(" where ");
			}
			if(StringUtils.hasLength(employeeName)){
				hql.append("  employee.employeeName like '%" + employeeName + "%'");
				isAdd=true;
			}
			if (StringUtils.hasLength(employeeMobile)) {
				if(isAdd){
					hql.append("  and ");
				}
				hql.append(" employee.employeeMobile like '%" + employeeMobile
						+ "%'");
				isAddM=true;
			}
			if(StringUtils.hasLength(employeeType)){
				if(isAdd||isAddM){
					hql.append("  and ");
				}
				hql.append("  employee.employeeType like '%" + employeeType
						+ "%'");
			}
		}
		List<Employee> employees = employeeHibernateDao.getEmployees(hql
				.toString());
		List<EmployeeVO> vos = new ArrayList<EmployeeVO>();
		if (employees != null) {
			for (Employee employee : employees) {
				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmployeeID(employee.getUserID());
				employeeVO.setEmployeeName(employee.getEmployeeName());
				employeeVO.setEmployeeMobile(employee.getEmployeeMobile());
				employeeVO.setEmployeeNumber(employee.getEmployeeNumber());
				employeeVO.setEmployeePoints(employee.getEmployeePoints());
				employeeVO.setEmployeeType(employee.getEmployeeType());
				employeeVO.setPassword(employee.getPassword());
				if (employee.getParentEmployee() != null) {
					employeeVO.setParentEmployeeName(employee
							.getParentEmployee().getEmployeeName());
				}
				if (employee.getRegion() != null) {
					employeeVO.setRegionName(employee.getRegion()
							.getRegionName());
				}
				vos.add(employeeVO);
			}
		}
		Page page = new ListPage(vos, pageContext.getPageNumber(), pageContext
				.getPageSize());
		pageContext.setPage(page);
		return pageContext;
	}

	@Override
	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(Employee.REF)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updatePassword(int employeeID, int newPassword) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.updatePassword(employeeID, newPassword);
	}

	@Override
	public List<Employee> getEmployeeByParentID(int parentID) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.getEmployeeByParentID(parentID);
	}

	@Override
	public List<Rank> getDistrictRank() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.updateEmployee(employee);
	}


	@Override
	public  List<Employee> getEmployeeByRegionID(int regionID) {
		// TODO Auto-generated method stub
		return employeeHibernateDao.getEmployeeByRegionID(regionID);
	}

}
