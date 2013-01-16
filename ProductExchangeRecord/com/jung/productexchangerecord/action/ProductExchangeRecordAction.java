package com.jung.productexchangerecord.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.product.model.Product;
import com.jung.product.service.ProductService;
import com.jung.productexchangerecord.model.ProductExchangeRecord;
import com.jung.productexchangerecord.service.ProductExchangeRecordService;
import com.jung.common.JqueryGridAction;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.service.DoctorService;

public class ProductExchangeRecordAction extends JqueryGridAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(ProductExchangeRecordAction.class);
	
	private ProductExchangeRecordService productExchangeRecordService;
	private DoctorService doctorService;
	private ProductService productService;
	private String doctorID;
	private String productID;
	private String productPoints;
	private String result;
	
	//添加医师产品兑换记录 并更新医师积分
	public String addProductExchangeRecord(){
		Doctor doctor=doctorService.getDoctorById(Integer.parseInt(doctorID));
		Product product=productService.findProductByID(Integer.parseInt(productID));
		ProductExchangeRecord productExchangeRecord=new ProductExchangeRecord();
		productExchangeRecord.setDoctor(doctor);
		productExchangeRecord.setProduct(product);
		int doctorPoints=doctorService.getDoctorPointsByID(Integer.parseInt(doctorID));
		int newPoints=doctorPoints-Integer.parseInt(productPoints);
		if(doctorService.updateDoctorPoints(Integer.parseInt(doctorID), newPoints)){
			logger.info("更新医师积分成功");
		}
		if(productExchangeRecordService.addProductExchangeRecord(productExchangeRecord)){
			logger.info("添加产品兑换记录成功");
			this.result=SUCCESS;
			return SUCCESS;
		}
		return FAIL;
	}

	@Resource
	public void setProductExchangeRecordService(
			ProductExchangeRecordService productExchangeRecordService) {
		this.productExchangeRecordService = productExchangeRecordService;
	}

	public void setDoctorID(String doctorID) {
		this.doctorID = doctorID;
	}

	public String getDoctorID() {
		return doctorID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductID() {
		return productID;
	}
	@Resource
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

	public void setProductPoints(String productPoints) {
		this.productPoints = productPoints;
	}

	public String getProductPoints() {
		return productPoints;
	}

}
