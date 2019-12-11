import javax.swing.*;

/**
 * @author Colin Mills
 */
public class Particle extends JPanel {
    //Values of a particle
    private double temp;
    private String side;
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private int radius = 8;

    /**
     * Constructor that gets the particle moving
     * randomly assigns directions
     * randomly assigns speeds for red between 4-6 and for blue between 2-4
     * randomly assigns starting position of ball to allow variety of paths
     * @param side Lets Particle know which side to spawn the ball on
     * @param color Lets Particle know if it should make a red or blue
     */
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
            x = 225 + (int) (30 * Math.random()); //Middle area random spawn
            y = 200 + (int) (30 * Math.random());
        }
        else {
            x = 725 + (int) (30 * Math.random());
            y = 200 + (int) (30 * Math.random());
        }
    }//END constructor

    /**
     * Called every 15 milliseconds by runnable to update position of balls
     * @param openDoor affects behavior if door is open or closed
     */
    public void update(boolean openDoor) {

        //Update side to determine behavior
        if ((getX() + getRadius()) <= 450) {
            this.side = "Left";
        }
        else {
            this.side = "Right";
        }

        //Update ball position
        this.x += xVelocity;
        this.y += yVelocity;

        if (getSide().equals("Left")) {
            if (!openDoor || y > 300 || y < 100) {
                if (x + getRadius() + xVelocity >= 450) {
                    xVelocity *= -1;
                }
            }//END if hitting right side
            if (x <= 0) {
                xVelocity *= -1;
            }//END hitting left side
            if (y + radius * 2 >= 400) {
                yVelocity *= -1;
            }//END hitting top
            if (y <= 0 ) {
                yVelocity *= -1;
            }
        }//END left side check

        if (getSide().equals("Right")) {
            if (!openDoor || y > 300 || y < 100) {
                if (x <= 450) {
                    xVelocity *= -1;
                }
            }//END if hitting left side
            if (x + radius * 2 >= 900) {
                xVelocity *= -1;
            }//END hitting right side
            if (y + radius * 2 >= 400) {
                yVelocity *= -1;
            }//END hitting top
            if (y <= 0 ) {
                yVelocity *= -1;
            }//END hitting bottom
        }//END ride side check

    }//END update

    /**
     * allows constructor to assign direction randomly
     * @return either positive or negative direction
     */
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

    /**
     * sets temp so thread can call for this value
     * temp as determined as magnitude of vector
     */
    public void setTemp() {
        double magnitude;
        //Magnitude is the square root of the sums of component vectors
        magnitude = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
        temp = magnitude;
    }//END setTemp

    /**
     * passes temp to Thread
     * @return returns magnitude
     */
    public double getTemp() {return temp;};

    /**
     * Useful for counter in Thread
     * @return "Left" or "Right"
     */
    public String getSide() {return side;}

    /**
     * position used for paint
     * @return x coordinate withing gamespace
     */
    public int getX() {return x;}

    /**
     * position used for paint
     * @return y coordinate withing gamespace
     */
    public int getY() {return y;}

    /**
     * Accesor method for radius
     * @return if length and width are 16 radius will always be 8
     */
    public int getRadius() {return radius;}
}//END particle


