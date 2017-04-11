package com.rising.mobilepayment.job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.rising.mobilepayment.commom.GetApplicationContext;
import com.rising.mobilepayment.service.FlowService;
public class FlowCheckJob extends QuartzJobBean {
	private FlowService flowService;
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		ApplicationContext applicationContext = GetApplicationContext
				.getApplicationContext();
		flowService=(FlowService) applicationContext.getBean("flowService");
		flowService.flowOrderInfoQuery();
	}
}