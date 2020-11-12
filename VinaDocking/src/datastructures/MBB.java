/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.text.NumberFormat;
import java.util.Locale;
import javax.vecmath.Point3d;

/**
 * Minimum Bounding Box
 * @author Paolo
 */
public class MBB {
    private double[][] points;
    private double[] center;
    private double width, height, depth;
    
    public MBB (double x, double y, double z, double x_len, double y_len, double z_len) {
        width  = x_len;
        height = y_len;
        depth  = z_len;
        
        points = new double[8][3];
        
        points[0][0] = x;
        points[0][1] = y;
        points[0][2] = z;
        
        points[1][0] = points[0][0] + width;
        points[1][1] = points[0][1];
        points[1][2] = points[0][2];
        
        points[2][0] = points[1][0];
        points[2][1] = points[1][1] + height;
        points[2][2] = points[1][2];
        
        points[3][0] = points[0][0];
        points[3][1] = points[0][1] + height;
        points[3][2] = points[0][2];
        
        points[4][0] = points[0][0];
        points[4][1] = points[0][1];
        points[4][2] = points[0][2] + depth;
        
        points[5][0] = points[1][0];
        points[5][1] = points[1][1];
        points[5][2] = points[1][2] + depth;
        
        points[6][0] = points[2][0];
        points[6][1] = points[2][1];
        points[6][2] = points[2][2] + depth;
        
        points[7][0] = points[3][0];
        points[7][1] = points[3][1];
        points[7][2] = points[3][2] + depth;
        
        center = new double[3];
        
        center[0] = points[0][0] + width  / 2;
        center[1] = points[0][1] + height / 2;
        center[2] = points[0][2] + depth  / 2;
    }
    
    public Point3d getCenter () {
        return new Point3d (center);
    }
    
    public double getWidth () {
        return width;
    }
    
    public double getHeight () {
        return height;
    }
    
    public double getDepth () {
        return depth;
    }
    
    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);
        nf.setMinimumFractionDigits(3);
        nf.setMaximumFractionDigits(3);
        
        String s = "\nCenter       [ " + nf.format(center[0]) + ", " + nf.format(center[1]) + ", " + nf.format(center[2]) + " ]\n" +
             "Dimensions   [ " + nf.format(width) + " x " + nf.format(height) + " x " + nf.format(depth) + " ]\n\n";
        
        String[] shape = new String[8];
        
        shape[0] = "   7----6 ";
        shape[1] = "  /|   /| ";
        shape[2] = " 3-+--2 | ";
        shape[3] = " | |  | | ";
        shape[4] = " | |  | | ";
        shape[5] = " | 4--+-5 ";
        shape[6] = " |/   |/  ";
        shape[7] = " 0----1   ";
        
        for (int i = 0; i < points.length; i++) {
                s += shape[i] + " " + i + " [ ";
            for (int j = 0; j < points[i].length; j++) {
                 s += nf.format(points[i][j]) + ", "; 
            }
            s = s.substring(0, s.length() - 2) + " ]\n";
        }
        
        return s;
    }
}
