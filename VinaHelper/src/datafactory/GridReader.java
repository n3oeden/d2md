/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datafactory;

import datastructures.VinaParams;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author Paolo
 */
public class GridReader {
    
    public static VinaParams readGrid(File gridFile) {
        boolean ntpsCheck, spacingCheck, receptorCheck, gridcenterCheck;
        BufferedReader in;
        String line, key, receptor;
        StringTokenizer t;
        double width, height, depth, spacing, x, y, z;
        File receptorFile;
        VinaParams vinaParams;
        
        try {
            width = height = depth = spacing = x = y = z = 0.0;
            receptorFile = null;
            ntpsCheck = spacingCheck = receptorCheck = gridcenterCheck = false;
            
            in = new BufferedReader (new FileReader (gridFile));
            
            while ((line = in.readLine()) != null) {
                
                t = new StringTokenizer (line, " ");
                key = t.nextToken();
                
                if (key.equals("npts")) {
                    width = Double.parseDouble(t.nextToken());
                    height = Double.parseDouble(t.nextToken());
                    depth = Double.parseDouble(t.nextToken());
                    if (width > 0.0 && height > 0.0 && depth > 0.0) {
                        ntpsCheck = true;
                    }
                }
                else if (key.equals("spacing")) {
                    spacing = Double.parseDouble(t.nextToken());
                    if (spacing > 0.0) {
                        spacingCheck = true;
                    }
                }
                else if (key.equals("receptor")) {
                    receptor = t.nextToken();
                    receptorFile = new File(receptor);
                    if (receptorFile.isFile()) {
                        receptorCheck = true;
                    }
                    else {
                        receptorFile = new File(gridFile.getParent() + "/" + receptor);
                        if (receptorFile.isFile()) {
                            receptorCheck = true;
                        }
                    }
                }
                else if (key.equals("gridcenter")) {
                    x = Double.parseDouble(t.nextToken());
                    y = Double.parseDouble(t.nextToken());
                    z = Double.parseDouble(t.nextToken());
                    gridcenterCheck = true;
                }
            }
            
            if (! ntpsCheck || ! spacingCheck) {
                JOptionPane.showMessageDialog(null, "Cannot read the box dimensions!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (! receptorCheck) {
                JOptionPane.showMessageDialog(null, "Cannot read the receptor file!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            if (! gridcenterCheck) {
                JOptionPane.showMessageDialog(null, "Cannot read the box center!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            vinaParams = new VinaParams();
            vinaParams.setReceptorFile(receptorFile);
            vinaParams.setCenter(x, y, z);
            vinaParams.setWidth(width * spacing);
            vinaParams.setHeight(height * spacing);
            vinaParams.setDepth(depth * spacing);
            
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Wrong parameters in the grid file!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Cannot read grid file \"" + gridFile.getPath() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        return vinaParams;
    }
}
