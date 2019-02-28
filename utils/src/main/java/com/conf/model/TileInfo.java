package com.conf.model;

/**
 * @program: spark-vectortile-slice
 * @description: 瓦片信息实体类
 * @author: Mr.Ao
 * @create: 2019-02-22 15:24
 **/
public class TileInfo {
    private int dpi;
    private double originX;
    private double originY;
    private int tileSize;
    private double baseResolution;
    private String format;

    public TileInfo() {
    }

    public TileInfo(int dpi, double originX, double originY, int tileSize, double baseResolution, String format) {
        this.dpi = dpi;
        this.originX = originX;
        this.originY = originY;
        this.tileSize = tileSize;
        this.baseResolution = baseResolution;
        this.format = format;
    }

    public int getDpi() {
        return this.dpi;
    }

    public double getOriginX() {
        return this.originX;
    }

    public double getOriginY() {
        return this.originY;
    }

    public int getTileSize() {
        return this.tileSize;
    }

    public double getBaseResolution() {
        return this.baseResolution;
    }

    public String getFormat() {
        return this.format;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void setBaseResolution(double baseResolution) {
        this.baseResolution = baseResolution;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TileInfo)) {
            return false;
        }
        final TileInfo other = (TileInfo) o;
        if (!other.canEqual((Object) this)) {
            return false;
        }
        if (this.dpi != other.dpi) {
            return false;
        }
        if (Double.compare(this.originX, other.originX) != 0) {
            return false;
        }
        if (Double.compare(this.originY, other.originY) != 0) {
            return false;
        }
        if (this.tileSize != other.tileSize) {
            return false;
        }
        if (Double.compare(this.baseResolution, other.baseResolution) != 0) {
            return false;
        }
        final Object this$format = this.format;
        final Object other$format = other.format;
        if (this$format == null ? other$format != null : !this$format.equals(other$format)) {
            return false;
        }
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TileInfo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.dpi;
        final long $originX = Double.doubleToLongBits(this.originX);
        result = result * PRIME + (int) ($originX >>> 32 ^ $originX);
        final long $originY = Double.doubleToLongBits(this.originY);
        result = result * PRIME + (int) ($originY >>> 32 ^ $originY);
        result = result * PRIME + this.tileSize;
        final long $baseResolution = Double.doubleToLongBits(this.baseResolution);
        result = result * PRIME + (int) ($baseResolution >>> 32 ^ $baseResolution);
        final Object $format = this.format;
        result = result * PRIME + ($format == null ? 43 : $format.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "TileInfo(dpi=" + this.dpi + ", originX=" + this.originX + ", originY=" + this.originY + ", tileSize=" + this.tileSize + ", baseResolution=" + this.baseResolution + ", format=" + this.format + ")";
    }
}
