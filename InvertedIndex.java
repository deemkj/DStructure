/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class InvertedIndex {
  LinkedList<Word> Inverted_Index;  
 

    public InvertedIndex() {
        Inverted_Index=new LinkedList <Word> ();
  

    }
  
  public void add(String word, int id){
      if(isWordExist(word))
      Inverted_Index.retrieve().addID(id);
      else
      {
        Word w =new Word(word);
          w.addID(id);
          Inverted_Index.insert(w);
          

      }
          
      
  }
  
  
  public boolean isWordExist(String w){ //Boolean
      if(Inverted_Index.empty())
      return false;
      
      Inverted_Index.findFirst();
      while(!Inverted_Index.last()){
          if(Inverted_Index.retrieve().word.equals(w))
              return true;
      Inverted_Index.findNext();
      }
      if(Inverted_Index.retrieve().word.equals(w))
              return true;
      return false;
  }
  
  public void display(){
      
     if(Inverted_Index.empty() ||Inverted_Index==null ) {
         System.out.println("Inverted_Index is empty");
      return;
     }
     Inverted_Index.findFirst();
     while(!Inverted_Index.last()){
         Inverted_Index.retrieve().display(); // return a word OBJECT 
          Inverted_Index.findNext();
     }
      Inverted_Index.retrieve().display();
  }
  
  
  public LinkedList<Integer> searchWordInDocumentInverted(String word){ //returns a list of IDs , ( by searching the Inverted_Index.If the  word in the Word object within the Inverted_Index matches the receivd word, it returns it's list of IDs 
      
      LinkedList<Integer> docIDs=new LinkedList<Integer>();    
      
      if(Inverted_Index.empty())
      return docIDs;
      
      Inverted_Index.findFirst();
      while(!Inverted_Index.last()){
          if(Inverted_Index.retrieve().word.equals(word))
             return Inverted_Index.retrieve().docID;
      Inverted_Index.findNext();
      }
      if(Inverted_Index.retrieve().word.equals(word))
 return Inverted_Index.retrieve().docID;      
      
      
      return docIDs;
  }
    
}