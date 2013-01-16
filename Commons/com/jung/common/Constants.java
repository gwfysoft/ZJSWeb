package com.jung.common;



/**
 * 常量定义类.
 * 
 * @version Revision: V1.0 2012-8-22 上午12:25:22
 * @author GuoJun mailto: jackson@highcolu.com
 */
public class Constants {
	/**
	 * @Description: 医师常量
	*  @version Revision: V1.0 2012-12-16 下午4:40:23
	*  @author GuoJun mailto: jackson@highcolu.com
	 */
	public static class Doctor {
		public static final Integer NONACTIVATED = 0;//未激活
		public static final Integer ACTIVE = 1;//激活
		public static final Integer INACTIVE = 2;//不活动
		
		public static final Integer POINTS = 0;//医师默认积分
	}
	/**
	 * 题目常量
	 * @author yy
	 *
	 */
	public static class Question{
		public static final Integer   UNAUDITED=0;//未审核
		public static final Integer   AUDITED=0;//已审核
	}
	/**
	 * @Description: 人员常量
	*  @version Revision: V1.0 2012-12-16 下午4:40:45
	*  @author GuoJun mailto: jackson@highcolu.com
	 */
	public static class User {
		public static final Integer DOCTOR = 0;//医师
		public static final Integer EMPLOYEE = 1;//内部员工
		public static final Integer ADMIN = 2; //系统管理员
		public static final Integer POINTS = 0;//员工默认积分
	}
	/**
	 * 分页行数大小键值定义常量.
	 */
	public static final String PAGE_SIZE = ".page.size";
	/**
	 * 默认的数据分页行数.
	 */
	public static final Integer DEFAULT_PAGE_SIZE = 10;
	public static final Integer DEFAULT_REGION_MAX_SIZE = 7;
	
	public static final Integer DEFAULT_REGION_MAX_LAYER = 100;
	public static final Integer DEFAULT_REGION_MAX_TOP = 100;

	/**
	 * 基本数据类型字符串数据常量定义.
	 */
	public static final String[] BASE_DATA_TYPE = new String[] { "Integer",
			"int", "Long", "long", "Boolean", "bool", "BigDecimal", "String",
			"Date", "Double", "double", "Float", "float" };
}

