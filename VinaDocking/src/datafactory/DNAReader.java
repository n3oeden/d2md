/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datafactory;

import datastructures.DNA;
import datastructures.Nucleotide;
import datastructures.Strand;
import java.util.Iterator;
import java.util.List;
import org.biojava.bio.structure.Atom;
import org.biojava.bio.structure.Chain;
import org.biojava.bio.structure.Group;
import org.biojava.bio.structure.Structure;
import org.biojava.bio.structure.io.FileParsingParameters;
import org.biojava.bio.structure.io.PDBFileReader;

/**
 *
 * @author Paolo Gatti
 */
public class DNAReader {

    public static DNA readPDB(String fileName) {
        int dnaLength = 0;
        double x_min, x_max, y_min, y_max, z_min, z_max;
        
        FileParsingParameters params = new FileParsingParameters();
        params.setAlignSeqRes(true);
        params.setParseSecStruc(true);
        params.setParseCAOnly(false);
        PDBFileReader pdbReader = new PDBFileReader();
        pdbReader.setFileParsingParameters(params);
        pdbReader.setAutoFetch(false);

        DNA dna;
        
        x_min = y_min = z_min = Double.MAX_VALUE;
        x_max = y_max = z_max = Double.MIN_VALUE;

        try {
            Structure pdbStructure = pdbReader.getStructure(fileName);

            dna = new DNA(fileName.substring(fileName.lastIndexOf("/") + 1,
                    fileName.length() - 4));

            List<Chain> chainList = pdbStructure.getChains();
            Iterator chainsIterator = chainList.iterator();
            Chain chain;
            Strand strand;
            
            List<Group> groupsList;
            Iterator groupsIterator;
            Group group;
            Nucleotide nucleotide;
            
            List<Atom> atomsList;
            Iterator atomsIterator;
            org.biojava.bio.structure.Atom pdbAtom;
            datastructures.Atom atom;
            
            while (chainsIterator.hasNext()) {
                chain = (Chain) chainsIterator.next();
                groupsList = chain.getAtomGroups();
                groupsIterator = groupsList.iterator();

                strand = new Strand(chain.getChainID());
                
                while (groupsIterator.hasNext()) {
                    group = (Group) groupsIterator.next();
                    
                    if (group.getType().equals("nucleotide")) {
                        
                        dnaLength++;
                        
                        atomsList = group.getAtoms();
                        atomsIterator = atomsList.iterator();

                        nucleotide = new Nucleotide(group.getPDBName().trim(),
                            group.getResidueNumber().getSeqNum());

                        while (atomsIterator.hasNext()) {
                            pdbAtom = (Atom) atomsIterator.next();
                            
                            atom = new datastructures.Atom(
                                    pdbAtom.getPDBserial(),
                                    pdbAtom.getName().trim(),
                                    pdbAtom.getX(),
                                    pdbAtom.getY(),
                                    pdbAtom.getZ(),
                                    pdbAtom.getElement().name().equals("") ? 
                                        pdbAtom.getName().trim() : 
                                            pdbAtom.getElement().name());
                            
                            if (atom.getName().equals("C1'")) {
                                nucleotide.setC1(atom);
                            }
                            
                            if (atom.getX() <= x_min)
                                x_min = atom.getX();
                            else if (atom.getX() >= x_max)
                                x_max = atom.getX();
                            if (atom.getY() <= y_min)
                                y_min = atom.getY();
                            else if (atom.getY() >= y_max)
                                y_max = atom.getY();
                            if (atom.getZ() <= z_min)
                                z_min = atom.getZ();
                            else if (atom.getZ() >= z_max)
                                z_max = atom.getZ();
                            
                            nucleotide.addAtom(atom);
                        }
                        
                        strand.addNucleotide(nucleotide);
                    }
                    
                }
                dna.addStrand(strand);
                
                if (dna.getLength() != 0 && dna.getLength() != dnaLength) {
                    
                    //JOptionPane.showMessageDialog(null, "The strands in \"" + fileName + "\" have different lenghts!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    //System.out.println("[ERROR]: Strands have different lengths!");
                    return null;
                }
                dna.setLength(dnaLength);
                dnaLength = 0;
                
            }
            
            dna.setMBB(x_min, y_min, z_min, Math.abs(x_max - x_min),
                    Math.abs(y_max - y_min), Math.abs(z_max - z_min));
            
            //dna.calculateTopology();
            
            //dna.findIntercalationSpaces();
        
        } catch (Exception e) {
            //System.out.println("Cannot read file \"" + fileName + "\"");
            //JOptionPane.showMessageDialog(null, "Cannot open \"" + fileName + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return (dna);
    }
}
