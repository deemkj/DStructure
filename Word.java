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
    LinkedList <Integer> docID;

    public Word(String word ) {
        this.word = word;
       docID=new LinkedList<Integer>();
    }

   public void addID(int id){
       
      if(!isIDExist(id)) 
       docID.insert(id);
       
       
   }

 public boolean isIDExist(Integer id){ // ensure id dose not exist in the list of IDs
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
  public void display() {
       if(docID.empty()){
          System.out.println("There is no document has that word");
          return;}
           
    
      System.out.println(word +" , "+docID.size()); // display the word and the number of document contain this word
      
      System.out.println("-------------------------------------------");
  }
  
  public LinkedList<Integer> getIDs(){
      return docID;
  }
}
