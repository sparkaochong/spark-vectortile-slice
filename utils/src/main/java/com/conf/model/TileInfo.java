package com.conf.model;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public class TileInfo {
    private int dpi;
    private double originX;
    private double originY;
    private int tileSize;
    private double baseResolution;

    public TileInfo(){}

    public TileInfo(int dpi, double originX, double originY, int tileSize, double baseResolution) {
        this.dpi = dpi;
        this.originX = originX;
        this.originY = originY;
        this.tileSize = tileSize;
        this.baseResolution = baseResolution;
    }

    public int getDpi() {
        return dpi;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public double getOriginX() {
        return originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public double getOriginY() {
        return originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public double getBaseResolution() {
        return baseResolution;
    }

    public void setBaseResolution(double baseResolution) {
        this.baseResolution = baseResolution;
    }

    @Override
    public String toString() {
        return "TileInfo{" +
                "dpi=" + dpi +
                ", originX=" + originX +
                ", originY=" + originY +
                ", tileSize=" + tileSize +
                ", baseResolution=" + baseResolution +
                '}';
    }
}
