package codeSys.java.utils;

/**
 * 
* Copyright © 2018 A Little Bao. All rights reserved.
* 
* @ClassName: Tools.java
* @Description: 常用工具类
* 功能目录
* 1,根据两点经纬度计算距离  getDistance
* @version: v1.0.0
* @author: Alibao
* @date: 2018年2月11日 上午9:37:14 
*
* Modification History:
* Date         Author          Version            Description
*---------------------------------------------------------*
* 2018年2月11日     Alibao           v1.0.0               修改原因
 */
public class Tools {
	//地球平均半径  
    private static final double EARTH_RADIUS = 6378137;  
    //把经纬度转为度（°）  
    private static double rad(double d){  
       return d * Math.PI / 180.0;  
    }  
      
    /**
     * 
    * @Description: 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
    *
    * @param:lng1第一点经度、lat1第一点纬度
    * @param lng2第二点经度、lat2第二点纬度
    * @return：两点间距离
    * @author: Alibao
    * @date: 2018年2月11日 上午10:11:38
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2){  
       double radLat1 = rad(lat1);  
       double radLat2 = rad(lat2);  
       double a = radLat1 - radLat2;  
       double b = rad(lng1) - rad(lng2);  
       double s = 2 * Math.asin(  
            Math.sqrt(  
                Math.pow(Math.sin(a/2),2)   
                + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)  
            )  
        );  
       s = s * EARTH_RADIUS;  
       s = Math.round(s * 10000) / 10000;  
       return s;  
    }  
}
