/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Paolo Gatti
 */
public class Nucleotide {

    private String name;
    private int serial;
    private Atom C1;
    private Nucleotide adjacent, prev, next;
    private ArrayList<Atom> atoms;

    public Nucleotide(String n, int s) {
        name = n;
        serial = s;
        C1 = null;
        adjacent = prev = next = null;
        atoms = new ArrayList<Atom>();
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public int getSerial() {
        return serial;
    }
    
    public Atom getC1() {
        return C1;
    }
    
    public void setC1(Atom c1) {
        C1 = c1;
    }
    
    public ArrayList getAtoms() {
        return atoms;
    }

    public Atom getAtom(int i) {
        return atoms.get(i);
    }

    public void addAtom(Atom atomo) {
        atoms.add(atomo);
    }
    
    public Nucleotide goPrev () {
        return prev;
    }
    
    public void setPrev (Nucleotide prev) {
        this.prev = prev;
    }
    
    public Nucleotide goNext () {
        return next;
    }
    
    public void setNext (Nucleotide next) {
        this.next = next;
    }
    
    public void setAdj (Nucleotide adj) {
        adjacent = adj;
    }
    
    public Nucleotide goAdj () {
        return adjacent;
    }
    
    @Override
    public String toString() {
        String s = serial + ") " + name;
        
        if (C1 != null)
            s += " [C1' = " + getC1().getSerial() + "]";
        
        s += "\n";
        
        Iterator iter = atoms.iterator();
        while(iter.hasNext()) {
            s += "|   |   |   " + iter.next();
        }
        
        return s;
    }
}
