/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datafactory;

import datastructures.Conformation;
import datastructures.VinaData;
import java.io.*;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Paolo
 */
public class VinaDataReader {
    public static VinaData readVinaData (File file) {
        BufferedReader in;
        String line;
        StringTokenizer t;
        int mode;
        float energy;
        Conformation conf;

        VinaData data = new VinaData ();
        
        try {
            in = new BufferedReader(new FileReader (file));
            while ((line = in.readLine()) != null) {
                t = new StringTokenizer(line, " ");
                if (t.countTokens() == 4) {
                    try {
                        mode = Integer.parseInt(t.nextToken());
                        energy = Float.parseFloat(t.nextToken());
                        conf = new Conformation (mode, energy);
                        data.add(conf);
                    } catch (NumberFormatException nfe) {}
                }
            }
            in.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VinaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cannot open \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(VinaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "\"" + file.getName() + "\" is not a well-formed Vina log file", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (Exception ex) {
            Logger.getLogger(VinaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Something went bad with \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return data;
    }
    
    public static void readExclusionList (File file, VinaData data) {
        BufferedReader in;
        String line;
        StringTokenizer t;
        int mode;
        Conformation conf;
        Iterator<Conformation> iter;
        
        iter = data.getConformations().iterator();
        while (iter.hasNext()) {
            iter.next().setEnabled(true);
        }
        
        try {
            in = new BufferedReader(new FileReader (file));
            while ((line = in.readLine()) != null) {
                t = new StringTokenizer(line, " ");
                while (t.hasMoreTokens()) {
                    mode = Integer.parseInt(t.nextToken());
                    iter = data.getConformations().iterator();
                    while (iter.hasNext()) {
                        conf = iter.next();
                        
                        if (mode == conf.getMode()) {
                            conf.setEnabled(false);
                        }
                    }
                }
            }
            in.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VinaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cannot open \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(VinaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "\"" + file.getName() + "\" is not a well-formed Vina exclusion list", "ERROR!", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(VinaDataReader.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Something went bad with \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
