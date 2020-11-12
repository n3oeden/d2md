/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import javax.vecmath.Point3d;

/**
 *
 * @author Paolo
 */
public class IntercalationSpace {
    private int number;
    private String description;
    private Point3d center;
    
    public IntercalationSpace (int num, String desc, Point3d point) {
        number = num;
        description = desc;
        center = point;
    }
    
    public int getNum() {
        return number;
    }
    
    public String getDesc() {
        return description;
    }
    
    /**
     * @return the center
     */
    public Point3d getCenter() {
        return center;
    }

    /**
     * @param center the center to set
     */
    public void setCenter(Point3d center) {
        this.center = center;
    }
}
