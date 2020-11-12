/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datafactory;

import datastructures.Atom;
import datastructures.Ligand;
import java.io.*;

/**
 *
 * @author Paolo Gatti
 */
public class LigandReader {
    public static Ligand readPDB(File file) {
        BufferedReader in;
        int atomSerial, emptyAtomType = 0;
        float atomX, atomY, atomZ;
        String recordType, line, atomName, atomType, fileName;
        Atom atom;
        
        fileName = file.getName();
        
        Ligand lig = new Ligand (fileName.substring(0, fileName.length() - 4));
        
        try {
            in = new BufferedReader (new FileReader (file));
            try {
                while ((line = in.readLine()) != null) {
                    try {
                        recordType = line.substring(0, 6).trim();
                    }
                    catch (Exception e) {
                        continue;
                    }
                    if (recordType.equals("ATOM") || recordType.equals("HETATM")) {
                        try {
                            atomSerial = Integer.parseInt(line.substring(6, 11).trim());
                        } catch (Exception ex) {
                            //System.out.println("[ERROR] Cannot read atom serial number");
                            return null;
                        }
                        
                        try {
                            atomType = line.substring(12, 16).trim();
                        } catch (Exception ex) {
                            emptyAtomType++;
                            atomType = "";
                        }
                        
                        try {
                            atomX = Float.parseFloat(line.substring(30, 38).trim());
                        } catch (Exception ex) {
                            //System.out.println("[ERROR] Cannot read tridimensional coordinates");
                            return null;
                        }
                        
                        try {
                            atomY = Float.parseFloat(line.substring(38, 46).trim());
                        } catch (Exception ex) {
                            //System.out.println("[ERROR] Cannot read tridimensional coordinates");
                            return null;
                        }
                        
                        try {
                            atomZ = Float.parseFloat(line.substring(46, 54).trim());
                        } catch (Exception ex) {
                            //System.out.println("[ERROR] Cannot read tridimensional coordinates");
                            return null;
                        }
                        
                        try {
                            atomName = line.substring(76, 78).trim();
                        } catch (Exception ex) {
                            atomName = atomType;
                        }
                        
                        atom = new Atom(atomSerial, atomName, atomX, atomY, atomZ, atomType);
                        lig.addAtom(atom);
                        
                        /*
                        System.out.println ("ATOM\t" +
                                             atomSerial + "\t" +
                                             atomName   + "\t" +
                                             atomX      + "\t" +
                                             atomY      + "\t" +
                                             atomZ      + "\t" +
                                             atomType
                                           );
                        * 
                        */
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                try {
                    in.close();
                } catch (IOException ex1) {
                    System.out.println(ex1.getMessage());
                }
                return null;
            }
            try {
                in.close();
            } catch (IOException ex1) {
                System.out.println(ex1.getMessage());
            }
            
            lig.setBoundingSphere(lig.getPoints());
            return lig;
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
