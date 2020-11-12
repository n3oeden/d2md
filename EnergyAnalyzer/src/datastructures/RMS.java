/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Paolo
 */
public class RMS {
    private float value;
    private ArrayList<Cluster> clusters;
    
    public RMS (float v) {
        value = v;
        clusters = new ArrayList<Cluster>();
    }
    
    public float getValue () {
        return value;
    }
    
    public void add (Cluster c) {
        clusters.add(c);
    }
    
    public Cluster getCluster (int n) {
        Cluster c;
        Iterator<Cluster> iter = clusters.iterator();
        while (iter.hasNext()) {
            c = (Cluster) iter.next();
            if (c.getNumber() == n)
                return c;
        }
        return null;
    }
    
    public ArrayList<Cluster> getClusters () {
        return clusters;
    }
    
    @Override
    public String toString() {
        String s = "RMS = " + value + "\n";
        Iterator<Cluster> iter = clusters.iterator();
        while (iter.hasNext()) {
            s += "|  |  " + iter.next() + "\n";
        }
        return s;
    }
}
