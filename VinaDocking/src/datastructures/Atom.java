/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructures;

/**
 *
 * @author Paolo Gatti
 */
public class Atom {

    private int serial;
    private String name, element;
    private double x, y, z;

    public Atom(int s, String n, double xx, double yy, double zz, String e) {
        serial = s;
        name = n;
        x = xx;
        y = yy;
        z = zz;
        element = e;
    }

    public String getName() {
        return name;
    }

    public int getSerial() {
        return serial;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
    
    public String getElement() {
        return element;
    }
    
    @Override
    public String toString() {
        String spaces1 = " ";
        String spaces2 = " ";
        int nSpaces1 = (int) (Math.floor(Math.log10(serial)) + 1);
        int nSpaces2 = 3;
        int countSize = (int) (Math.floor(Math.log10(serial)) + 1);
        int nameSize = name.length();

        nSpaces1 -= countSize;
        nSpaces2 -= nameSize;

        while (nSpaces1-- > 0) {
            spaces1 += " ";
        }
        while (nSpaces2-- > 0) {
            spaces2 += " ";
        }

        return serial + ")" + spaces1 + name + spaces2 + "[x = "
                + x + ", y = " + y + ", z = " + z + "] " + element + "\n";
    }
}
