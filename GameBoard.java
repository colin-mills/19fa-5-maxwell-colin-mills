import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * @author Colin Mills
 */
public class GameBoard extends JPanel {

    //keep track of # of particles and where
    static int counterColdLeft; //# blue guys on left
    static int counterHotLeft; //# red guys on left
    static int counterColdRight; //# blue guys on right
    static int counterHotRight; //# red guys on right
    //Keep track of each instance of particles
    static Vector<Particle> hotOnes = new Vector<>();
    static Vector<Particle> coldOnes = new Vector<>();
    //keep track of averages
    static double leftAverageTemp; //Average of squared temps left
    static double rightAverageTemp; //Average of squared temps right
    //Keep track if that door is open
    static Boolean doorOpen = false; //Initiate it as NOT open

    /**
     * This is the main fxn in the program designed to implement java swing GUI elements and pass all responsibilities
     * @param args there are no command line arguments
     */
    public static void main(String[] args) {
        //frame for game place
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);

        //main panel for everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(1000, 750);

        //Game space
        GameBoard gamePanel = new GameBoard();

        // Create and add labels to main board
        JLabel tempLeft = new JLabel("Temp: ");
        tempLeft.setForeground(Color.black);
        tempLeft.setBounds(100, 150, 300, 25);
        mainPanel.add(tempLeft);

        JLabel tempRight = new JLabel("Temp: ");
        tempRight.setForeground(Color.black);
        tempRight.setBounds(600, 150, 300, 25);
        mainPanel.add(tempRight);

        JLabel hotLeft = new JLabel("# Hot: ");
        hotLeft.setForeground(Color.red);
        hotLeft.setBounds(100, 200, 300, 25);
        mainPanel.add(hotLeft);

        JLabel hotRight = new JLabel("# Hot: ");
        hotRight.setForeground(Color.red);
        hotRight.setBounds(600, 200, 300, 25);
        mainPanel.add(hotRight);

        JLabel coldLeft = new JLabel("# Cold: ");
        coldLeft.setForeground(Color.blue);
        coldLeft.setBounds(100, 250, 300, 25);
        mainPanel.add(coldLeft);

        JLabel coldRight = new JLabel("# Cold: ");
        coldRight.setForeground(Color.blue);
        coldRight.setBounds(600, 250, 300, 25);
        mainPanel.add(coldRight);

