package model;


public class Gabriel extends MovableBlock{
    
        private Story st;
    
        public Gabriel(int pos, String key, int maxCol){
            
            super(pos,key,maxCol);
            
            Story sl = new Story("utilities/story/GabrielStory.txt");
            st = sl.getTree(); // Getting a tree
            
            
        }
    
        public String getComment(){
            return st.getValue();
        }
    
        public String getOptionOne(){
            return st.getResponse1();
        }
    
        public String getOptionTwo(){
            return st.getResponse2();
        }
    
        public void goLeft(){
            if (st.getLeft() != null){
            st = st.getLeft();
            }
        }
        public void goRight(){
            if (st.getRight() != null){
            st = st.getRight();
            }
        }
    
        @Override
        public String getDescription(){
            return "Gabriel. An angel";
        }
        
    
    
}
