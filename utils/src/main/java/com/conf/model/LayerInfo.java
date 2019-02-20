package com.conf.model;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class LayerInfo {
    private int id;
    private int srid;
    private String layerName;

    public LayerInfo(){}

    public LayerInfo(int id, int srid, String layerName) {
        this.id = id;
        this.srid = srid;
        this.layerName = layerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSrid() {
        return srid;
    }

    public void setSrid(int srid) {
        this.srid = srid;
    }

    public String getLayerName() {
        return layerName;
    }

    public void setLayerName(String layerName) {
        this.layerName = layerName;
    }

    @Override
    public String toString() {
        return "LayerInfo{" +
                "id=" + id +
                ", srid=" + srid +
                ", layerName='" + layerName + '\'' +
                '}';
    }
}
