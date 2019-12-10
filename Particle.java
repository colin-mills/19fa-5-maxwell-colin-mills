import javax.swing.*;

public class Particle extends JPanel {
    //Values of a particle
    private double temp;
    private String side;
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private int radius;


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
    }//END constructor

    public void update(boolean openDoor) {

        //Update side to determine behavior
        if (getX() + getRadius() <= 500) {
            this.side = "Left";
        }
        else {
            this.side = "Right";
        }

        //Update ball position
        this.x += xVelocity;
        this.y += yVelocity;

        if (getSide().equals("Left")) {
            if (!openDoor || y < 618 || y > 855) {
                if (x + getRadius() + xVelocity >= 500) {
                    xVelocity *= -1;
                }
            }//END if hitting right side
            if (x + radius + xVelocity <= 50) {
                xVelocity *= -1;
            }//END hitting left side
            if (y + radius + yVelocity >= 975) {
                yVelocity *= -1;
            }//END hitting top
            if (y + radius + yVelocity <= 500 ) {
                yVelocity *= -1;
            }
        }//END left side check

        if (getSide().equals("Left")) {
            if (!openDoor || y < 618 || y > 855) {
                if (x + getRadius() + xVelocity <= 500) {
                    xVelocity *= -1;
                }
            }//END if hitting left side
            if (x + radius + xVelocity >= 900) {
                xVelocity *= -1;
            }//END hitting right side
            if (y + radius + yVelocity >= 975) {
                yVelocity *= -1;
            }//END hitting top
            if (y + radius + yVelocity <= 500 ) {
                yVelocity *= -1;
            }
        }//END ride side check

    }//END update

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
    }//END random direction

    public void setTemp() {
        double magnitude;
        //Magnitude is the square root of the sums of component vectors
        magnitude = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
        temp = magnitude;
    }//END setTemp

    public double getTemp() {return temp;};

    public String getSide() {return side;}

    public int getX() {return x;}

    public int getY() {return y;}

    public int getRadius() {return radius;}
}//END particle


