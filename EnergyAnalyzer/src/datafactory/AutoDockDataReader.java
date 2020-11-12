/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datafactory;

import datastructures.AutoDockData;
import datastructures.Cluster;
import datastructures.RMS;
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
public class AutoDockDataReader {
    public static AutoDockData readClusterData (File file) {
        BufferedReader in;
        String line;
        StringTokenizer t;
        String rmsValue;
        int rmsCount = 0, count;
            
        AutoDockData data = new AutoDockData();
        
        try {
            in = new BufferedReader(new FileReader(file));
            line = in.readLine();
            
            t = new StringTokenizer(line, " ");

            RMS rms = new RMS(0.0f);
            Cluster cluster = new Cluster(0);
            rms.add(cluster);
            data.addRMS(rms);
            
            while (t.hasMoreTokens()) {
                rmsValue = t.nextToken();
                if (!rmsValue.equals("energy")) {
                    rms = new RMS (Float.parseFloat(rmsValue));
                    data.addRMS(rms);
                    rmsCount++;
                }
            }
            int clusterNumber;
            float energy;
            
            while ((line = in.readLine()) != null) {
                t = new StringTokenizer (line, " ");
                
                count = rmsCount * 3 + 1;
                
                while (count-- > 0) {
                    t.nextToken();
                }
                energy = Float.parseFloat(t.nextToken());
                data.getRMS(0.0f).getCluster(0).add(energy);
                
                t = new StringTokenizer (line, " ");
                count = rmsCount;
                while (count-- > 0) {
                    clusterNumber = Integer.parseInt(t.nextToken());
                    rms = data.getRMS(rmsCount - count);
                    cluster = rms.getCluster(clusterNumber);
                    if (cluster == null) {
                        cluster = new Cluster (clusterNumber);
                        rms.add(cluster);
                    }
                    cluster.add(energy);
                    t.nextToken();
                }
            }
            in.close();
            
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cannot open \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AutoDockDataReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "\"" + file.getName() + "\" is not a well-formed AutoDock cluster file", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AutoDockDataReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Something went bad with \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AutoDockDataReader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return data;
    }
    
    public static void readExclusionList (File file, AutoDockData data) {
        BufferedReader in;
        String line;
        StringTokenizer t;
        String rmsValue;
        int clusterNumber;
        RMS rms;
        
        Iterator<RMS> iter = data.getRMSs().iterator();
        Iterator<Cluster> iter2;
        while (iter.hasNext()) {
             iter2 = iter.next().getClusters().iterator();
             while (iter2.hasNext()) {
                 iter2.next().setEnabled(true);
             }
        }
        
        try {
            in = new BufferedReader(new FileReader(file));
            
            while ((line = in.readLine()) != null) {
                t = new StringTokenizer (line, " ");
                rmsValue = t.nextToken();
                rmsValue = rmsValue.substring(0, rmsValue.length() - 1);
                rms = data.getRMS(Float.parseFloat(rmsValue));
                if (rms != null) {
                    while (t.hasMoreTokens()) {
                        clusterNumber = Integer.parseInt(t.nextToken());
                        rms.getCluster(clusterNumber).setEnabled(false);
                    }
                }
            }
            in.close();            
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Cannot open \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AutoDockDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "\"" + file.getName() + "\" is not a well-formed AutoDock exclusion list", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AutoDockDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Something went bad with \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AutoDockDataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
