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
public class AutoDockData {
    private ArrayList<RMS> data;
    
    public AutoDockData () {
        data = new ArrayList<RMS>();
    }
    
    public void addRMS (RMS rms) {
        data.add(rms);
    }
    
    public RMS getRMS (int n) {
        return data.get(n);
    }
    
    public RMS getRMS (float v) {
        RMS r;
        Iterator<RMS> iter = data.iterator();
        while (iter.hasNext()) {
            r = (RMS) iter.next();
            if (r.getValue() == v) {
                return r;
            }
        }
        return null;
    }
    
    public ArrayList<RMS> getRMSs () {
        return data;
    }
    
    @Override
    public String toString() {
        String s = "AutoDock data:\n";
        Iterator<RMS> iter = data.iterator();
        while (iter.hasNext()) {
            s += "|  " + iter.next();
        }
        return s;
    }
}
