package com.xian80;

import org.locationtech.jts.geom.Envelope;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

/**
 * Created by aochong Cotter on 2019/2/20.
 */
public abstract class VectorTile {

    public static boolean isOverlap(Geometry geom, List<Integer> list,double distance){
        Envelope env = geom.getEnvelopeInternal();
        double step = env.getWidth()/list.get(0);
        if(step % distance == 0){
            return true;
        }
        return false;
    }

    public static boolean isEqual(List<Integer> list){
        if(list.get(0).compareTo(list.get(1))==0){
            return true;
        }
        return false;
    }

    public static boolean isZero(List<Integer> list){
        if(list.get(0) == 0 && list.get(1) == 0){
            return true;
        }
        return false;
    }


}
