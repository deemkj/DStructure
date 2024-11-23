/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class Index { 
    LinkedList<Document> Documents;

    public Index() {
        Documents = new LinkedList<>();
    }

    public void addDocument(Document document) {
        Documents.insert(document);
    }

    public void display() {
        if (Documents.empty()) {
            System.out.println("There is no Document to display");
            return;
        }
        Documents.findFirst();
        while (!Documents.last()) {
            Documents.retrieve().display();
            Documents.findNext();
        }
        Documents.retrieve().display();
    }

    public Document getDocumentByID(Integer ID) {
        if (Documents.empty()) {
            return null;
        }
        Documents.findFirst();
        while (!Documents.last()) {
            if (Documents.retrieve().id == ID) {
                return Documents.retrieve();
            }
            Documents.findNext();
        }
        if (Documents.retrieve().id == ID) {
            return Documents.retrieve();
        }
        return null;
    }


    public int getDocumentCount() {
        int count = 0;
        if (Documents.empty()) return count;
        Documents.findFirst();
        while (!Documents.last()) {
            count++;
            Documents.findNext();
        }
        count++; 
        return count;
    }

    public LinkedList<Integer> SearchTermInDocuments(String word) { 
        LinkedList<Integer> docIDs = new LinkedList<>();
        if (Documents.empty()) {
            return docIDs;
        }

        Documents.findFirst();
        while (!Documents.last()) {
            
            if (Documents.retrieve().containsWord(word)) {  
                docIDs.insert(Documents.retrieve().id);
            }
            Documents.findNext();
        }

       
        if (Documents.retrieve().containsWord(word)) {
            docIDs.insert(Documents.retrieve().id);
        }

        return docIDs;
    }
}
 
