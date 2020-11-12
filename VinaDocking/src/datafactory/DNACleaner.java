/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datafactory;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paolo
 */
public class DNACleaner {
    public static File cleanPDB (File file) {
        BufferedReader in;
        PrintWriter out;
        String line, recordType, cleanedName, fileName;
        
        fileName = file.getName();
        cleanedName = fileName.substring(0, fileName.length() - 4) + "-cleaned.pdb";
        
        try {
            in = new BufferedReader (new FileReader (file));
            try {
                out = new PrintWriter(new FileWriter (file.getParent() + "/" + cleanedName));
                while ((line = in.readLine()) != null) {
                    try {
                        recordType = line.substring(0, 6).trim();
                        if (recordType.equals("ATOM")) {
                           out.println(line);
                        }
                    }
                    catch (Exception e) {
                        continue;
                    }
                }
                out.close();
                in.close();
            } catch (IOException ex) {
                //JOptionPane.showMessageDialog(null, "Something went bad with \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
                Logger.getLogger(DNACleaner.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            
        } catch (FileNotFoundException ex) {
            //JOptionPane.showMessageDialog(null, "Cannot open \"" + file.getName() + "\"", "ERROR!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DNACleaner.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return new File(file.getParent() + "/" + cleanedName);
    }
}
