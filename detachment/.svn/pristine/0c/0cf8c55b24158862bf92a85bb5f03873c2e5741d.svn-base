package com.detachment.web.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.detachment.pojo.TbAward;
import com.detachment.pojo.TbCandidate;
import com.detachment.pojo.TbVote;

public class ServiceTest {

	CandidateService service;
	AwardService service1;
	VoteService voteService;
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = 
		   new FileSystemXmlApplicationContext("src/applicationContext_test.xml");
		service = (CandidateService) ctx.getBean("candidateService");
		service1 = (AwardService) ctx.getBean("awardService");
		voteService = (VoteService) ctx.getBean("voteService");
		}
	
	@Test
	public void test1(){
		TbAward b=new TbAward();
		TbAward b1=new TbAward();
		TbAward b2=new TbAward();
		TbAward b3=new TbAward();
		TbAward b4=new TbAward();
		TbAward b5=new TbAward();
		TbAward b6=new TbAward();
		TbAward b7=new TbAward();
		TbAward b8=new TbAward();
		b.setDesc_("2006年获浙江省首届最感人出租车司机。2006年荣获绍兴市精神文明十佳。");
		b1.setDesc_("2007年获浙江省交通十大感动人物。");
		b2.setDesc_("获2007-2010浙江省出租车服务明星，获诸暨市十佳效能行风建设标兵，诸暨市十大文明事先进个人，诸暨市十佳出租车司机。");
		b3.setDesc_("2008年荣获省级文明优秀驾驶员，省级优秀阳光车友。");
		b4.setDesc_("2009年感动诸暨十佳年度新闻人物。");
		b5.setDesc_("1997年－2011年被诸暨市公安交警大队评为先进驾驶员，2012年荣获浙江省“创先争优闪光言行之星”。");
		b6.setDesc_("中共绍兴市优秀共产党员，2012诸暨身边道德模范。");
		b7.setDesc_("2013年评为诸暨市十佳最美的士；浙江省十大最美的士；最美暨阳人、身边的好人。");
		b8.setDesc_("2014年评为绍兴市交通“三好”好形象；绍兴市“绍兴好人”五星级驾驶员等荣誉");
		service1.addTbAward(b);
		service1.addTbAward(b1);
		service1.addTbAward(b2);
		service1.addTbAward(b3);
		service1.addTbAward(b4);
		service1.addTbAward(b5);
		service1.addTbAward(b6);
		service1.addTbAward(b7);
		service1.addTbAward(b8);
		TbCandidate c=new TbCandidate();
		c.setName("杨金海");
		c.setSex("男");
		c.setBirth("1960.12");
		c.setPoliticsStatus("党员");
		c.setNation("汉");
		c.setDegree("高中");
		c.setWorkUnit("诸暨市长途汽车运输有限公司客运出租分公司出租车驾驶员文明车队队长");
		c.setStartWork("18年");
		c.setTel("0575-87222422");
		c.setFeeling("爱岗敬业、无私奉献");
		c.setIntro("1.踊跃参与全市各种重大文明创建活动，展现诸暨的士文明使者风采。2.连续9年开展免费接送高考生献爱心活动3.心系地震灾区踊跃捐款。4.关爱残疾人、白血病人和困难职工。5.配合行业管理部门，完成突发应急任务。6.关爱老人，3年来坚持免费接送80多岁老太太。7.帮助困难人员献爱心8.宁波、诸暨二地联手救伤员。");
		Set t=new HashSet();
		t.add(b);
		t.add(b1);
		t.add(b2);
		t.add(b3);
		t.add(b4);
		t.add(b5);
		t.add(b6);
		t.add(b7);
		t.add(b8);
//		c.setAwards(t);
		service.testAdd(c);
	}
	
	@Test
	public void test2(){
		TbVote vote=new TbVote();
		vote.setTopicName("三有三好驾驶员评选");
		voteService.insertVote(vote);
	}
	
	@Test
	public void test3(){
		TbVote vote=voteService.getVoteById(2);
		System.out.println(vote.getCandidate().size());;
	}
	
	@Test
	public void test4(){
		voteService.getVoteRes();
	}

}
