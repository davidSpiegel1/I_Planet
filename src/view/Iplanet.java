// A new I Planet gui
//

package view;
import java.util.ArrayList;
import java.util.*;
import java.awt.event.*;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.RoundRectangle2D;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.*;
import java.util.Scanner;
import controller.Controller2; // New controller
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;
import model.Story;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
// New imports
import model.Parse;
import model.Scan;
import java.awt.event.KeyAdapter;


public class Iplanet extends JFrame {
    
    private JFrame frame;
    private JPanel panel;
    private JPanel infoDec;
    private ArrayList<JLabel> labelList;
    private JLabel curDescription;
    private Controller2 c; // Controller needed

    


    // Constructor
    public Iplanet(){
        c = new Controller2(); // Initializing the controller
      
        
        prepareGui();
        
    }
    
    public void prepareGui(){
        frame = new JFrame("i planet");
        panel = new JPanel();
        infoDec = new JPanel(); // Our info dec
        GridLayout layout = new GridLayout(1,2);
        layout.setHgap(0);
        layout.setVgap(0);
        infoDec.setLayout(layout);
        
        frame.setSize(350,390);
        panel.setLayout(new GridLayout(17,23));
        frame.setBackground(Color.black);
        
        ArrayList<Block> list = c.constructMap();
        labelList = c.parse(list);
        int size = c.getAmountCol();
        for (int i = 0; i<= labelList.size()-1;i++){
            panel.add(labelList.get(i));
        }
   
        frame.addKeyListener(new KeyListener(){
            @Override
            public void keyReleased(KeyEvent event ){
                
                    if (event.getKeyChar() == KeyEvent.VK_UP){
                        System.out.println("Hi1");
                        
                        
                    }
                }
            @Override
                        public void keyTyped(KeyEvent event) {
                            System.out.println("Hey3");
                        }
            @Override
                        public void keyPressed(KeyEvent event) {
                            System.out.println("Hi2");
                            if (event.getKeyCode() == KeyEvent.VK_UP){
                                System.out.println("Up works...");
                                c.moveChar("w");

                            }
                            if (event.getKeyCode() == KeyEvent.VK_DOWN){
                                System.out.println("Down works...");
                                c.moveChar("s");
                                
                            }
                            if (event.getKeyCode() == KeyEvent.VK_LEFT){
                                System.out.println("Left works...");
                                c.moveChar("a");
                            }
                            if (event.getKeyCode() == KeyEvent.VK_RIGHT){
                                System.out.println("Right works...");
                                c.moveChar("d");
                            }
                            if (event.getKeyCode() == KeyEvent.VK_SPACE){
                                
                                System.out.println("Space works!");
                                System.out.println("The description: "+c.getDescription());
                                curDescription.setText(c.getDescription());
                            }
                            
                                ArrayList<Block> list = c.constructMap();
                                labelList.clear();
                                labelList = c.parse(list);
                           
                                panel.removeAll();
                                panel.revalidate();
                        
                                for (int i = 0; i< labelList.size()-1;i++){
                                    panel.add(labelList.get(i),i);
                                }
                            
                                panel.revalidate();
                                panel.repaint();
                        }
            });
        try{
        infoDec = buildInfoDec(c,infoDec);
            }catch(Exception e){
                System.err.println("Error");
            }
        
        
       
        frame.addWindowListener(new WindowAdapter() {
                 public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                 }
              });
        
        
        
        frame.add(infoDec,BorderLayout.NORTH);
        frame.add(panel,BorderLayout.CENTER); // Adding the game panel
        //frame.add(infoDec); // Adding the infoDec
     
        frame.setVisible(true);
    }
    
    
   
    public JPanel buildInfoDec(Controller2 c, JPanel p)  throws UnsupportedLookAndFeelException, ClassNotFoundException{
        
        //JButton testb = new JButton("Hey");
        curDescription = new JLabel("*description*");
        
        JLabel menuButton = new JLabel("*:*");
    
        
        //menuButton.setBorder(brdr);
        menuButton.setBackground(Color.BLUE);
        menuButton.setOpaque(true);
        curDescription.setBackground(Color.GREEN);
        curDescription.setOpaque(true);
     
        
        p.add(menuButton);
        p.add(curDescription);
        
        
        return p;
        
    }
        
    

    public static void main(String [] args){
        
        
        Iplanet i = new Iplanet();
        
        
    }


}

