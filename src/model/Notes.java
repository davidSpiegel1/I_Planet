// Here is a class for 'notes' which is simply a wrapper for text content that can be
// Manipulated
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Notes extends Block{
    
    
    private String description;
    private String note;
    
    public Notes (String key){
        
        super(key);
        
        this.description = "A Note! Can read and write on it";
    }
    public void setNoteFile(String path){
        File myFile = new File(path);
        
        try{
        Scanner reader = new Scanner(myFile);
       
        this.note = "";
        while (reader.hasNextLine()){
            this.note += reader.nextLine();
        }
            
            reader.close(); // Closing the file
            
        } catch (FileNotFoundException e){
            System.out.println("Error: "+e);
        
        }
        
    }
    public String getNote(){
        return this.note;
    }
    
    public void addNote(String subNote){
        this.note += subNote;
    }
    
    @Override
    public String getDescription(){
        return description;
    }
    
    
    
    
    
}
