// Will be the save mechanisim that consturcts and destroys user data as needed
// 

package model;

// Needed imports
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;


import model.Block;


// class deff
public class SaveEngine{
    
    private ArrayList<Block> curLevelList;
    private String dir = "/utilities/userData";
    private int colNum = 22; // Change this when you can (should get from controller)
    
    
    public SaveEngine(){
        
        
        
    }
    
    
    public boolean isDir(){
        File dir2 = new File("."+dir);
        return dir2.exists();
        
    }
    
    public void saveData(String fn1, ArrayList<Block> level){
        System.out.println("---> The file name: "+fn1);
        String fileName = fn1.split("/")[2]; // Might want a try-catch here
        try{
            System.out.println("TRYING TO CHANGE LEVEL!!");
            File dir2 = new File("."+dir);// Will change
            if (!dir2.exists()){
                dir2.mkdirs();
            }
            
        }catch(Exception e){
            System.out.println("Error In constructing dir: "+e);
        }
        
        try{
            
            String fn = "."+dir+"/"+fileName;
            File file = new File(fn);
            //file.close();
            if (!file.createNewFile()){
                System.out.println("Error: File not constructed.");
            }
                // Attempting to write the file
                    writeFile(fn,level);
        }
        catch(IOException en){
            System.out.println("Error In constructing file: "+en);
        }
            
        
    }
    
    public void writeFile(String fileName, ArrayList<Block>level){
        
        String levelStr = "";
        for (int i = 0; i<= level.size()-1;i++){
            if (i%colNum == 0 && i!=0){
                levelStr += "\n";
            }
            if (level.get(i).getKey().equals("C")){
                levelStr += "f";
            }else{
            levelStr += level.get(i).getKey();
            }
        }
        try{
        
        FileWriter myWriter = new FileWriter(fileName);
        myWriter.write(levelStr);
        myWriter.close();
            
        }catch (IOException e){
            System.out.println("Error writing file!");
        }
    }
    
    
    public void clearDirectory(){
        File dir2 = new File("."+dir);
        System.out.println("The file list: "+dir2.listFiles());
        for(File file: dir2.listFiles()){
            if (!file.isDirectory()){
                file.delete();
            }
        }
    }
    
    
    
    
    
}
