// Will be a scanner class
// We take a txt file and build a list of nodes that are blocks
package model;

import java.util.*;
import java.io.*;


public class Scan{
    // Some needed instance variables
    private String content = "";
    private ArrayList<String> allowedChars;
    private ArrayList<Block> nodeList;// The needed node list
    
    // A constructor. I think we will use StringBuilder and FileInputStream to do the scanning
    public Scan(String fileName){
        // Our final node list
        nodeList = new ArrayList<Block>();
        
        
        this.allowedChars = new ArrayList<String>();
        this.allowedChars = this.populateAllowedChars(allowedChars);
        
        try{
            this.content = new String(this.getFile(fileName));
            //System.out.println("The content: "+this.content);
            //this.scan(this.content);
            
            }catch(IOException e){
                System.out.println("Error loading file: "+e); // Change to throw something
            }
    }
    public ArrayList<String> populateAllowedChars(ArrayList<String> al){
        al.add("A");
        al.add("C");
        al.add("c");
        al.add(".");
        al.add("T");
        al.add("t");
        al.add("K");
        al.add("W");
        al.add("X");
        al.add("K");
        al.add("G");
        al.add("g");
        al.add("|");
        al.add("_");
        al.add("p");
        al.add("s");
        al.add("O");
        al.add("E");
        
        return al;
        
    }
    
    
    public ArrayList<Block> scan(){
        String content = this.content;
        int curIndex = 0;
        int row = 1;
        int col = 1;
        Block finalBlock = null;
        while (curIndex <= content.length()-1){
            
            //System.out.println(content.charAt(curIndex));
            char initText = content.charAt(curIndex); // Getting the char at a specific point
            if (initText == '\n'){
                row++;
                col = 1;
                curIndex += 1;
                
            }
            // Allowing for comments in our game maps
            else if(initText == '/'){
                if (curIndex+1 < content.length()){
                    if (content.charAt(curIndex+1) == '/'){
                        while (curIndex < content.length() && content.charAt(curIndex) != '\n'){
                            curIndex ++;
                            col++;
                        }
                        
                    }
                }
                
            }
            // If it is an allowed text, we will determine what it is
            else if (this.allowedChars.contains(String.valueOf(initText))){
                
                if (initText == 'C'){
                    this.nodeList.add(new Character());
                    
                }
                else{
                    this.nodeList.add(new Block(String.valueOf(initText)));
                    
                }
                
                curIndex++;
            }
            else{
                curIndex++;
            }
        }
        
        // The final node list
        for (int i = 0; i<= this.nodeList.size()-1;i++){
            
            //System.out.println("The node: "+this.nodeList.get(i));
        }
        
        
        return this.nodeList;
    }
    
    public String getFile(String fileName) throws IOException{
        // Attempting to open the file
        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[10];
        
        StringBuilder sb = new StringBuilder();
        while (fis.read(buffer) != -1){
            
            sb.append(new String(buffer));
            buffer = new byte[10];
        }
        
        String content = sb.toString();
        return content;
        
    }
    
    
  
    
   
    
   
    
}

