/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package autodockhelper;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Paolo
 */
class AutoDockThread extends Thread{

    private ADHFrame frame;
    private File dockingFile;
    
    public AutoDockThread(ADHFrame frame, File dockingFile) {
        this.frame = frame;
        this.dockingFile = dockingFile;
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
            outputFile = dockingFile.getPath().substring(0, dockingFile.getPath().length() - 3) + "dlg";
            
            commands = new ArrayList<String>();
            commands.add(ADHFrame.autoDockFile.getPath());
            commands.add("-p");
            commands.add(dockingFile.getPath());
            commands.add("-l");
            commands.add(outputFile);
            
            pb = new ProcessBuilder(commands);
            pb.directory(dockingFile.getParentFile());
            proc = pb.start();
            
            stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            
            exitCode = proc.waitFor();
            
            if (exitCode != 0) {
                String line, error = "";
                while ((line = stderr.readLine()) != null) {
                    if (line.trim().length() > 0) {
                        line = line.substring(line.indexOf("autodock4") + 11, line.length() - 1);
                        error += line + "\n";
                    }
                }
                JOptionPane.showMessageDialog(frame, error, "ERROR!", JOptionPane.ERROR_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(frame,
                        "AutoDock successful completion!\nThe operation log is in " + outputFile,
                        "DONE!", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "AutoDock failed for some reason!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
        
        frame.autoDockButton.setEnabled(true);
        frame.autoDockMenuItem.setEnabled(true);
        frame.openDockingMenuItem.setEnabled(true);
        frame.autoDockButton.setText("Launch AutoDock");
        frame.autoDockButton.setIcon(null);
    }
    
}
