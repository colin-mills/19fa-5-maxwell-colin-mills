import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

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

    public static void main(String[] args) {
        //frame for game place
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);

        //main panel for everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(1000, 1000);

        //Game space
        GameBoard gamePanel = new GameBoard();
        //Set game board
        gamePanel.setBounds(50, 500, 900, 475);
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

        //Set door
        gamePanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        //Add gamePanel to larger panel
        mainPanel.add(gamePanel);

        // Create and add labels to main board
        JLabel tempLeft = new JLabel("Temp: ");
        tempLeft.setForeground(Color.black);
        tempLeft.setBounds(100, 262, 300, 50);
        mainPanel.add(tempLeft);

        JLabel tempRight = new JLabel("Temp: ");
        tempRight.setForeground(Color.black);
        tempRight.setBounds(600, 262, 300, 50);
        mainPanel.add(tempRight);

        JLabel hotLeft = new JLabel("# Hot: ");
        hotLeft.setForeground(Color.red);
        hotLeft.setBounds(100, 337, 300, 50);
        mainPanel.add(hotLeft);

        JLabel hotRight = new JLabel("# Hot: ");
        hotRight.setForeground(Color.red);
        hotRight.setBounds(600, 337, 300, 50);
        mainPanel.add(hotRight);

        JLabel coldLeft = new JLabel("# Cold: ");
        coldLeft.setForeground(Color.blue);
        coldLeft.setBounds(100, 412, 300, 50);
        mainPanel.add(coldLeft);

        JLabel coldRight = new JLabel("# Cold: ");
        coldRight.setForeground(Color.blue);
        coldRight.setBounds(600, 412, 300, 50);
        mainPanel.add(coldRight);

        //Add buttons for main panel usage
        JButton addParticles = new JButton("Add Particles");
        addParticles.setBounds(100, 50, 300, 200);
        addParticles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addParticlesToGame();
            }
        }); //END define what to do if pressed add

        mainPanel.add(addParticles); //Add add to panel

        JButton reset = new JButton("Reset");
        reset.setBounds(600, 50, 300, 200);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hotOnes.clear();
                coldOnes.clear();
            }
        }); //END define what to do if pressed reset

        mainPanel.add(reset); //Add reset to panel

        //final settings to frame
        frame.setContentPane(mainPanel);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(1000, 1000);
        frame.setTitle("Maxwell's Demon");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Now that everything is set pass to thread so action listeners can report
        Runnable myRun = new GamePlayRunnable();
        Thread myThread = new Thread(myRun);
        //Passes client handling responsibilities to new thread
        myThread.start();

    }//END main

    public static void addParticlesToGame() {
        Particle one = new Particle("Left", "Red");
        Particle two = new Particle("Left", "Blue");
        Particle three = new Particle("Right", "Red");
        Particle four = new Particle("Right", "Blue");

    }//END addParticles

    public static class GamePlayRunnable implements Runnable {


        /***/
        @Override
        public void run() {

        }//END run()

    }//END GamePlayRunnable
}//END JPanel
