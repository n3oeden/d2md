/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.ArrayList;
import java.util.Iterator;
import javax.media.j3d.BoundingSphere;
import javax.vecmath.Point3d;

/**
 *
 * @author Paolo
 */
public class Ligand {
    private String name;
    private ArrayList<Atom> atoms;
    private BoundingSphere bs;

    public Ligand (String name) {
        this.name = name;
        atoms = new ArrayList<Atom>();
        bs = null;
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
    
    public Point3d[] getPoints () {
        int count = 0;
        Point3d[] points = new Point3d[atoms.size()];
        Iterator<Atom> iter = atoms.iterator();
        Atom atom;
        
        while (iter.hasNext()) {
            atom = iter.next();
            points[count++] = new Point3d(atom.getX(), atom.getY(), atom.getZ());
        }
        
        return points;
    }
    
    public BoundingSphere getBoundingSphere () {
        return bs;
    }
    
    public void setBoundingSphere (Point3d[] points) {
        bs = new BoundingSphere(points[0], 1.0);
        bs.combine(points);
    }
    
    @Override
    public String toString() {
        String s = "Ligand " + name + ":\n";
        
        Iterator iter = atoms.iterator();
        while(iter.hasNext()) {
            s += "|  " + iter.next();
        }
        
        return s;
    }
}
