/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import javax.vecmath.Point3d;

/**
 *
 * @author Paolo Gatti
 */
public class DNA {

    private String name;
    int length;
    private MBB mbb;
    private HashMap<String, Strand> strands;
    private HashMap<Integer, IntercalationSpace> spaces;
    
    public DNA(String name) {
        this.name = name;
        length = 0;
        mbb = null;
        strands = new HashMap<String, Strand>();
        spaces = new HashMap<Integer, IntercalationSpace>();
    }

    public String getName() {
        return name;
    }
    
    public void setLength (int l) {
        length = l;
    }
    
    public int getLength () {
        return length;
    }
    
    public Strand getStrand(String label) {
        return strands.get(label);
    }
    
    public void setMBB (double x, double y, double z, double x_len, double y_len, double z_len) {
        this.mbb = new MBB(x, y, z, x_len, y_len, z_len);
    }
    
    public MBB getMBB () {
        return mbb;
    }
    
    public void printMBB () {
        System.out.println("DNA \"" + name + "\" Minimum Bounding Box\n" + mbb);
    }
    
    public Collection<Strand> getStrands() {
        return strands.values();
    }
    
    public void addStrand(Strand s) {
        strands.put(s.getLabel(), s);
    }
    
    public IntercalationSpace getSpace(int n) {
        return spaces.get(n);
    }
    
    public Collection<IntercalationSpace> getSpaces () {
        return spaces.values();
    }
    
