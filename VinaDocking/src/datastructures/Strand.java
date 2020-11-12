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
public class Strand {

    String label;
    ArrayList<Nucleotide> nucleotides;

    public Strand(String l) {
        label = l;
        nucleotides = new ArrayList<Nucleotide>();
    }

    public String getLabel() {
        return label;
    }

    
    public ArrayList getNucleotides() {
        return nucleotides;
    }

    public Nucleotide getNucleotide(int i) {
        Nucleotide m;
        Iterator monomerIterator = nucleotides.iterator();
        while (monomerIterator.hasNext()) {
            m = (Nucleotide) monomerIterator.next();
            if (m.getSerial() == i) {
                return m;
            }
        }
        return null;
    }

    public int getSize() {
        return nucleotides.size();
    }

    public void addNucleotide(Nucleotide m) {
        nucleotides.add(m);
    }

    @Override
    public String toString() {
        String s = "Strand " + label + ":\n";
        Iterator iter = nucleotides.iterator();
        while (iter.hasNext()) {
            s += "|   |   " + iter.next();
        }
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;
        Strand s = (Strand) o;
        if (s.label.compareTo(label) == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 29 * hash + (this.nucleotides != null ? this.nucleotides.hashCode() : 0);
        return hash;
    }
}
