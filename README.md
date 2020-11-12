# d2md
A DNA-Drug Molecular Docking suite.

The suite aims to help automatizing and batch processing the molecular docking studies of DNA-Drug complexes. All the upcoming programs work on Windows, Mac and Linux.

## Software

**AutoDock Helper**

A simple GUI to the AutoGrid and AutoDock programs, in the case the AutoDockTools GUI is too buggy also for you!

Requirements
* Java 6+
* AutoDock 4.2+

**Vina Helper**

Starting from an AutoDock 4.2 grid file and the ligand PDBQT, this program allows you to perform a docking simulation by AutoDock Vina without having to build any configuration file or recalculate any grid.

Requirements:
* Java 6+
* AutoDock 4.2+
* AutoDock Vina 1.1.2+

**Energy Analyzer**

Output of AutoDock and Vina simulations can be analyzed better with these tools, by doing a mean on all conformations or on a particular RMS cluster set.

Requirements:
* Java 6+
* AutoDock 4.2+
* AutoDock Vina 1.1.2+
* MGLTools 1.5.6rc2+

**Vina Docking**

This programs is a full featured DNA-Drug Vina docking simulation. It's completely automatized since starting from the ligand and DNA PDB files one needs only to select one of the automatically discovered intercalation spaces (or none if blind docking) and start a simulation!

Requirements:
* Java 6+
* Java 3D 1.5.2+
* BioJava 3.0.2+
* AutoDock Vina 1.1.2+
* MGLTools 1.5.6rc2+

## Scripts
In order to obtain full batch processing 2 BASH scripts are available for AutoDock and Vina.
Another BASH script is also available if one would like to perform Molecular Dynamics with Gromacs.
All the scripts run interactively if no arguments are passed to them.

**AutoDock script**

``` $ ad -g | SKIP -d | SKIP ```. 

Avoid any extension! Assuming .gpf for grid and .dpf for docking. 

Requirements:
* BASH 3.2+
* AutoDock 4.2+ 

**AutoDock Vina script**

``` $ av -l -g [-c #{cpu}] [-e exhaustiveness] [-m #{modes}] ```

Avoid any extension. Assuming .pdbqt for ligand and .gpf for grid. Number of CPUs, modes and exhaustiveness should be greater than 0.

Requirements:
* BASH 3.2+
* AutoDock 4.2+
* AutoDock Vina 1.1.2+

**Gromacs script**

``` $ gr [quiet] ```

Use ```quiet``` to suppress the standard output of all intermediate steps.

Requirements:
* BASH 3.2+
* Gromacs 4.5.5+
* AmberTools 1.5+
* ACPYPE
