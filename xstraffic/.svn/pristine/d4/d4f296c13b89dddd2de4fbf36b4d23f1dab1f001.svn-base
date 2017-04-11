package com.traffic.util;

 


public class PathUtil {
	public String getWebClassesPath() {
		String path = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		return path.substring(1, path.length()); 
	} 
	public String getWebInfPath() {
		String path = this.getWebClassesPath();
		if (path.indexOf("WEB-INF") > 0)
			path = path.substring(0, path.indexOf("WEB-INF") + 8); 
		return path;
	}

	public String getWebRoot() {
		String path = getWebClassesPath();
		if (path.indexOf("WEB-INF/classes") > 0)
			path = path.substring(0, path.indexOf("WEB-INF/classes")); 
		return path;
	} 
	public static void main(String[] args) {
		// F:/EApp/logistic/WebRoot/WEB-INF/classes/
		System.out.println((new PathUtil()).getWebRoot());
	}
}