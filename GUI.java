import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;


import Game.Board;
import Game.Tile;


public class GUI extends JFrame{

    private static final long serialVersionUID = 1L;
    private JFrame frame;
    private Board board;
    public static ArrayList<Tile> grid;
    public static int width = 8;
    public static int height = 8;

    public GUI(Board b) {
        grid = new ArrayList<Tile>();
        for (Tile tile : b.getTiles()) {
            grid.add(tile);
        }
        
        
        frame = new JFrame("Chess");
        board = new Board();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setVisible(true);
        frame.add(grid);
            
        }

    }

    public static void main(String[] args) {
        Board b = new Board();
        new GUI(b);
    }

    
}