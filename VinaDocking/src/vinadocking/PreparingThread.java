/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vinadocking;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Paolo Gatti
 */
public class PreparingThread extends Thread{
    private VDFrame frame;
    private File dnaFile, ligandFile;
    private boolean amideRotable;
    
    public PreparingThread (VDFrame frame, File dnaFile, File ligandFile, boolean amideRotable) {
        this.frame = frame;
        this.dnaFile = dnaFile;
        this.ligandFile = ligandFile;
        this.amideRotable = amideRotable;
    }
    
    @Override
    public void run() {
        try {
            ArrayList<String> commands;
            ProcessBuilder pb;
            Process proc;
            BufferedReader stdout, stderr;
            int exitCode;
            
            commands = new ArrayList<String>();
            commands.add(VDFrame.adtDir + "/prepare_receptor4.py");
            commands.add("-A bonds_hydrogens");
            commands.add("-U nphs_lps_waters");
            commands.add("-r " + dnaFile.getPath());
            commands.add("-o " + dnaFile.getPath() + "qt");
            pb = new ProcessBuilder(commands);
            proc = pb.start();
            /*
            Iterator<String> iter = commands.iterator();
            while (iter.hasNext())
                System.out.print(iter.next() + " ");
            System.out.println("\n");
            */
            stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            exitCode = proc.waitFor();
            
            if (exitCode != 0) {
                /*
                String line;
                while ((line = stderr.readLine()) != null)
                    System.out.println(line);
                */
                throw new Exception();
            }
            
        } catch (Exception ex) {
            frame.chargesAndRotations.setIcon(new ImageIcon(getClass().getResource("/data/uncheck.png")));
            JOptionPane.showMessageDialog(frame, "Failed adding charges to DNA molecule!", "ERROR!", JOptionPane.ERROR_MESSAGE);
            frame.dnaMenuItem.setEnabled(true);
            frame.ligandMenuItem.setEnabled(true);
            frame.adtMenuItem.setEnabled(true);
            frame.vinaMenuItem.setEnabled(true);
            frame.amideCheckBox.setEnabled(true);
            frame.prepareButton.setEnabled(true);
            return;
        }
        
        
        try {
            ArrayList<String> commands;
            ProcessBuilder pb;
            Process proc;
            BufferedReader stdout, stderr;
            int exitCode;
            
            commands = new ArrayList<String>();
            commands.add(VDFrame.adtDir + "/prepare_ligand4.py");
            commands.add("-A bonds_hydrogens");
            
            if (amideRotable){
                commands.add("-B amide");
            }
            
            commands.add("-l " + ligandFile.getPath());
            commands.add("-o " + ligandFile.getPath() + "qt");
            pb = new ProcessBuilder(commands);
            proc = pb.start();
            stdout = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            stderr = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
            exitCode = proc.waitFor();
            
            if (exitCode != 0) {
                throw new Exception();
            }
            
        } catch (Exception ex) {
                frame.chargesAndRotations.setIcon(new ImageIcon(getClass().getResource("/data/uncheck.png")));
                JOptionPane.showMessageDialog(frame, "Failed adding charges or rotable bonds to ligand molecule!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                frame.dnaMenuItem.setEnabled(true);
                frame.ligandMenuItem.setEnabled(true);
                frame.adtMenuItem.setEnabled(true);
                frame.vinaMenuItem.setEnabled(true);
                frame.amideCheckBox.setEnabled(true);
                frame.prepareButton.setEnabled(true);
                return;
        }
        
        frame.chargesAndRotations.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
        frame.dnaMenuItem.setEnabled(true);
        frame.ligandMenuItem.setEnabled(true);
        frame.adtMenuItem.setEnabled(true);
        frame.vinaMenuItem.setEnabled(true);
        frame.amideCheckBox.setEnabled(true);
        frame.prepareButton.setEnabled(true);
        frame.dockingButton.setEnabled(true);
    }
}
