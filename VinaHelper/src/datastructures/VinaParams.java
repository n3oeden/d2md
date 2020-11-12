/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.io.File;
import javax.vecmath.Point3d;

/**
 *
 * @author Paolo
 */
public class VinaParams {
    private File receptorFile;
    private Point3d center;
    private double width, height, depth;
    private int cpu, exhaustiveness, modes;
    
    public VinaParams () {
        receptorFile = null;
        center = null;
        width = height = depth = 0.0;
        cpu = exhaustiveness = modes = 0;
    }
    
    /**
     * @return the receptorFile
     */
    public File getReceptorFile() {
        return receptorFile;
    }

    /**
     * @param receptorFile the receptorFile to set
     */
    public void setReceptorFile(File receptorFile) {
        this.receptorFile = receptorFile;
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
    
    /**
     * @param x the center x to set
     * @param y the center y to set
     * @param z the center z to set
     */
    public void setCenter(double x, double y, double z) {
        this.center = new Point3d (x, y ,z);
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * @return the depth
     */
    public double getDepth() {
        return depth;
    }

    /**
     * @param depth the depth to set
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }

    /**
     * @return the cpu
     */
    public int getCpu() {
        return cpu;
    }

    /**
     * @param cpu the cpu to set
     */
    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the exhaustiveness
     */
    public int getExhaustiveness() {
        return exhaustiveness;
    }

    /**
     * @param exhaustiveness the exhaustiveness to set
     */
    public void setExhaustiveness(int exhaustiveness) {
        this.exhaustiveness = exhaustiveness;
    }

    /**
     * @return the modes
     */
    public int getModes() {
        return modes;
    }

    /**
     * @param modes the modes to set
     */
    public void setModes(int modes) {
        this.modes = modes;
    }
    
}
