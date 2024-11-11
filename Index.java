/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class Index {
    LinkedList <Document> Documents;

    public Index() {
   Documents = new  LinkedList <Document> ();
    }
    
    public void addDocument(Document d){
        Documents.insert(d);
        
    }
    
    public void display(){
       if(Documents.empty()) {
           System.out.println("There is no Document to display");
       return;
       }
       Documents.findFirst();
       while(!Documents.last()){
            Documents.retrieve().display();
            Documents.findNext();
       }
                   Documents.retrieve().display();

    }
    
    
    public static void main(String[] args) {
        
   
Index ind1=new Index () ;
LinkedList<String>words=new LinkedList<>();
words. insert ("national");
words. insert ("flag");
Document d1=new Document (1, words) ;
ind1.addDocument(d1);
LinkedList<String>words2=new LinkedList<>() ;
words2.insert ("green") ;
words2.insert ("color");

Document d2=new Document (2, words2) ; 
ind1.addDocument (d2) ;

ind1.display () ;

}
}