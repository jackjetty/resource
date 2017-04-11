package com.detachment.web.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.detachment.pojo.TbProblems;
import com.detachment.web.service.TbProblemsService;

@Controller("problemsAction")
public class ProblemsAction {
	private HashMap<String,Object> result;
	private ArrayList<TbProblems> listProblem;
	
	@Autowired
	TbProblemsService tbProblemsService;
	
	
	public String doOnlineStudy(){
		return "success";
	}
	public String getProblemJsp(){
		listProblem=tbProblemsService.getProblemsByNum(800);
		return "success";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public String saveProblem(){
//		String filePath = "D:\\problem.txt";
//        String aa=readTxtFile(filePath);
//        JSONObject json=JSONObject.fromObject(aa);
//        JSONArray arry=json.getJSONArray("data");
//        for (int i = 0; i < arry.size(); i++) {
//        	TbProblems tp=new TbProblems();
//        	JSONObject jo = (JSONObject) arry.get(i);
//        	tp.setProblem(jo.getString("question"));
//        	tp.setResultA(jo.getString("optionA"));
//        	tp.setResultB(jo.getString("optionB"));
//        	tp.setResultC(jo.getString("optionC"));
//        	tp.setResultD(jo.getString("optionD"));
//        	if("1".equals(jo.getString("answer"))){
//        		tp.setAnswer("A");
//        	}
//        	if("2".equals(jo.getString("answer"))){
//        		tp.setAnswer("B");
//        	}
//        	if("3".equals(jo.getString("answer"))){
//        		tp.setAnswer("C");
//        	}
//        	if("4".equals(jo.getString("answer"))){
//        		tp.setAnswer("D");
//        	}
//        	if(!"".equals(jo.getString("pic"))){
//        		tp.setImageUrl("http://www.jsrzx.com/resources/"+jo.getString("pic"));
//        	}else{
//        		tp.setImageUrl("");
//        	}
//        	
//        	tp.setExplain(jo.getString("explain"));
//        	result=tbProblemsService.saveTbProblem(tp);
//        	
//        }
//		
//		return "success";
//	}

	
	
	public static String readTxtFile(String filePath){
		String result="";
        try {
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null){
                        //System.out.println(lineTxt);
                        result+=lineTxt;
                    }
                    read.close();
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return result;
    }
	
	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public ArrayList<TbProblems> getListProblem() {
		return listProblem;
	}

	public void setListProblem(ArrayList<TbProblems> listProblem) {
		this.listProblem = listProblem;
	}
	
	
	
	
}
