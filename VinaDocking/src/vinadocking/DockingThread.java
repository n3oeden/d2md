/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vinadocking;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.vecmath.Point3d;

/**
 *
 * @author Paolo Gatti
 */
public class DockingThread extends Thread{
    private VDFrame frame;
    private File dnaFile, ligandFile;
    private Point3d center;
    private double width, height, depth;
    private int cpu, exhaustiveness, modes;
    
    public DockingThread (VDFrame frame, File dnaFile, File ligandFile, Point3d center,
            double width, double height, double depth, int c, int e, int m) {
        
        this.frame = frame;
        this.dnaFile = dnaFile;
        this.ligandFile = ligandFile;
        this.center = center;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.cpu = c;
        this.exhaustiveness = e;
        this.modes = m;
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
                    substring(0, ligandFile.getPath().length() - 4) +
                    "_vina";
            
            commands = new ArrayList<String>();
            commands.add(VDFrame.vinaFile.getPath());
            commands.add("--receptor");
            commands.add(dnaFile.getPath() + "qt");
            commands.add("--ligand");
            commands.add(ligandFile.getPath() + "qt");
            commands.add("--center_x");
            commands.add(nf.format(center.x));
            commands.add("--center_y");
            commands.add(nf.format(center.y));
            commands.add("--center_z");
            commands.add(nf.format(center.z));
            commands.add("--size_x");
            commands.add(nf.format(width));
            commands.add("--size_y");
            commands.add(nf.format(height));
            commands.add("--size_z");
            commands.add(nf.format(depth));
            commands.add("--out");
            commands.add(outputFile + ".pdbqt");
            commands.add("--log");
            commands.add(outputFile + ".log");
            commands.add("--cpu");
            commands.add(String.valueOf(cpu));
            commands.add("--exhaustiveness");
            commands.add(String.valueOf(exhaustiveness));
            commands.add("--num_modes");
            commands.add(String.valueOf(modes));
            /*
            Iterator<String> iter = commands.iterator();
            while (iter.hasNext()) {
                System.out.println(iter.next());
            }
            */
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
                /*
                System.out.println(exitCode);
                
                String line2;
                while ((line2 = stderr.readLine()) != null) {
                    System.out.println(line2);
                }
                */
                throw new Exception();
            }
            else {
                frame.dockingSimulation.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
                frame.dockingButton.setEnabled(true);
                frame.prepareButton.setEnabled(true);
                frame.dnaMenuItem.setEnabled(true);
                frame.ligandMenuItem.setEnabled(true);
                frame.adtMenuItem.setEnabled(true);
                frame.vinaMenuItem.setEnabled(true);
                frame.amideCheckBox.setEnabled(true);
                frame.spaceSelect.setEnabled(true);
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
            frame.dockingSimulation.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
            frame.dockingButton.setEnabled(true);
            frame.prepareButton.setEnabled(true);
            frame.dnaMenuItem.setEnabled(true);
            frame.ligandMenuItem.setEnabled(true);
            frame.adtMenuItem.setEnabled(true);
            frame.vinaMenuItem.setEnabled(true);
            frame.amideCheckBox.setEnabled(true);
            frame.spaceSelect.setEnabled(true);
            frame.cpuSlider.setEnabled(true);
            frame.exhaustivenessTextField.setEnabled(true);
            frame.modesTextField.setEnabled(true);
            frame.progressBar.setStringPainted(false);
            frame.progressBar.setValue(0);
            JOptionPane.showMessageDialog(frame, "Docking simulation failed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
