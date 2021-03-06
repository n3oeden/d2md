/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vinadocking;

import datafactory.DNACleaner;
import datafactory.DNAReader;
import datafactory.LigandReader;
import datastructures.DNA;
import datastructures.IntercalationSpace;
import datastructures.Ligand;
import datastructures.MBB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.vecmath.Point3d;

/**
 *
 * @author Paolo
 */
public class VDFrame extends javax.swing.JFrame {
    public static final File configFile = new File(System.getenv("HOME") + "/.vinadocking");
    public static File workingDir, adtDir, vinaFile;
    
    private File dnaFile, dnaCleanedFile, ligandFile;
    private DNA dna;
    private Ligand lig;
    
    /**
     * Creates new form VDFrame
     */
    public VDFrame() {
        dnaFile = dnaCleanedFile = ligandFile;
        dna = null;
        lig = null;
        
        initComponents();
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getSize().width) / 2, (dim.height - getSize().height) / 2); 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cpuSlider = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        exhaustivenessTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        modesTextField = new javax.swing.JTextField();
        dockingButton = new javax.swing.JButton();
        amideCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        spaceSelect = new javax.swing.JComboBox();
        prepareButton = new javax.swing.JButton();
        dockingPanel = new javax.swing.JPanel();
        dnaCleaning = new javax.swing.JLabel();
        dnaAnalysis = new javax.swing.JLabel();
        intercalationSpaceIdentification = new javax.swing.JLabel();
        ligandAnalysis = new javax.swing.JLabel();
        chargesAndRotations = new javax.swing.JLabel();
        dockingSimulation = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        dnaMenuItem = new javax.swing.JMenuItem();
        ligandMenuItem = new javax.swing.JMenuItem();
        fileMenuSeparator = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        adtMenuItem = new javax.swing.JMenuItem();
        vinaMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vina Docking");
        setIconImage(new ImageIcon (getClass().getResource("/data/icon.png")).getImage());

        optionsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Options"));

        jLabel2.setText("Number of CPUs:");

        cpuSlider.setMajorTickSpacing(1);
        cpuSlider.setMaximum(Runtime.getRuntime().availableProcessors());
        cpuSlider.setMinimum(1);
        cpuSlider.setMinorTickSpacing(1);
        cpuSlider.setPaintLabels(true);
        cpuSlider.setSnapToTicks(true);

        jLabel3.setText("Exhaustiveness:");

        exhaustivenessTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        exhaustivenessTextField.setText("9");

        jLabel4.setText("Number of modes:");

        modesTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        modesTextField.setText("8");

        dockingButton.setText("Start Docking!");
        dockingButton.setEnabled(false);
        dockingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dockingButtonActionPerformed(evt);
            }
        });

        amideCheckBox.setSelected(true);
        amideCheckBox.setText("Allow amide bonds to be rotable");

        jLabel1.setText("Intercalation site:");

        spaceSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None (blind docking)" }));

        prepareButton.setText("Prepare molecules");
        prepareButton.setEnabled(false);
        prepareButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prepareButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout optionsPanelLayout = new org.jdesktop.layout.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(optionsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(optionsPanelLayout.createSequentialGroup()
                        .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel4)
                            .add(jLabel2)
                            .add(jLabel3))
                        .add(18, 18, 18)
                        .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, optionsPanelLayout.createSequentialGroup()
                                .add(0, 0, Short.MAX_VALUE)
                                .add(cpuSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(optionsPanelLayout.createSequentialGroup()
                                .add(modesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(prepareButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(optionsPanelLayout.createSequentialGroup()
                                .add(exhaustivenessTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 65, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(dockingButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .add(optionsPanelLayout.createSequentialGroup()
                        .add(111, 111, 111)
                        .add(spaceSelect, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(optionsPanelLayout.createSequentialGroup()
                        .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(amideCheckBox)
                            .add(jLabel1))
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(optionsPanelLayout.createSequentialGroup()
                .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(spaceSelect, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(amideCheckBox)
                .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(optionsPanelLayout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(jLabel2))
                    .add(optionsPanelLayout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(cpuSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(11, 11, 11)
                .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(prepareButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(modesTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(4, 4, 4)
                .add(optionsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(dockingButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(exhaustivenessTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        dockingPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Docking Simulation"));

        dnaCleaning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/uncheck.png"))); // NOI18N
        dnaCleaning.setText("DNA file clean up");

        dnaAnalysis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/uncheck.png"))); // NOI18N
        dnaAnalysis.setText("DNA structure analysis");

        intercalationSpaceIdentification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/uncheck.png"))); // NOI18N
        intercalationSpaceIdentification.setText("DNA intercalation spaces identification");

        ligandAnalysis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/uncheck.png"))); // NOI18N
        ligandAnalysis.setText("Ligand structure analysis");

        chargesAndRotations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/uncheck.png"))); // NOI18N
        chargesAndRotations.setText("Atom charges addition and rotable bonds discovery");

        dockingSimulation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/uncheck.png"))); // NOI18N
        dockingSimulation.setText("Molecular docking simulation");

        org.jdesktop.layout.GroupLayout dockingPanelLayout = new org.jdesktop.layout.GroupLayout(dockingPanel);
        dockingPanel.setLayout(dockingPanelLayout);
        dockingPanelLayout.setHorizontalGroup(
            dockingPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dockingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dockingPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(dockingPanelLayout.createSequentialGroup()
                        .add(dockingPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(dnaCleaning)
                            .add(dnaAnalysis)
                            .add(intercalationSpaceIdentification)
                            .add(ligandAnalysis)
                            .add(chargesAndRotations)
                            .add(dockingSimulation))
                        .add(0, 0, Short.MAX_VALUE))
                    .add(progressBar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dockingPanelLayout.setVerticalGroup(
            dockingPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(dockingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(dnaCleaning)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dnaAnalysis)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(intercalationSpaceIdentification)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ligandAnalysis)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chargesAndRotations)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dockingSimulation)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");

        dnaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/open.png"))); // NOI18N
        dnaMenuItem.setText("Open DNA file...");
        dnaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dnaMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(dnaMenuItem);

        ligandMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/open.png"))); // NOI18N
        ligandMenuItem.setText("Open ligand file...");
        ligandMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ligandMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(ligandMenuItem);
        fileMenu.add(fileMenuSeparator);

        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/exit.png"))); // NOI18N
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        adtMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/dir.png"))); // NOI18N
        adtMenuItem.setText("AutoDockTools directory...");
        adtMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adtMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(adtMenuItem);

        vinaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/exe.png"))); // NOI18N
        vinaMenuItem.setText("Vina executable file...");
        vinaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vinaMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(vinaMenuItem);

        menuBar.add(editMenu);

        helpMenu.setText("Help");

        aboutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/about.png"))); // NOI18N
        aboutMenuItem.setText("About...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(optionsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(dockingPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(optionsPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(dockingPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dockingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dockingButtonActionPerformed
        int exhaustiveness, modes, cpu;
        Point3d center;
        double width, height, depth;
        
        if (vinaFile != null && vinaFile.isFile()) {
        
            cpu = cpuSlider.getValue();

            try{
                exhaustiveness = Integer.parseInt(exhaustivenessTextField.getText());
                if (exhaustiveness < 1)
                    throw new NumberFormatException();
            }
            catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "The exhaustiveness should be greater than 0!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try{
                modes = Integer.parseInt(modesTextField.getText());
                if (modes < 1)
                    throw new NumberFormatException();
            }
            catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "The number of modes should be a greater than 0!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (spaceSelect.getSelectedIndex() == 0) {
                MBB dnaMBB = dna.getMBB();
                center = dnaMBB.getCenter();
                width = dnaMBB.getWidth() + 2; // +2 is for enlarging a bit the box
                height = dnaMBB.getHeight() + 2;
                depth = dnaMBB.getDepth() + 2;
            }
            else {
                IntercalationSpace is = dna.getSpace(spaceSelect.getSelectedIndex());
                center = is.getCenter();
                width = height = depth = lig.getBoundingSphere().getRadius() * 2 + 2;
            }
            
            //System.out.println("center = ( " + center.x + ", " + center.y + ", " + center.z + " )");
            //System.out.println("width = " + width + " height = " + height + " depth = " + depth);
            
            dockingButton.setEnabled(false);
            prepareButton.setEnabled(false);
            dnaMenuItem.setEnabled(false);
            ligandMenuItem.setEnabled(false);
            adtMenuItem.setEnabled(false);
            vinaMenuItem.setEnabled(false);
            amideCheckBox.setEnabled(false);
            spaceSelect.setEnabled(false);
            cpuSlider.setEnabled(false);
            exhaustivenessTextField.setEnabled(false);
            modesTextField.setEnabled(false);
            dockingSimulation.setIcon(new ImageIcon(getClass().getResource("/data/loading.gif")));

            new DockingThread(this, dnaCleanedFile, ligandFile,
                    center, width, height, depth, cpu, exhaustiveness, modes).start();
        }
        else {
            JOptionPane.showMessageDialog(this, "Choose Vina executable first!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_dockingButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        setVisible(false);
        dispose();
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void dnaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dnaMenuItemActionPerformed
        File file;
        JFileChooser open = new JFileChooser();
        
        prepareButton.setEnabled(false);
        dockingButton.setEnabled(false);
        
        open.setDialogTitle("Open");
        open.setDialogType(JFileChooser.OPEN_DIALOG);
        open.setAcceptAllFileFilterUsed(false);
        open.setFileFilter(new FileNameExtensionFilter("PDB file", "pdb", "PDB"));
        
        if (dnaFile != null && dnaFile.isFile()) {
            open.setCurrentDirectory(dnaFile);
            open.setSelectedFile(dnaFile);
        }
        else if (workingDir != null && workingDir.isDirectory()) {
            open.setCurrentDirectory(workingDir);
        }
        
        if (open.showOpenDialog(this) == JFileChooser.APPROVE_OPTION &&
                (file = open.getSelectedFile()) != null) {
            
            if (file.getPath().indexOf(' ') >= 0) {
                
                JOptionPane.showMessageDialog(this, "Due to a bug in AutoDockTools utilities\nthe file paths and names should not contain any space", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            dnaFile = file;
            updateWorkingDir (file);
            
            dnaCleaning.setIcon(new ImageIcon(getClass().getResource("/data/loading.gif")));
            dnaCleanedFile = DNACleaner.cleanPDB(file);
            
            if (dnaCleanedFile == null || ! dnaCleanedFile.isFile()) {
                dockingButton.setEnabled(false);
                dnaCleaning.setIcon(new ImageIcon(getClass().getResource("/data/uncheck.png")));
                JOptionPane.showMessageDialog(this, "DNA cleaning failed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            dnaCleaning.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
            dnaAnalysis.setIcon(new ImageIcon(getClass().getResource("/data/loading.gif")));
            
            dna = DNAReader.readPDB(dnaCleanedFile.getPath());
            
            if (dna == null || ! dna.calculateTopology()) {
                dockingButton.setEnabled(false);
                dnaAnalysis.setIcon(new ImageIcon(getClass().getResource("/data/uncheck.png")));
                JOptionPane.showMessageDialog(this, "DNA reading failed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            dnaAnalysis.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
            intercalationSpaceIdentification.setIcon(new ImageIcon(getClass().getResource("/data/loading.gif")));
            
            spaceSelect.removeAllItems();
            spaceSelect.addItem("None (blind docking)");
            
            if (dna.findIntercalationSpaces() > 0) {
                Iterator<IntercalationSpace> iter = dna.getSpaces().iterator();
                IntercalationSpace is;
                while (iter.hasNext()) {
                    is = iter.next();
                    spaceSelect.addItem(is.getNum() + ": " + is.getDesc());
                }
            }
            intercalationSpaceIdentification.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
            
            if (lig != null)
                prepareButton.setEnabled(true);
        }
    }//GEN-LAST:event_dnaMenuItemActionPerformed

    private void ligandMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ligandMenuItemActionPerformed
        File file;
        JFileChooser open = new JFileChooser();
        
        prepareButton.setEnabled(false);
        dockingButton.setEnabled(false);
        
        open.setDialogTitle("Open");
        open.setDialogType(JFileChooser.OPEN_DIALOG);
        open.setAcceptAllFileFilterUsed(false);
        open.setFileFilter(new FileNameExtensionFilter("PDB file", "pdb", "PDB"));
        
        if (ligandFile != null && ligandFile.isFile()) {
            open.setCurrentDirectory(ligandFile);
            open.setSelectedFile(ligandFile);
        }
        else if (workingDir != null && workingDir.isDirectory()) {
            open.setCurrentDirectory(workingDir);
        }
        
        if (open.showOpenDialog(this) == JFileChooser.APPROVE_OPTION &&
                (file = open.getSelectedFile()) != null) {
            
            if (file.getPath().indexOf(' ') >= 0) {
                
                JOptionPane.showMessageDialog(this, "Due to a bug in AutoDockTools utilities\nthe file paths and names should not contain any space", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ligandFile = file;
            updateWorkingDir (file);
            
            ligandAnalysis.setIcon(new ImageIcon(getClass().getResource("/data/loading.gif")));
            lig = LigandReader.readPDB(file);
            
            if (lig == null) {
                dockingButton.setEnabled(false);
                ligandAnalysis.setIcon(new ImageIcon(getClass().getResource("/data/uncheck.png")));
                JOptionPane.showMessageDialog(this, "Ligand reading failed!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            lig.setBoundingSphere(lig.getPoints());
            ligandAnalysis.setIcon(new ImageIcon(getClass().getResource("/data/check.png")));
            
            if (dna != null)
                prepareButton.setEnabled(true);
        }
    }//GEN-LAST:event_ligandMenuItemActionPerformed

    private void adtMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adtMenuItemActionPerformed
        File file;
        JFileChooser open = new JFileChooser();
        
        open.setDialogTitle("Open");
        open.setDialogType(JFileChooser.OPEN_DIALOG);
        open.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        if (adtDir != null && adtDir.isDirectory()) {
            open.setCurrentDirectory(adtDir);
        }
        
        if (open.showOpenDialog(this) == JFileChooser.APPROVE_OPTION &&
                (file = open.getSelectedFile()) != null) {
            updateAdtDir(file);
        }
    }//GEN-LAST:event_adtMenuItemActionPerformed

    private void vinaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vinaMenuItemActionPerformed
        File file;
        JFileChooser open = new JFileChooser();
        
        open.setDialogTitle("Open");
        open.setDialogType(JFileChooser.OPEN_DIALOG);
        
        if (vinaFile != null && vinaFile.isFile()) {
            open.setCurrentDirectory(vinaFile);
            open.setSelectedFile(vinaFile);
        }
        
        if (open.showOpenDialog(this) == JFileChooser.APPROVE_OPTION &&
                (file = open.getSelectedFile()) != null) {
            updateVinaFile(file);
        }
    }//GEN-LAST:event_vinaMenuItemActionPerformed

    private void prepareButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prepareButtonActionPerformed
        
        if (adtDir != null && adtDir.isDirectory()) {
        
            dnaMenuItem.setEnabled(false);
            ligandMenuItem.setEnabled(false);
            adtMenuItem.setEnabled(false);
            vinaMenuItem.setEnabled(false);
            amideCheckBox.setEnabled(false);
            prepareButton.setEnabled(false);
            chargesAndRotations.setIcon(new ImageIcon(getClass().getResource("/data/loading.gif")));

            new PreparingThread(this, dnaCleanedFile, ligandFile, amideCheckBox.isSelected()).start();
        }
        else {
            JOptionPane.showMessageDialog(this, "Choose AutoDockTools directory first!", "WARNING!", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_prepareButtonActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        String message = "\n   Vina Docking\n" +
                         "    Version 1.0\n\n" + 
                         "© 2012 - Paolo Gatti\n" +
                         "n3o.eden@gmail.com";
        JOptionPane.showMessageDialog(this, message, "About...", JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(getClass().getResource("/data/aboutImg.png")));
    }//GEN-LAST:event_aboutMenuItemActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        if (configFile.isFile()) {
            
            try {
                String line, key, value;
                String content = "";
                BufferedReader in = new BufferedReader (new FileReader (configFile));
                
                while ((line = in.readLine()) != null) {
                    content += line + "\n";
                }
                in.close();
                
                StringTokenizer t = new StringTokenizer(content, "\n"), t2;
                
                while (t.hasMoreTokens()) {
                    line = t.nextToken();
                    t2 = new StringTokenizer (line, "=");
                    key = t2.nextToken().trim();
                    value = t2.nextToken().trim();
                    if (key.equals("ADTDir")) {
                        adtDir = new File (value);
                    }
                    else if (key.equals("VinaFile")) {
                        vinaFile = new File (value);
                    }
                    else if (key.equals("WorkingDir")) {
                        workingDir = new File (value);
                    }
                    else {
                        throw new IOException();
                    }
                }
                in.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VDFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /*
         * Create and display the form
         */
        Locale.setDefault(Locale.US);
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new VDFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    javax.swing.JMenuItem adtMenuItem;
    javax.swing.JCheckBox amideCheckBox;
    javax.swing.JLabel chargesAndRotations;
    javax.swing.JSlider cpuSlider;
    javax.swing.JLabel dnaAnalysis;
    javax.swing.JLabel dnaCleaning;
    javax.swing.JMenuItem dnaMenuItem;
    javax.swing.JButton dockingButton;
    private javax.swing.JPanel dockingPanel;
    javax.swing.JLabel dockingSimulation;
    private javax.swing.JMenu editMenu;
    javax.swing.JTextField exhaustivenessTextField;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPopupMenu.Separator fileMenuSeparator;
    private javax.swing.JMenu helpMenu;
    javax.swing.JLabel intercalationSpaceIdentification;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    javax.swing.JLabel ligandAnalysis;
    javax.swing.JMenuItem ligandMenuItem;
    private javax.swing.JMenuBar menuBar;
    javax.swing.JTextField modesTextField;
    private javax.swing.JPanel optionsPanel;
    javax.swing.JButton prepareButton;
    javax.swing.JProgressBar progressBar;
    javax.swing.JComboBox spaceSelect;
    javax.swing.JMenuItem vinaMenuItem;
    // End of variables declaration//GEN-END:variables

    private static void updateAdtDir(File file) {
        adtDir = file;
        
        boolean keyFound = false;
        try {
            if (configFile.isFile()) {
                String line, line2, key, value;
                String content = "";
                BufferedReader in = new BufferedReader (new FileReader (configFile));

                while ((line = in.readLine()) != null) {
                    content += line + "\n";
                }
                in.close();

                PrintWriter out = new PrintWriter (new FileWriter (configFile));
                StringTokenizer t = new StringTokenizer(content, "\n"), t2;

                while (t.hasMoreTokens()) {
                    line2 = t.nextToken();
                    t2 = new StringTokenizer (line2, "=");
                    key = t2.nextToken().trim();
                    value = t2.nextToken().trim();
                    if (! key.equals("ADTDir")) {
                        out.println(line2);
                    }
                    else {
                        keyFound = true;
                        out.println("ADTDir = " + adtDir.getPath());
                    }
                }
                if (! keyFound) {
                    out.println("ADTDir = " + adtDir.getPath());
                }
                out.close();
            }
            else {
                PrintWriter out = new PrintWriter (new FileWriter (System.getenv("HOME") + "/.vinadocking"));
                out.println("ADTDir = " + adtDir.getPath());
                out.close();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void updateVinaFile(File file) {
        vinaFile = file;
        
        boolean keyFound = false;
        try {
            if (configFile.isFile()) {
                String line, line2, key, value;
                String content = "";
                BufferedReader in = new BufferedReader (new FileReader (configFile));

                while ((line = in.readLine()) != null) {
                    content += line + "\n";
                }
                in.close();

                PrintWriter out = new PrintWriter (new FileWriter (configFile));
                StringTokenizer t = new StringTokenizer(content, "\n"), t2;

                while (t.hasMoreTokens()) {
                    line2 = t.nextToken();
                    t2 = new StringTokenizer (line2, "=");
                    key = t2.nextToken().trim();
                    value = t2.nextToken().trim();
                    if (! key.equals("VinaFile")) {
                        out.println(line2);
                    }
                    else {
                        keyFound = true;
                        out.println("VinaFile = " + vinaFile.getPath());
                    }
                }
                if (! keyFound) {
                    out.println("VinaFile = " + vinaFile.getPath());
                }
                out.close();
            }
            else {
                PrintWriter out = new PrintWriter (new FileWriter (System.getenv("HOME") + "/.vinadocking"));
                out.println("VinaFile = " + vinaFile.getPath());
                out.close();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private static void updateWorkingDir(File file) {
        workingDir = file.getParentFile();
        
        boolean keyFound = false;
        try {
            if (configFile.isFile()) {
                String line, line2, key, value;
                String content = "";
                BufferedReader in = new BufferedReader (new FileReader (configFile));

                while ((line = in.readLine()) != null) {
                    content += line + "\n";
                }
                in.close();

                PrintWriter out = new PrintWriter (new FileWriter (configFile));
                StringTokenizer t = new StringTokenizer(content, "\n"), t2;
                
                while (t.hasMoreTokens()) {
                    line2 = t.nextToken();
                    t2 = new StringTokenizer (line2, "=");
                    key = t2.nextToken().trim();
                    value = t2.nextToken().trim();
                    if (! key.equals("WorkingDir")) {
                        out.println(line2);
                    }
                    else {
                        keyFound = true;
                        out.println ("WorkingDir = " + workingDir.getPath());
                    }
                }
                if (! keyFound) {
                    out.println ("WorkingDir = " + workingDir.getPath());
                }
                out.close();
            }
            else {
                PrintWriter out = new PrintWriter (new FileWriter (System.getenv("HOME") + "/.vinadocking"));
                out.println("WorkingDir = " + workingDir.getPath());
                out.close();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
