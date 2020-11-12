/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author Paolo
 */
public class Conformation {
    private boolean enabled;
    private int mode;
    private float energy;
    
    public Conformation (int m, float e) {
        mode = m;
        energy = e;
        enabled = true;
    }
    
    public int getMode () {
        return mode;
    }
    
    public float getEnergy () {
        return energy;
    }
    
    public boolean isEnabled () {
        return enabled;
    }
    
    public void setEnabled (boolean e) {
        enabled = e;
    }
    
    @Override
    public String toString () {
        return energy + (enabled ? "" : "*");
    }
}
