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
public class VinaData {
    private ArrayList<Conformation> conformations;
    
    public VinaData () {
        conformations = new ArrayList<Conformation>();
    }
    
    public ArrayList<Conformation> getConformations () {
        return conformations;
    }
    
    public void add (Conformation c) {
        conformations.add(c);
    }
    
    @Override
    public String toString () {
       String s = "Vina data: ";
       Iterator<Conformation> iter = conformations.iterator();
       while (iter.hasNext()) {
           s += iter.next() + " ";
       }
       
       return s;
    }
}
