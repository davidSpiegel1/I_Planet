// Trying to reconstruct the controller
package view;
import java.util.*;
import java.util.Scanner;
import controller.Controller2;
import model.Block;
import model.Character;
import model.Enemy;
import model.Environment;
import model.Person;
import model.Story;
// New imports
import model.Parse;
import model.Scan;

public class IplanetConsole2{
    
    
    public static void displayMap(ArrayList<Block> list, int amountRow){
        for (int i = 0;i<= list.size()-1;i++){
            for (int j = 0; j<= amountRow;j++){
                System.out.print(list.get(i).getKey());
                if (i+1 <= list.size()-1){
                    i++;
                }
            }
            System.out.println();
            
        }
        
    }
    
    public static void main(String args[]){
        // Geting everything from console
        Controller2 c2 = new Controller2();
        ArrayList<Block> list = c2.constructMap();
        int amountRow = c2.getAmountCol();

        System.out.println("*** I Planet Terminal Version ***");
        displayMap(list, amountRow);
        
        Scanner sc1 = new Scanner(System.in);
        while (sc1.hasNextLine()){
        System.out.println("Please enter a value: ");
        String userVal = sc1.nextLine();
        c2.moveChar(userVal);
        displayMap(list,amountRow);
        }
        

        
        
        
    }
    
    
    
}
