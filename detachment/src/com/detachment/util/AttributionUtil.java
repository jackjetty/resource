package com.detachment.util;

import java.util.List;

import com.detachment.math.PointMath;

public class AttributionUtil {
	private static final int offset=1000;  
	private static final  double eps=1e-8; 
	private static double cross(PointMath pi,PointMath pj,PointMath pk){ // (pi,pj)X(pi,pk)  
	    return (pj.getPointX()-pi.getPointX())*(pk.getPointY()-pi.getPointY())-(pj.getPointY()-pi.getPointY())*(pk.getPointX()-pi.getPointX());  
	}  
	public static int InPolygon(List<PointMath> pointLine ,PointMath pt ){  
		 PointMath q=new PointMath();  
		 int on_edge=1;
		 int i=0,counter=0; 
		 int len=pointLine.size();
		 while(i<len){  
		   q.setPointX((float)Math.random()+offset);//随机取一个足够远的点q  
		   q.setPointY((float)Math.random()+offset);//以p为起点q为终点做射线L  
		   for(counter=i=0;i<len;i++){//依次对多边形的每条边进行考察  
		    if(Math.abs(cross(pt,pointLine.get(i) ,pointLine.get((i+1)%len) ))<eps &&  
		    (pointLine.get(i).getPointX()-pt.getPointX())*(pointLine.get((i+1)%len).getPointX()-pt.getPointX())<eps && (pointLine.get(i).getPointY()-pt.getPointY())*(pointLine.get((i+1)%len).getPointY()-pt.getPointY())<eps)  
		      return on_edge; //点p在边上,返回on_edge  
		    else if(Math.abs(cross(pt,q,pointLine.get(i)))<eps) break; //点arr[i]在射线pq上，停止本循环，另取q  
		    else if(cross(pt,pointLine.get(i),q)*cross(pt,pointLine.get((i+1)%len),q)<-eps &&  
		      cross(pointLine.get(i),pt,pointLine.get((i+1)%len))*cross(pointLine.get(i),q,pointLine.get((i+1)%len))<-eps)  
		      counter++;  
		   }  
		 }  
		 return counter&1;  
	 }  
	  
	
}