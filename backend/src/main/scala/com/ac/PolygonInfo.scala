package com.ac

import org.locationtech.jts.geom.Geometry

/**
  * @program: spark-vectortile-slice
  * @description: ${description}
  * @author: Mr.Ao
  * @create: 2019-03-07 16:17
  **/
case class PolygonInfo(id: Int, layerName: String, polygon: Geometry)
