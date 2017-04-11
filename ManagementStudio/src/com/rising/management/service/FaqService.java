package com.rising.management.service;

import java.util.*;
import com.rising.management.bean.Faq;

public interface FaqService {
	public HashMap<String, Object> getFaqByPage(Integer pageIndex,
			Integer pageSize);

	public HashMap<String, Object> getPageDate(Integer pageIndex,
			Integer pageSize, String order, String sort, Faq cdtFaq);

	public HashMap<String, Object> getFaqById(Integer id);

	public HashMap<String, Object> updateFaq(Faq faq);

	public HashMap<String, Object> replaceFaqRand(Integer curRowId,
			Integer repRowId);
}