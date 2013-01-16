package com.jung.questionanswerrecord.dao.impl;

import java.util.List;


import com.hp.util.Page;
import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.hp.xquery.SimpleQuery;
import com.jung.questionanswerrecord.dao.QuestionAnswerRecordDao;
import com.jung.questionanswerrecord.model.QuestionAnswerRecord;

public class QuestionAnswerRecordDaoImpl extends HibernateBaseDao implements QuestionAnswerRecordDao {

	@Override
	public Class getEntityType() {
		// TODO Auto-generated method stub
		return QuestionAnswerRecord.class;
	}



}
