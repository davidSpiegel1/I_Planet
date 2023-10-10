package model;
import java.util.*;
import java.io.*;


/**
 * 
 * @author davidspiegel
 * 
 * Description: This class will be used to tell the story of the game. We will have an abstract
 *           repersation of a story. This will be a decision tree.
 *
 */
// TEXT FILE ALWAYS NEEDS A NEW LINE AT BOTTOM!! DO NOT FORGET!!
public class Story{

	// A constructor to set the level
    private Story left,right;
    private String value = "";
    
    private String content;
    private String response1 = "";
    private String response2 = "";
    private Story tree;

	public Story(String textFile) {
        
        this.left = null;
        this.right = null;
        
        // Processing the text file
        try {
            this.content = new String(this.processFile(textFile));
            
        }catch (IOException e){
            System.out.println("Error loading file: "+e);
        }
        this.tree = this.buildDecisionTree(this.content);
	}
    
    public Story getTree(){
        return this.tree;
    }
    public Story buildDecisionTree(String content){
        System.out.println("Building decisiontree");
        String line = "";
        //Story head = new Story();// The head
        ArrayList<String>lineList = new ArrayList<String>();
        //Story cur = new Story();
        int amount = 5;
        int counter = 0;
        Story cur = null;
        
        for (int i = 0; i<= content.length()-1;i++){
            
                if (content.charAt(i) == '\n'){
                    System.out.println("The line: "+line);
                    lineList.add(line);
                    line = "";
                }
                line += content.substring(i,i+1);
        }
        System.out.println("The line list: "+lineList);
        if (lineList.size() > 0){

            // Index: 0 - 3 - 9
            // Loop amount: 1 - 2 - 4
           // head = buildDecisionTreeHelper(lineList,head,9,4);
            
            int startVal = 1;
            int i = 0;
            int j = 3;
            Story head = new Story();// The head
          
            ArrayList<Story> storyLevel = new ArrayList<Story>();
            int level = 1;
            
            while (i+j <= lineList.size()-1){
                
                
                System.out.println("The start val: "+startVal);
                System.out.println("The I val: "+i);
                
                storyLevel = buildDecisionTreeHelper(lineList,cur,i,startVal);
                System.out.println("The story level: "+storyLevel);
                
                // Compute how far down we need to go...
                cur = setLevel(level,cur,storyLevel);
                startVal = startVal*2;
                i = (i*2)+3;
                //j= j+3;
                level++;
                
                
            }

        }
        return cur;
  
    }
    
    public Story setLevel(int levelNum,Story cur, ArrayList<Story> level){
            
            if (cur == null){
                if (level.size()==1){
                cur = level.get(0);
                }else if (level.size() != 0){
                    cur.setLeft(level.get(0));
                    cur.setRight(level.get(1));
                }
                return cur;
            }else{
                // Splitting the list
                ArrayList<Story>a1 = new ArrayList<Story>();
                for (int i = 0; i<=(level.size()/2)-1; i++){
                    a1.add(level.get(i));
                }
                ArrayList<Story>a2 = new ArrayList<Story>();
                for (int i = level.size()/2;i<= level.size()-1;i++){
                    a2.add(level.get(i));
                }
                
                cur.setLeft(setLevel(levelNum,cur.getLeft(),a1));
                cur.setRight(setLevel(levelNum,cur.getRight(),a2));
             return cur;
            }
        
    }
    
    public ArrayList<Story> buildDecisionTreeHelper(ArrayList<String> stringList, Story st, int index,int aLoop){
       
            ArrayList<Story> storyLevel = new ArrayList<Story>();// Used ot gather level
        
            int amountLoop = index+3;
        
            int acturalLoop = aLoop;
            System.out.println("The actural loop: "+acturalLoop);
            
        
            int countVar = index;
            int in = countVar+(acturalLoop);
        
            for (int i = 0; i<= acturalLoop-1; i++){
                //System.out.println("What in is: "+in);
                Story st1 = new Story();
                
                String val = stringList.get(countVar);
                
                st1.setValue(val); // The first value for the level
                
                
                //in++;
                String child1 = stringList.get(in);
                
                st1.setResponse1(child1);
                //st1.setValue(val);
                in++;
                
                Story st3 = new Story();
                
                String child2 = stringList.get(in);
                in++;
                
                st1.setResponse2(child2);
                //st1.setValue(val);
                
                System.out.println("The value: "+val);
                System.out.println("The children: "+child1+", "+child2);
                countVar++;
                
                //st1.setLeft(st2);
                //st1.setRight(st3);
                storyLevel.add(st1);
                
            }

            
            return storyLevel;

            
        
    }
    
    public void printTree(Story val, String buff){
        if (val != null){
            System.out.println(buff+val.getResponse1());
            System.out.println(buff+val.getValue());
           
            printTree(val.getLeft(),buff+">");
            printTree(val.getRight(),buff+">");
        }
     
        
    }
    
    
    
    public Story(){
        
        this.left = null;
        this.right = null;
        
    }
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public String getResponse1(){
        return this.response1;
    }
    public String getResponse2(){
        return this.response2;
    }
    public void setResponse1(String response1){
        this.response1 = response1;
    }
    public void setResponse2(String response2){
        this.response2 = response2;
    }
    public void setLeft(Story left){
        this.left = left;
    }
    public Story getLeft(){
        return this.left;
    }
    
    public void setRight(Story right){
        this.right = right;
    }
    
    public Story getRight(){
        return this.right;
    }
    
    public String processFile(String fileName) throws IOException{
        FileInputStream fis = new FileInputStream(fileName);
        byte[] buffer = new byte[10];
        
        StringBuilder sb = new StringBuilder();
        while (fis.read(buffer) != -1){
            sb.append(new String(buffer));
            buffer = new byte[10];
      
            
        }
        String con = sb.toString();
        return con;
    }
    
    @Override
    public String toString(){
        return "(The value: "+this.value+",r1 "+this.response1+",r2"+this.response2+")";
    }
    
    
   
        
    
    
   /*public static void main(String args[]){
        System.out.println("The story class");
        // Will change this soon
        Story st = new Story("/Users/davidspiegel/git/I_Planet/src/utilities/testStory2.txt");
        Story newT = st.getTree();
        Story curVal = newT;
        Scanner myScanner = new Scanner(System.in);
        while (curVal != null){
            System.out.println("Joan: \n\n"+curVal.getValue());
            System.out.println("\n\nPick a response: \n (a)"+curVal.getResponse1()+"\n (b)"+curVal.getResponse2());
            String userInput = myScanner.nextLine();
            if (userInput.equals("a")){
                curVal = curVal.getLeft();
            }
            else if (userInput.equals("b")){
                curVal = curVal.getRight();
            }
            
        }
        
    }*/
    
}
