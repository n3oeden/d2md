/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vinahelper;

import datastructures.VinaParams;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Paolo Gatti
 */
public class DockingThread extends Thread{
    private VHFrame frame;
    private File ligandFile;
    private VinaParams vinaParams;
    
    public DockingThread (VHFrame frame, File ligandFile, VinaParams vinaParams) {
        
        this.frame = frame;
        this.ligandFile = ligandFile;
        this.vinaParams = vinaParams;
    }
    
    @Override
    public void run() {
        try {
            ArrayList<String> commands;
            ProcessBuilder pb;
            Process proc;
            BufferedReader stdout, stderr;
            int exitCode;
            
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3);
            
            String outputFile = ligandFile.getPath().
                    substring(0, ligandFile.getPath().length() - 6) +
                    "_vina";
            
            commands = new ArrayList<String>();
            
            commands.add(VHFrame.vinaFile.getPath());
            commands.add("--receptor");
            commands.add(vinaParams.getReceptorFile().getPath());
            commands.add("--ligand");
            commands.add(ligandFile.getPath());
            commands.add("--center_x");
            commands.add(nf.format(vinaParams.getCenter().x));
            commands.add("--center_y");
            commands.add(nf.format(vinaParams.getCenter().y));
            commands.add("--center_z");
            commands.add(nf.format(vinaParams.getCenter().y));
            commands.add("--size_x");
            commands.add(nf.format(vinaParams.getWidth()));
            commands.add("--size_y");
            commands.add(nf.format(vinaParams.getHeight()));
            commands.add("--size_z");
            commands.add(nf.format(vinaParams.getDepth()));
            commands.add("--out");
            commands.add(outputFile + ".pdbqt");
            commands.add("--log");
            commands.add(outputFile + ".log");
            commands.add("--cpu");
            commands.add(String.valueOf(vinaParams.getCpu()));
            commands.add("--exhaustiveness");
            commands.add(String.valueOf(vinaParams.getExhaustiveness()));
            commands.add("--num_modes");
            commands.add(String.valueOf(vinaParams.getModes()));
            
            
            Iterator<String> iter = commands.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
            
            pb = new ProcessBuilder(commands);
            proc = pb.start();
            
            stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            
            frame.progressBar.setStringPainted(true);
            int c, counter=0;
            while ((c = stdout.read()) != -1) {
                if (c == '*') {
                    frame.progressBar.setValue(counter);
                    counter += 2;
                }
            }
            frame.progressBar.setStringPainted(false);
            frame.progressBar.setValue(0);
            
            exitCode = proc.waitFor();
            
            if (exitCode != 0) {
                
                System.out.println(exitCode);
                
                String line2;
                while ((line2 = stderr.readLine()) != null) {
                    System.out.println(line2);
                }
                
                throw new Exception();
            }
            else {
                frame.dockingButton.setEnabled(true);
                frame.gridMenuItem.setEnabled(true);
                frame.ligandMenuItem.setEnabled(true);
                frame.vinaMenuItem.setEnabled(true);
                frame.cpuSlider.setEnabled(true);
                frame.exhaustivenessTextField.setEnabled(true);
                frame.modesTextField.setEnabled(true);
                frame.progressBar.setStringPainted(false);
                frame.progressBar.setValue(0);
                
                String outputConf = outputFile.substring(outputFile.lastIndexOf('/') + 1) + ".pdbqt";
                String outputLog = outputFile.substring(outputFile.lastIndexOf('/') + 1) + ".log";
                
                JOptionPane.showMessageDialog(frame,
                        "<html><center><b><u>THE DOCKING SIMULATION WAS COMPLETED SUCCESSFULLY!</u></b></center><br>"
                        + "<p>The output directory is <b>" + ligandFile.getParent() + "<b></p><br>"
                        + "<p>The resulting conformations are encoded in <b>" + outputConf + "</b></p><br>"
                        + "<p>A copy of the operation log is in <b>" + outputLog + "</b></p></html>",
                        "DONE!", JOptionPane.INFORMATION_MESSAGE);
                
            }
            
            
            
        } catch (Exception ex) {
            frame.dockingButton.setEnabled(true);
            frame.gridMenuItem.setEnabled(true);
            frame.ligandMenuItem.setEnabled(true);
            frame.vinaMenuItem.setEnabled(true);
            frame.cpuSlider.setEnabled(true);
            frame.exhaustivenessTextField.setEnabled(true);
            frame.modesTextField.setEnabled(true);
            frame.progressBar.setStringPainted(false);
            frame.progressBar.setValue(0);
            JOptionPane.showMessageDialog(frame, "Docking simulation failed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
