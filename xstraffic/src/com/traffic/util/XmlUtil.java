package com.traffic.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map; 
import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XmlUtil {
	protected static String PREFIX_CDATA = "<![CDATA[";
	protected static String SUFFIX_CDATA = "]]>";
	public static Map<String, String> xml2Map(String xmlStr)
			throws JDOMException, IOException {
		Map<String, String> rtnMap = new HashMap<String, String>();
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(new StringReader(xmlStr));
		// 得到根节点  
		Element root = doc.getRootElement();
		String rootName = root.getName();
		rtnMap.put("root.name", rootName);
		// 调用递归函数，得到所有最底层元素的名称和值，加入map中  
		convert(root, rtnMap, rootName);
		return rtnMap;
	}

	/**
	 * 递归函数，找出最下层的节点并加入到map中，由xml2Map方法调用。
	 * 
	 * @param e
	 *            xml节点，包括根节点
	 * @param map
	 *            目标map
	 * @param lastname
	 *            从根节点到上一级节点名称连接的字串
	 */
	public static void convert(Element e, Map<String, String> map,
			String lastname) {
		if (e.getAttributes().size() > 0) {
			Iterator it_attr = e.getAttributes().iterator();
			while (it_attr.hasNext()) {
				Attribute attribute = (Attribute) it_attr.next();
				String attrname = attribute.getName();
				String attrvalue = e.getAttributeValue(attrname);
				map.put(lastname + "." + attrname, attrvalue);
			}
		}
		List children = e.getChildren();
		Iterator it = children.iterator();
		while (it.hasNext()) {
			Element child = (Element) it.next();
			String name = lastname + "." + child.getName();
			// 如果有子节点，则递归调用
			if (child.getChildren().size() > 0) {
				convert(child, map, name);
			} else {
				// 如果没有子节点，则把值加入map
				map.put(name, child.getText());
				// 如果该节点有属性，则把所有的属性值也加入map
				if (child.getAttributes().size() > 0) {
					Iterator attr = child.getAttributes().iterator();
					while (attr.hasNext()) {
						Attribute attribute = (Attribute) attr.next();
						String attrname = attribute.getName();
						String attrvalue = child.getAttributeValue(attrname);
						map.put(name + "." + attrname, attrvalue);
					}
				}
			}
		}
	}
	
	public static XStream init(boolean isAddCDATA) {
		XStream xstream = null;
		if (isAddCDATA) {
			xstream = new XStream(new XppDriver() {
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						protected void writeText(QuickWriter writer, String text) {
							if (!text.startsWith(PREFIX_CDATA)) {
								text = PREFIX_CDATA + text + SUFFIX_CDATA;
							}
							writer.write(text);
						}
					};
				};
			});
		} else {
			xstream = new XStream();
		}
		xstream.autodetectAnnotations(true);
		/*Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("xml", com.traffic.bean.BaseBean.class);
        clazzMap.put("xml", com.traffic.bean.req.ClickEventReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.ImageReqBean.class); 
        clazzMap.put("xml", com.traffic.bean.req.LinkReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.LocationReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.ScanEventReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.ScribeEventReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.TextReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.VideoReqBean.class);
        clazzMap.put("xml", com.traffic.bean.req.VoiceReqBean.class);
        clazzMap.put("xml", com.traffic.bean.res.ImageResBean.class);
        
        clazzMap.put("xml", com.traffic.bean.res.NewsResBean.class);
        clazzMap.put("xml", com.traffic.bean.res.TextResBean.class);
        clazzMap.put("Image", com.traffic.bean.res.object.ImageResObject.class);
        clazzMap.put("item", com.traffic.bean.res.object.ImageResObject.class);
        clazzMap.put("Music", com.traffic.bean.res.object.MusicResObject.class);
        clazzMap.put("Video", com.traffic.bean.res.object.VideoResObject.class);
        clazzMap.put("Voice", com.traffic.bean.res.object.VoiceResObject.class);
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next();
            xstream.alias(m.getKey(), m.getValue());
        }*/
        return xstream; 
	}

}
