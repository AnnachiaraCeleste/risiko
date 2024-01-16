/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package risiko;

/**
 *
 * @author Annachiara
 */
public enum Colors {
    RED(255, 0, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    YELLOW(255, 255, 0),
    MAGENTA(255, 0, 255),
    CYAN(0, 255, 255),
    WHITE(255, 255, 255),
    BLACK(0, 0, 0);

    private Colors(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    private final int red;
    private final int green;
    private final int blue;

    @Override
    public String toString() {
        return this.name() + " (" + this.red + ", " + this.green + ", " + this.blue + ")";
    }
}
