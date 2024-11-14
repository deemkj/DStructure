/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */


public class InvertedIndexBST {
    BST<Word> InvertedIndex;

    public InvertedIndexBST() {
        InvertedIndex=new BST<Word>();
        
    }
        
        public void add(String word, int id) {    // This method adds a word to the inverted index and associates it with a document ID.
                                                 // If the word is new to the inverted index, it creates a new entry with the given word and document ID.
                                                 // If the word already exists in the inverted index, it adds the document ID to the word's document list.
                                                
 
   
          if (!InvertedIndex.findkey(word)) {
       
              Word w = new Word(word);
              w.docID.insert(id);
              InvertedIndex.insert(word, w);
           } else {
        
               Word foundWord = InvertedIndex.retrieve();
               foundWord.addID(id);
             }
          
        }
          public void display(){
              InvertedIndex.displayInOrder();
          }
        }
        
        
        
       



    
    