    public boolean calculateTopology() {
        Iterator nucleotideIterator, strandIterator = getStrands().iterator();
        Strand strand;
        Nucleotide nucleotide;
        boolean tryReverseOrder = false;
        int count = 0, count2 = 0;
        int[][] bases = new int[length][getStrands().size()];
        String[] seqs = new String[getStrands().size()];
        
        while (strandIterator.hasNext()) {
            strand = (Strand) strandIterator.next();
            seqs[count] = strand.getLabel();
            
            nucleotideIterator = strand.getNucleotides().iterator();
            
            while(nucleotideIterator.hasNext()) {
                nucleotide = (Nucleotide) nucleotideIterator.next();
                bases[count2++][count] = nucleotide.getSerial();
            }
            
            count2 = 0;
            count++;
        }
        
        for (int i = 0; i < bases.length; i++) {
            if ((getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DA") &&
                    !getStrand(seqs[1]).getNucleotide(bases[i][1]).getName().equals("DT")) ||
                (getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DC") &&
                    !getStrand(seqs[1]).getNucleotide(bases[i][1]).getName().equals("DG")) ||
                (getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DG") &&
                    !getStrand(seqs[1]).getNucleotide(bases[i][1]).getName().equals("DC")) ||
                (getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DT") &&
                    !getStrand(seqs[1]).getNucleotide(bases[i][1]).getName().equals("DA"))) {
                
                tryReverseOrder = true;
                break;
            }
        }
        
        if (tryReverseOrder) {
            for (int i = 0; i < bases.length; i++) {
                if ((getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DA") &&
                        !getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).getName().equals("DT")) ||
                    (getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DC") &&
                        !getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).getName().equals("DG")) ||
                    (getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DG") &&
                        !getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).getName().equals("DC")) ||
                    (getStrand(seqs[0]).getNucleotide(bases[i][0]).getName().equals("DT") &&
                        !getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).getName().equals("DA"))) {

                    //System.out.println("[ERROR] DNA nucleotides do not match themselves!");
                    return false;
                }
            }
        }
        
        if (tryReverseOrder) {
            for (int i = 0; i < bases.length; i++) {
                getStrand(seqs[0]).getNucleotide(bases[i][0]).setAdj(getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]));
                getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).setAdj(getStrand(seqs[0]).getNucleotide(bases[i][0]));
                
                if (i - 1 >= 0) {
                  getStrand(seqs[0]).getNucleotide(bases[i][0]).setPrev(getStrand(seqs[0]).getNucleotide(bases[i - 1][0]));
                  getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).setNext(getStrand(seqs[1]).getNucleotide(bases[length - i][1]));
                }
                
                if (i + 1 < length) {
                  getStrand(seqs[0]).getNucleotide(bases[i][0]).setNext(getStrand(seqs[0]).getNucleotide(bases[i + 1][0]));
                  getStrand(seqs[1]).getNucleotide(bases[length - i - 1][1]).setPrev(getStrand(seqs[1]).getNucleotide(bases[length - i - 2][1]));
                }
            }
        }
        else {
            for (int i = 0; i < bases.length; i++) {
                getStrand(seqs[0]).getNucleotide(bases[i][0]).setAdj(getStrand(seqs[1]).getNucleotide(bases[i][1]));
                getStrand(seqs[1]).getNucleotide(bases[i][1]).setAdj(getStrand(seqs[0]).getNucleotide(bases[i][0]));
                
                if (i - 1 >= 0) {
                  getStrand(seqs[0]).getNucleotide(bases[i][0]).setPrev(getStrand(seqs[0]).getNucleotide(bases[i - 1][0]));
                  getStrand(seqs[1]).getNucleotide(bases[i][1]).setNext(getStrand(seqs[1]).getNucleotide(bases[i - 1][1]));
                }
                
                if (i + 1 < length) {
                  getStrand(seqs[0]).getNucleotide(bases[i][0]).setNext(getStrand(seqs[0]).getNucleotide(bases[i + 1][0]));
                  getStrand(seqs[1]).getNucleotide(bases[i][1]).setPrev(getStrand(seqs[1]).getNucleotide(bases[i + 1][1]));
                }
            }
        }
        return true;
    }
    
    public void printTopology () {
        Iterator nucleotideIterator, strandIterator;
        Nucleotide nucleotide;
        Strand strand;
        
        System.out.println("DNA \"" + name + "\" Topology");
        
        strandIterator = getStrands().iterator();
        while (strandIterator.hasNext()) {
            strand = (Strand) strandIterator.next();
            nucleotideIterator = strand.getNucleotides().iterator();
            
            while (nucleotideIterator.hasNext()) {
                nucleotide = (Nucleotide) nucleotideIterator.next();
                /*
                Nucleotide prev, next, adj;
                
                prev = nucleotide.goPrev();
                next = nucleotide.goNext();
                
                System.out.print(nucleotide.getName() + " < " + (prev != null ? prev.getName() : "XX") +
                        " > " + (next != null ? next.getName() : "XX"));
                System.out.print(" = ");
                
                adj  = nucleotide.goAdj();
                prev = adj.goPrev();
                next = adj.goNext();
                
                System.out.print(adj.getName() + " < " + (prev != null ? prev.getName() : "XX") +
                        " > " + (next != null ? next.getName() : "XX"));
                
                System.out.println();
                */
                
                if (nucleotide.goPrev() != null) {
                    System.out.print("||");
                }
                else System.out.print("  ");
                System.out.print("   ");
                if (nucleotide.goAdj().goNext() != null) {
                    System.out.print("||");
                }
                System.out.println();
                
                System.out.println(nucleotide.getName() + " - " + nucleotide.goAdj().getName());
                
                if (nucleotide.goNext() != null) {
                    System.out.print("||");
                }
                else System.out.print("  ");
                System.out.print("   ");
                if (nucleotide.goAdj().goPrev() != null) {
                    System.out.print("||");
                }
                System.out.println();
            }
            
            break;
        }
    }
    
    
    public int findIntercalationSpaces() {
        Atom vertex[] = new Atom[4];
        double distance1, distance2;
        Iterator nucleotideIterator, strandIterator;
        Nucleotide nucleotide, adj;
        Strand strand;
        int counter = 1;
        String description;
        
        Point3d p13, p43, p21, pa, pb, center;
        Double d1343, d4321, d1321, d4343, d2121, denom, num, mua, mub;
        IntercalationSpace is;
        
        strandIterator = getStrands().iterator();
        while (strandIterator.hasNext()) {
            strand = (Strand) strandIterator.next();
            nucleotideIterator = strand.getNucleotides().iterator();
            
            while (nucleotideIterator.hasNext()) {
                nucleotide = (Nucleotide) nucleotideIterator.next();
                
                if (nucleotide.goNext() != null) {
                    vertex[0] = nucleotide.getC1();
                    vertex[1] = nucleotide.goAdj().getC1();
                    vertex[2] = nucleotide.goAdj().goPrev().getC1();
                    vertex[3] = nucleotide.goNext().getC1();
                    
                    distance1 = Math.sqrt(Math.pow(vertex[0].getX() - vertex[3].getX(), 2) + 
                                          Math.pow(vertex[0].getY() - vertex[3].getY(), 2) + 
                                          Math.pow(vertex[0].getZ() - vertex[3].getZ(), 2));
                    
                    distance2 = Math.sqrt(Math.pow(vertex[1].getX() - vertex[2].getX(), 2) + 
                                          Math.pow(vertex[1].getY() - vertex[2].getY(), 2) + 
                                          Math.pow(vertex[1].getZ() - vertex[2].getZ(), 2));
                    
                    if (distance1 >= 6.0 && distance2 >= 6.0) {
                        description = strand.getLabel() + " " + nucleotide.getSerial() + " " + nucleotide.getName();
                        description += " | ";
                        description += strand.getLabel() + " " + nucleotide.goNext().getSerial() + " " + nucleotide.goNext().getName();
                        
                        p13    = new Point3d();
                        p43    = new Point3d();
                        p21    = new Point3d();
                        pa     = new Point3d();
                        pb     = new Point3d();
                        center = new Point3d();
                        
                        p13.x = vertex[0].getX() - vertex[3].getX();
                        p13.y = vertex[0].getY() - vertex[3].getY();
                        p13.z = vertex[0].getZ() - vertex[3].getZ();
                        p43.x = vertex[1].getX() - vertex[3].getX();
                        p43.y = vertex[1].getY() - vertex[3].getY();
                        p43.z = vertex[1].getZ() - vertex[3].getZ();
                        p21.x = vertex[2].getX() - vertex[0].getX();
                        p21.y = vertex[2].getY() - vertex[0].getY();
                        p21.z = vertex[2].getZ() - vertex[0].getZ();
                        
                        d1343 = p13.x * p43.x + p13.y * p43.y + p13.z * p43.z;
                        d4321 = p43.x * p21.x + p43.y * p21.y + p43.z * p21.z;
                        d1321 = p13.x * p21.x + p13.y * p21.y + p13.z * p21.z;
                        d4343 = p43.x * p43.x + p43.y * p43.y + p43.z * p43.z;
                        d2121 = p21.x * p21.x + p21.y * p21.y + p21.z * p21.z;
                        
                        denom = d2121 * d4343 - d4321 * d4321;
                        num = d1343 * d4321 - d1321 * d4343;
                        
                        mua = num / denom;
                        mub = (d1343 + d4321 * mua) / d4343;
                        
                        pa.x = vertex[0].getX() + mua * p21.x;
                        pa.y = vertex[0].getY() + mua * p21.y;
                        pa.z = vertex[0].getZ() + mua * p21.z;
                        pb.x = vertex[3].getX() + mub * p43.x;
                        pb.y = vertex[3].getY() + mub * p43.y;
                        pb.z = vertex[3].getZ() + mub * p43.z;
                        
                        center.x = (pa.x + pb.x) / 2;
                        center.y = (pa.y + pb.y) / 2;
                        center.z = (pa.z + pb.z) / 2;
                        
                        //System.out.println(description + "\n" + secondPair + "\n" + center);
                        
                        is = new IntercalationSpace (counter, description, center);
                        spaces.put(counter++, is);
                    }
                }
            }
            break; // check only one strand
        }
        
        return (counter - 1);
    }
    
    @Override
    public String toString() {
        Iterator i = getStrands().iterator();
        String s = "DNA file " + name + " (" + length + " nucleotides)\n";
        while (i.hasNext()) {
            s += "|   " + i.next();
        }
        return (s);
    }
}
