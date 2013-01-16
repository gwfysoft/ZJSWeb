package com.jung.product.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.common.JqueryGridAction;
import com.jung.product.model.Product;
import com.jung.product.service.ProductService;
import com.jung.productexchangerecord.model.ProductExchangeRecord;
import com.jung.productexchangerecord.service.ProductExchangeRecordService;

public class ProductAction extends JqueryGridAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(ProductAction.class);

	private String result;
	private String productID;
	private String productValidStartDate;
	private String productValidEndDate;

	private Product product;
	private ProductService productService;
	private ProductExchangeRecordService productExchangeRecordService;
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
	public String addProduct(){
		Product temp;
		if(productID!=null&&!productID.equals("")){
			if(product==null){
				product=productService.findProductByID(Integer.parseInt(productID));
				this.result=SUCCESS;
				return SUCCESS;
			}else{
				temp=productService.findProductByID(Integer.parseInt(productID));
				temp.setProductName(product.getProductName());
				temp.setProductOrder(product.getProductOrder());
				temp.setProductPoints(product.getProductPoints());
				temp.setProductValidStartDate(product.getProductValidStartDate());
				temp.setProductValidEndDate(product.getProductValidEndDate());
				if(productService.updateProduct(temp)){
					logger.info("更新产品成功");
					this.result=SUCCESS;
					return SUCCESS;
				}
			}
		}else{
			try {
				if(productValidStartDate!=null&&!productValidStartDate.equals("")){
					Date 	startDate = simpleDateFormat.parse(productValidStartDate);
					product.setProductValidStartDate(startDate);
				}
				if(productValidEndDate!=null&&!productValidEndDate.equals("")){
					Date     endDate=simpleDateFormat.parse(productValidEndDate);
					product.setProductValidEndDate(endDate);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			product.setLastUpdate(new Date());
			if(productService.addProduct(product)){
				logger.info("添加产品成功");
				this.result=SUCCESS;
				return SUCCESS;
			}
		}
		return FAIL;
		
	}

	public String deleteProduct(){
		Product product=productService.findProductByID(Integer.parseInt(productID));
		//先删除产品兑换记录
		Set<ProductExchangeRecord> set=product.getRecordSet();
		for(ProductExchangeRecord productExchangeRecord:set){
			productExchangeRecordService.deleteProductExchangeRecord(productExchangeRecord);
			logger.info("删除产品兑换记录成功");
		}
		if(productService.delteProductByID(Integer.parseInt(productID))){
			logger.info("删除产品成功");
			this.result=SUCCESS;
			return SUCCESS;
		}else{
			return FAIL;
		}
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param productService the productService to set
	 */
	@Resource
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}
	/**
	 * @param productID the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	 * @param productValidEndDate the productValidEndDate to set
	 */
	public void setProductValidEndDate(String productValidEndDate) {
		this.productValidEndDate = productValidEndDate;
	}

	/**
	 * @return the productValidEndDate
	 */
	public String getProductValidEndDate() {
		return productValidEndDate;
	}

	/**
	 * @param productValidStartDate the productValidStartDate to set
	 */
	public void setProductValidStartDate(String productValidStartDate) {
		this.productValidStartDate = productValidStartDate;
	}

	/**
	 * @return the productValidStartDate
	 */
	public String getProductValidStartDate() {
		return productValidStartDate;
	}
@Resource
	public void setProductExchangeRecordService(
			ProductExchangeRecordService productExchangeRecordService) {
		this.productExchangeRecordService = productExchangeRecordService;
	}
}
