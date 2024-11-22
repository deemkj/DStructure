/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */


public class Document {
     int id;
     LinkedList<String> Words ;

    // Constructor
   

    public Document(int id, LinkedList<String> Words) {
        this.id = id;
        this.Words = Words;
    }

    
    // Method to print the document details
    public void display() {
        System.out.println("Document ID: " + id);
        System.out.println("Words: ");
        Words.findFirst();
        while(!Words.last()){
         System.out.print(Words.retrieve()+", ");
    
            Words.findNext();
            
        }
        if(!Words.empty())
          System.out.println(Words.retrieve());
        
        
    }

    public int getId() {
        return id;
    }
    
    public boolean containsWord(String w){
        if(Words.empty())
        return false;
        Words.findFirst();
        while(!Words.last()){
            if(Words.retrieve().equalsIgnoreCase(w))
                return true;
            Words.findNext();
        }
            if(Words.retrieve().equalsIgnoreCase(w))
                return true;
        return false;
        
    }
}
