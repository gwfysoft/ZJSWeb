package com.jung.doctor.service;

import java.util.List;

import com.jung.common.JqueryGridService;
import com.jung.doctor.model.Doctor;
import com.jung.doctor.model.Rank;

public interface DoctorService extends JqueryGridService {
	  public boolean addDoctor(Doctor doctor);
	  public boolean updateDoctor(Doctor doctor);
      public boolean deleteDoctor(Doctor doctor);
      public boolean deleteDoctorByID(int doctorID);
      public Doctor getDoctorById(int doctorId);
      public List<Doctor> getDoctors();
      
      public boolean updateDoctorPoints(int doctorID,int newPoints);//医师回答问题后根据规则累积积分,医师兑换产品后扣除积分
      public int getDoctorPointsByPhone(String phone);//根据医师手机号查询积分           
      public List<Rank> getDoctorRank();//医师(激活的)累积积分排名,查询前50名,降序
      public String activeDoctor(int doctorID,String doctorActivateOption);//激活医师，返回医师密码
      public boolean lockDoctor(int docotrID);//设置医师活动
      public boolean unLockDoctor(int doctorID);//设置医师不活动
      public boolean updatePassword(int doctorID,String newPassword);//医师修改密码
      public boolean  deleteDoctorByRegionID(int regionID);//根据地区ID删除医师
      public Doctor getDoctorByRegionID(int regionID);//根据地区ID查找医师
      public int getDoctorPointsByID(int doctorID);//根据医师ID获取医师积分
      
}
