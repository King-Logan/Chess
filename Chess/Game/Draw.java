package Chess.Game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

//This class creates a visual represntation of the board
public class Draw extends JPanel{
    //TODO: optimize this
    public void paint(Graphics g) {
        g.fillRect(100, 100, 400, 400); //makes back
        for(int i = 100; i <= 400; i += 100){ //white spaces odd rows
            for(int j = 100; j <= 400; j += 100){
                g.clearRect(i, j, 50, 50);
            }
        }
        for(int i = 150; i <= 450; i += 100){ //white spaces even rows
            for(int j = 150; j <= 450; j += 100){
                g.clearRect(i, j, 50, 50);
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();            //create
        frame.setSize(600,600);                 //resize
        frame.getContentPane().add(new Draw()); //add board 
        frame.setLocationRelativeTo(null);
        frame.setBackground(Color.LIGHT_GRAY);  //set bg color
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}