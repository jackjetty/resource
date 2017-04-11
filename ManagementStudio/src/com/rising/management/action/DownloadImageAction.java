package com.rising.management.action;
	import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

	import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

	import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

	@Controller("downloadImageAction")
	public class DownloadImageAction {
		private String type;
		private String svg;
		private String filename;
		private Integer banben;
		
		public String dowloadImage(){
			if(banben!=null&&banben==10){
				svg=svg.replaceAll("stroke-width=", "text-stroke-width=");
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			ServletOutputStream out=null;
			try {
				out = response.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}  
			filename = filename==null?"chart":filename;  
			 if (null != type && null != svg) {  
		            svg = svg.replaceAll(":rect", "rect");  
		            String ext = "";  
		            Transcoder t = null;  
		            if (type.equals("image/png")) {  
		                ext = "png";  
		                t = new PNGTranscoder();  
		            } else if (type.equals("image/jpeg")) {  
		                ext = "jpeg";  
		                t = new JPEGTranscoder();
		                t.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(0.8));
		            } else if (type.equals("application/pdf")) {  
		                ext = "pdf";  
		                t =(Transcoder) new PDFTranscoder(); 
		            } else if(type.equals("image/svg+xml"))   
		                ext = "svg";     
		            response.addHeader("Content-Disposition", "attachment; filename="+ filename + "."+ext);  
		            response.addHeader("Content-Type", type);  
		              
		            if (null != t) {  
		                TranscoderInput input = new TranscoderInput(new StringReader(svg));  
		                TranscoderOutput output = new TranscoderOutput(out);  
		                try {  
		                    t.transcode(input, output);  
		                } catch (TranscoderException e) {  
		                    try {
								out.print("Problem transcoding stream. See the web logs for more details.");
							} catch (IOException e1) {
								e1.printStackTrace();
							}  
		                    e.printStackTrace();  
		                }  
		            } else if (ext.equals("svg")) {  
		                OutputStreamWriter writer;
						try {
							writer = new OutputStreamWriter(out, "UTF-8");
							writer.append(svg);  
			                writer.close();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}  
		                  
		            } else
						try {
							out.print("Invalid type: " + type);
						} catch (IOException e) {
							e.printStackTrace();
						}  
		        } else {  
		            response.addHeader("Content-Type", "text/html");  
		            try {
						out.println("Usage:\n\tParameter [svg]: The DOM Element to be converted." +  
						        "\n\tParameter [type]: The destination MIME type for the elment to be transcoded.");
					} catch (IOException e) {
						e.printStackTrace();
					}  
		        }  
		        try {
					out.flush();
					out.close(); 
				} catch (IOException e) {
					e.printStackTrace();
				}  
		         
	          
			return null;
		}
		
		
		
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getSvg() {
			return svg;
		}
		public void setSvg(String svg) {
			this.svg = svg;
		}
		public String getFilename() {
			return filename;
		}
		public void setFilename(String filename) {
			this.filename = filename;
		}
		public Integer getBanben() {
			return banben;
		}
		public void setBanben(Integer banben) {
			this.banben = banben;
		}
		
		
		
	}