        //Add buttons for main panel usage
        JButton addParticles = new JButton("Add Particles");
        addParticles.setBounds(100, 25, 300, 100);
        addParticles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addParticlesToGame();
            }
        }); //END define what to do if pressed add
        mainPanel.add(addParticles); //Add add to panel

        JButton reset = new JButton("Reset");
        reset.setBounds(600, 25, 300, 100);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotOnes.clear();
                coldOnes.clear();
            }
        }); //END define what to do if pressed reset
        mainPanel.add(reset); //Add reset to panel

        //Set game board
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        gamePanel.setBounds(50, 300, 900, 400);
        gamePanel.addMouseListener(new MouseListener() { //Must implement all methods because of interface
            @Override
            public void mouseClicked(MouseEvent e) {/*nothing*/}
            @Override
            public void mousePressed(MouseEvent e) {
                doorOpen = true;
            }//If pressed open true
            @Override
            public void mouseReleased(MouseEvent e) {
                doorOpen = false;
            }//if pressed open false
            @Override
            public void mouseEntered(MouseEvent e) {/*nothing*/}
            @Override
            public void mouseExited(MouseEvent e) {/*nothing*/}
        }); //set door and close open event

        //Add gamePanel to larger panel
        mainPanel.add(gamePanel);

        //final settings to frame
        frame.setContentPane(mainPanel);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(1000, 750);
        frame.setTitle("Maxwell's Demon");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Now that everything is set pass to thread so action listeners can report
        Runnable myRun = new GamePlayRunnable(gamePanel, tempLeft, tempRight, hotLeft,
                hotRight, coldLeft, coldRight);
        Thread myThread = new Thread(myRun);
        //Passes client handling responsibilities to new thread
        myThread.start();

    }//END main

    /**
     * This is called by the thread in order to add particles to the game
     * Creates 2 on either side, hot and cold
     */
    public static void addParticlesToGame() {
        Particle one = new Particle("Left", "Red");
        hotOnes.addElement(one);
        Particle two = new Particle("Left", "Blue");
        coldOnes.addElement(two);
        Particle three = new Particle("Right", "Red");
        hotOnes.addElement(three);
        Particle four = new Particle("Right", "Blue");
        coldOnes.addElement(four);
    }//END addParticles

    /**
     * Overridden paintComponent so that we paint what we want
     * calls super to do proper initialization
     * @param g this guy is good at drawing things :-)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //reset counters
        counterColdLeft = 0;
        counterHotLeft = 0;
        counterColdRight = 0;
        counterHotRight = 0;
        leftAverageTemp = rightAverageTemp = 0;

        g.setColor(Color.black);
        //middle line
        g.fillRect(450, 0, 3, 400);
        //check status of door
        if (doorOpen) {
            g.setColor(getBackground());
            g.fillRect(450, 100, 3, 200);
        }//END if doorOpen

        //Now paint particles
        //Red first
        g.setColor(Color.red);
        for (Particle ball : hotOnes) {
            g.fillOval(ball.getX(), ball.getY(), 16, 16);
            if (ball.getSide() == "Left")
            {
                counterHotLeft++;
                leftAverageTemp += Math.pow(ball.getTemp(), 2);
            }//increment this counter
            else {
                counterHotRight++;
                rightAverageTemp += Math.pow(ball.getTemp(), 2);
            }
        }//END hot ones

        //now blue
        g.setColor(Color.blue);
        for (Particle ball : coldOnes) {
            g.fillOval(ball.getX(), ball.getY(), 16, 16);
            if (ball.getSide() == "Left")
            {
                counterColdLeft++;
                leftAverageTemp +=  Math.pow(ball.getTemp(), 2);
            }//increment this counter
            else {
                counterColdRight++;
                rightAverageTemp +=  Math.pow(ball.getTemp(), 2);
            }
        }//END cold ones

        //Compute temp as average
        leftAverageTemp = leftAverageTemp / (counterColdLeft + counterHotLeft);
        rightAverageTemp = rightAverageTemp / (counterHotRight + counterColdRight);

    }//END paintComponent

    /**
     * Runnable that keeps the game going
     * Updates every 15 milliseconds
     * This handles the game updates so that main can listen for user action
     */
    public static class GamePlayRunnable implements Runnable {
        GameBoard gamePanel;
        JLabel tempLeft;
        JLabel tempRight;
        JLabel hotLeft;
        JLabel hotRight;
        JLabel coldLeft;
        JLabel coldRight;

        /**
         * Runnable constructor that is passed all of these objects which are otherwise out of scope
         * @param gamePanel
         * @param tempLeft
         * @param tempRight
         * @param hotLeft
         * @param hotRight
         * @param coldLeft
         * @param coldRight
         */
        GamePlayRunnable(GameBoard gamePanel, JLabel tempLeft, JLabel tempRight, JLabel hotLeft, JLabel hotRight,
                         JLabel coldLeft, JLabel coldRight) {
            this.gamePanel = gamePanel;
            this.tempLeft = tempLeft;
            this.tempRight = tempRight;
            this.hotLeft = hotLeft;
            this.hotRight = hotRight;
            this.coldLeft = coldLeft;
            this.coldRight = coldRight;
        }

        /**
         * runnable passed to a thread in main
         */
        @Override
        public void run() {
            //first particles
            addParticlesToGame();

            boolean running = true;

            while (running) {
                //update all particles
                for (Particle ball : hotOnes) {
                    ball.update(doorOpen);
                }//END hotOnes

                for (Particle ball : coldOnes) {
                    ball.update(doorOpen);
                }//END coldOnes

                //repaint stats
                gamePanel.repaint();
                tempLeft.setText("Temp: " + Math.round(leftAverageTemp));
                tempRight.setText("Temp: " + Math.round(rightAverageTemp));
                hotLeft.setText("# Hot: " + counterHotLeft);
                hotRight.setText("# Hot: " + counterHotRight);
                coldLeft.setText("# Cold: " + counterColdLeft);
                coldRight.setText("# Cold: " + counterColdRight);

                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    running = false;
                } catch (Exception e) {
                    System.out.println("Unknown error in thread.");
                    running = false;
                }
            }//END while running
        }//END run()
    }//END GamePlayRunnable
}//END JPanel
