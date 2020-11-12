/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class which represents a set of energies clusterized by RMS
 * @author Paolo Gatti
 */
public class Cluster {
    private int number;
    private boolean enabled;
    private ArrayList<Float> energies;
    
    public Cluster (int n) {
        number = n;
        enabled = true;
        energies = new ArrayList<Float>();
    }
    
    public int getNumber () {
        return number;
    }
    
    public int getSize () {
        return energies.size();
    }
    
    public void setEnabled (boolean e) {
        enabled = e;
    }
    
    public boolean isEnabled () {
        return enabled;
    }
    
    public void add (float f) {
        energies.add(f);
    }
    
    public ArrayList<Float> getEnergies () {
        return energies;
    }
    
    @Override
    public String toString() {
        String s = number + (enabled ? " " : ".") + "(" + energies.size() + "): ";
        Iterator<Float> iter = energies.iterator();
        while (iter.hasNext()) {
            s += iter.next() + " ";
        }
        return s;
    }
}
