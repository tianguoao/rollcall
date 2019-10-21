package com.tga.rollcall.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 位置计算工具类
 * @author  Mario 
 * @version 2019年10月19日 下午5:51:31
 * Class: LocationUtils.java
 */
public class LocationUtils {
    /**
     * 计算两组经纬度之间相差的距离
     * @param a
     * @param b
     * @return
     */
    public static Double getDistance(Position a, Position b) {
        if (a != null && b != null) {
            return XO(a, b);
        } else {
            return 0d;
        }
    }

    private static Double XO(Position a, Position b) {
        a.setLng(OD(a.getLng(), -180d, 180d));
        a.setLat(SD(a.getLat(), -74d, 74d));
        b.setLng(OD(b.getLng(), -180d, 180d));
        b.setLat(SD(b.getLat(), -74d, 74d));
        return Te(Uk(a.getLng()), Uk(b.getLng()), Uk(a.getLat()), Uk(b.getLat()));
    }

    private static Double OD(Double a, Double b, Double c) {
        for (; a > c;)
            a -= c - b;
        for (; a < b;)
            a += c - b;
        return a;
    }

    private static Double SD(Double a, Double b, Double c) {
        return a;
    }

    private static Double Te(Double a, Double b, Double c, Double d) {
        return 6370996.81 * Math
                .acos(Math.sin(c) * Math.sin(d) + Math.cos(c) * Math.cos(d) * Math.cos(b - a));
    }

    private static Double Uk(Double a) {
        return Math.PI * a / 180;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Position {
        /**
         * 经度
         */
        private Double lng;
        /**
         * 纬度
         */
        private Double lat;

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        };
    }

public static void main(String[] args) {
    //121.416146,31.221902
    //121.415103,31.220708
        System.out.println(getDistance(new Position(121.416146, 31.221902),new Position(121.415103, 31.220708)));
        
    }
 
}
