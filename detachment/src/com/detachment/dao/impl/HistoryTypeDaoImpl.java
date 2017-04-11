package com.detachment.dao.impl;

import org.springframework.stereotype.Repository;

import com.detachment.dao.HistoryTypeDao;
import com.detachment.pojo.TbHistoryType;

@Repository("historyTypeDao")
public class HistoryTypeDaoImpl extends BaseDaoImpl<TbHistoryType> implements HistoryTypeDao{

}
