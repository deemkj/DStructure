/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class Word {
    String word;
   private LinkedList <Integer> docID;

    public Word(String word ) {
        this.word = word;
       docID=new LinkedList<Integer>();
    }

   public void addID(int id){
       
      if(!isIDExist(id)) 
       docID.insert(id);
       
       
   }

 public boolean isIDExist(Integer id){
     if(docID.empty())
         return false;
     docID.findFirst();
     while(!docID.last()){
    if(id==docID.retrieve())
        return true;
     docID.findNext();
     }
     if(id==docID.retrieve())
        return true;
     return false;
 }
  
    
}
