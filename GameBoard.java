import javax.swing.*;

public class GameBoard extends JPanel {

    //GameBoard dimensions
    final static int panelHeight = 600; //GamePanel Height
    final static int panelWidth = 800; //GamePanel Width
    final static int panelX = 200; //GamePanel X start
    final static int panelY = 0; //GamePanel Y start
    //keep track of # of particles and where
    static int counterColdLeft; //# blue guys on left
    static int counterHotLeft; //# red guys on left
    static int counterColdRight; //# blue guys on right
    static int counterHotRight; //# red guys on right
    //keep track of averages
    static double leftAverageTemp; //Average of squared temps left
    static double rightAverageTemp; //Average of squared temps right

    public static void main(String[] args) {
        //frame for game place
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(null);
        //main panel for everything
        JPanel mainPanel = new JPanel();
        //Game space
        GameBoard gamePanel = new GameBoard();

        mainPanel.setLayout(null);
        mainPanel.setSize(1000, 1000);





    }//END main

    public static class GamePlayRunnable implements Runnable {


        /***/
        @Override
        public void run() {

        }//END run()

    }//END GamePlayRunnable
}//END JPanel
