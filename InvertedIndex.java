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
      if(isExist(word))
      Inverted_Index.retrieve().addID(id);
      else
      {
        Word w =new Word(word);
          w.addID(id);
          Inverted_Index.insert(w);
          
          
      }
          
      
  }
  
  
  public boolean isExist(String w){
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
    
}