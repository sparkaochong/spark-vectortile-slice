package com.xian80;

import com.conf.model.TileInfo;

/**
 * Created by aochong Cotter on 2019/2/20.
 */

public abstract class VectorTile {

    public static TileInfo tile;

    private static double tileXToX(long tileX, byte zoom) {
        return pixelXToX(tileX * tile.getTileSize(), zoom);
    }
    private static double tileYToY(long tileY, byte zoom) {
        return pixelYToY(tileY * tile.getTileSize(), zoom);
    }

    private static double pixelXToX(double pixelX, byte zoom) {
        double resolution = tile.getBaseResolution() / (1 << zoom);
        return pixelX * resolution;
    }

    private static double pixelYToY(double pixelY, byte zoom) {
        double resolution = tile.getBaseResolution() / (1 << zoom);
        return pixelY * resolution;
    }
}
