/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autodockhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Paolo
 */
public class AutoGridThread extends Thread{
    
    private ADHFrame frame;
    private File gridFile;
    
    public AutoGridThread (ADHFrame frame, File gridFile) {
        this.frame = frame;
        this.gridFile = gridFile;
    }
    
    @Override
    public void run() {
        
        ArrayList<String> commands;
        ProcessBuilder pb;
        Process proc;
        BufferedReader stdout, stderr;
        int exitCode;
        String outputFile;
        
        try {
            outputFile = gridFile.getPath().substring(0, gridFile.getPath().length() - 3) + "glg";
            
            commands = new ArrayList<String>();
            commands.add(ADHFrame.autoGridFile.getPath());
            commands.add("-p");
            commands.add(gridFile.getPath());
            commands.add("-l");
            commands.add(outputFile);
            
            pb = new ProcessBuilder(commands);
            pb.directory(gridFile.getParentFile());
            proc = pb.start();
            
            stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            
            exitCode = proc.waitFor();
            
            if (exitCode != 0) {
                String line, error = "";
                while ((line = stderr.readLine()) != null) {
                    if (line.trim().length() > 0) {
                        line = line.substring(line.indexOf("autogrid4") + 11, line.length() - 1);
                        error += line + "\n";
                    }
                }
                JOptionPane.showMessageDialog(frame, error, "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(frame,
                        "AutoGrid successful completion!\nThe operation log is in " + outputFile,
                        "DONE!", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "AutoGrid failed for some reason!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        
        frame.autoGridButton.setEnabled(true);
        frame.autoGridMenuItem.setEnabled(true);
        frame.openGridMenuItem.setEnabled(true);
        frame.autoGridButton.setText("Launch AutoGrid");
        frame.autoGridButton.setIcon(null);
    }
}
