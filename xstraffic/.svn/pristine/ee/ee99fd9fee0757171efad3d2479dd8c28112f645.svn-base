package com.traffic.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.traffic.web.service.BakPicService;


@Controller("bakPicAction")
public class BakPicAction {
	private Integer pageSize;
	private Integer pageIndex;
	private String startTime;
	private String endTime;
	private String picIds;
	private String picLocalStores;

	@Autowired
	BakPicService bakPicService;
	private HashMap<String, Object> result;

	public String doBakPic() {

		return "success";
	}

	public String getBakPic() {
		result = bakPicService.getBakPic(startTime, endTime, pageSize,
				pageIndex);
		return "success";
	}
	
	public String deleteBakPic(){
		String[] vss = picLocalStores.split(",");
		for (String vs : vss) {
			if (!"".equals(vs)) {
				File file = new File(vs);
				if (!file.isDirectory()) {
					file.delete();
				}
			}

		}
		result = bakPicService.deleteBakPic(picIds);
		return "success";
	}
	
	public void toImageDown(){
		HttpServletResponse response = ServletActionContext.getResponse();
		String[] vss = picLocalStores.split(",");
		File[] srcfile=new File[vss.length-1];
		for(int i=1;i<vss.length;i++){
			File f=new File(vss[i]);
			srcfile[i-1]=f;
		}
		response.reset();
		response.setContentType("zip");  
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=images.zip" );
		zipFiles(srcfile,response);
	}
	public static void zipFiles(File[] srcfile,HttpServletResponse response){  
        byte[] buf=new byte[1024];  
        try {  
            //ZipOutputStream类：完成文件或文件夹的压缩  
            ZipOutputStream out=new ZipOutputStream(response.getOutputStream());  
            for(int i=0;i<srcfile.length;i++){  
                FileInputStream in=new FileInputStream(srcfile[i]);  
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;  
                while((len=in.read(buf))>0){  
                    out.write(buf,0,len);  
                }  
                out.closeEntry();  
                in.close();  
            }  
            out.close();    
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }
	
	

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public HashMap<String, Object> getResult() {
		return result;
	}

	public void setResult(HashMap<String, Object> result) {
		this.result = result;
	}

	public String getPicIds() {
		return picIds;
	}

	public void setPicIds(String picIds) {
		this.picIds = picIds;
	}

	public String getPicLocalStores() {
		return picLocalStores;
	}

	public void setPicLocalStores(String picLocalStores) {
		this.picLocalStores = picLocalStores;
	}

}
