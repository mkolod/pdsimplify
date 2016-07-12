package edu.psu.sagnik.research.pdsimplify.path.impl

import java.awt.geom.Rectangle2D.Float
import java.awt.geom.{Rectangle2D, Area}

import edu.psu.sagnik.research.pdsimplify.model.Rectangle
import edu.psu.sagnik.research.pdsimplify.path.model.PDPath

/**
 * Created by sagnik on 6/28/16.
 */
//TODO: Current status, we are not checking for clip paths.
// TODO: the clipping path really doesn't work this way. What we should do
//is get the intersection of this path and the current clipping path and
//paint that. Instead, we are rejecting a strokable or paintable path
//if it doesn't fall within the current clipping path. Also, to
//see if a path falls within a clipping area, we are just checking with the
//bounding box of the whole path. This is really an approximation.


object CheckClipping {

  def getPathBB(path:PDPath):Rectangle2D.Float= {
    new Rectangle2D.Float(
      path.subPaths.flatMap(_.segments).map(_.bb).map(_.x1).min,
      path.subPaths.flatMap(_.segments).map(_.bb).map(_.y1).min,
      path.subPaths.flatMap(_.segments).map(_.bb).map(_.x2).max -
        path.subPaths.flatMap(_.segments).map(_.bb).map(_.x1).min,
      path.subPaths.flatMap(_.segments).map(_.bb).map(_.y2).max -
        path.subPaths.flatMap(_.segments).map(_.bb).map(_.y1).min
    )
  }

  def inSideClip(currentPath:Option[PDPath],ccp:Area):Boolean=currentPath match{ //ccp=current clipping path
    case Some(cp) => true//ccp.contains(getPathBB(cp))
    case _ => true //this path is none, so doesn't really matter
  }

}
