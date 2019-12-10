import javax.swing.*;
import java.awt.*;

public class Particle extends JPanel {
    //Values of a particle
    private double temp;
    private String side;
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;


    public Particle(String side, String color) {
        this.side = side;
        if (color.equals("Blue")) {
            xVelocity = (int) (((Math.random() * 2) + 2) * randomDirection());
            yVelocity = (int) (((Math.random() * 2) + 2) * randomDirection());
        }
        else {
            xVelocity = (int) (((Math.random() * 2) + 4) * randomDirection());
            yVelocity = (int) (((Math.random() * 2) + 4) * randomDirection());
        }
        setTemp();

        if (side.equals("Left")) {
            x = 225 + (int) (50 * Math.random()); //Middle area random spawn
            y = 737 + (int) (50 * Math.random());
        }
        else {
            x = 725 + (int) (50 * Math.random());
            y = 737 + (int) (50 * Math.random());
        }
    }

    public void update(boolean openDoor) {

    }

    public int randomDirection() {
        double rando = Math.random();
        int direction;
        if (rando >= .5) {
            direction = 1;
        }
        else {
            direction = -1;
        }
        return direction;
    }

    public void setTemp() {
        double magnitude;
        magnitude = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
        temp = magnitude;
    }//Magnitude is the square root of the sums of component vectors

    public double getTemp() {return temp};

    public String getSide() {return side;}

    public int getX() {return x;}

    public int getY() {return y;}
}//END particle


