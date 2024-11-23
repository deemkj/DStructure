
import java.io.File;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class DocumentManager{
  static  LinkedList<String> StopWords;
static int countStopWords;    
static int countWords;
  static  LinkedList<String> AllUniqueWords;
    Index index;
    InvertedIndex Inverted_Index;
    InvertedIndexBST Inverted_Index_BST;

    public DocumentManager() {
        StopWords=new LinkedList<String> ();
        index=new Index();
        Inverted_Index=new InvertedIndex();
        Inverted_Index_BST=new InvertedIndexBST();
        AllUniqueWords=new  LinkedList<String>();
        
    }
    
    
  public int calculateVocabularySize(String fileName) {
    LinkedList<String> uniqueWords = new LinkedList<>();
    
    try {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        
      
        scanner.nextLine(); 

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine(); 
            
          
                   String DocLine=line;                   



 DocLine = DocLine.toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", ""); 
  DocLine = DocLine.replaceAll("\\s+", " "); 

        
            String[] AllWords = DocLine.split(" "); 

            for (String Word : AllWords) {
   
                Word = Word.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", "");
                
                if (!Word.isEmpty()  && !isWordInList(uniqueWords, Word)) {
                    uniqueWords.insert(Word);
                }
            }
        }
        
        scanner.close();
    } catch (Exception e) {
        System.out.println("Error reading the file: " + e.getMessage());
    }

    return uniqueWords.size();  
}


private boolean isWordInList(LinkedList<String> list, String word) {
    if (list.empty()) return false;
    list.findFirst();
    while (!list.last()) {
        if (list.retrieve().equalsIgnoreCase(word)) return true;
        list.findNext();
    }
    return list.retrieve().equalsIgnoreCase(word); 
}






    public void countTokensInFile(String fileName) {
            LinkedList<String> allWords = new LinkedList<>();


    try {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            
            String line = scanner.nextLine(); 
            
               
                String DocLine = line.substring(line.indexOf(',') + 1).trim();

                              DocLine=DocLine.toLowerCase().replaceAll("\'", "");
                                                   
                                DocLine=DocLine.toLowerCase().replaceAll("-", " ");


                String[] tokens = DocLine.split(" "); 
                countWords+=tokens.length;
                
               
                
            
        }
        
        scanner.close();
    } catch (Exception e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

  
}


    public void LoadingStopWords(String fN){
        
        
         try {
            File f = new File(fN);
            Scanner s = new Scanner(f);
         
         
            while (s.hasNextLine()) {
                String word = s.nextLine();
                StopWords.insert(word);
                countStopWords++;
               

            }
        } catch (Exception e) {
            
        }
    }

    public  void Load(String fN) {
        String line = null;
        try {
            File f = new File(fN);
            Scanner s = new Scanner(f);
         
            s.nextLine();
            while (s.hasNextLine()) {
                line = s.nextLine();
                
                if (line.trim().length() < 3) {
                    break; 
                }

            
               
                String x = line.substring(0, line.indexOf(','));
                int id = Integer.parseInt(x.trim());
                String DocLine = line.substring(line.indexOf(',') + 1).trim();
                
                LinkedList<String> DocWords=cleanAndExtractWords(DocLine ,id); //this method return a list of words that are in the document
                Document d1=new Document(id,DocWords);
                
                index.addDocument(d1);
                
            }
        } catch (Exception e) {
            System.out.println("end of file");
        }
    }

    public LinkedList<String> cleanAndExtractWords (String DocLine, int id){ // add words in Inverted_Index_BST and Inverted_Index
                                                                             // after ensuring that the word is not exists in these lists
        LinkedList<String> words =new LinkedList<String>();
   
        DocLine = DocLine.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");

        String [] ExtractedWords =DocLine.split("\\s+");
        
        for(int i =0 ; i<ExtractedWords.length ; i++){
            if(!isExistINStopWords(ExtractedWords[i])){
                words.insert(ExtractedWords[i]);
                Inverted_Index.add(ExtractedWords[i],id);
                Inverted_Index_BST.add(ExtractedWords[i], id);
               
            }
        
                }
        
        return words;
    }
    
    
  
    public boolean isExistINStopWords(String w){
        if(StopWords.empty())
        return false;
        w=w.replaceAll("-", " ");
        StopWords.findFirst();
        while(!StopWords.last()){
            if(StopWords.retrieve().equals(w))
                return true;
            StopWords.findNext();
        }
         if(StopWords.retrieve().equals(w))
                return true;
         {
                return false;
         }
      
    }
   
        
  
    public static void main (String[] args){
       
    
        DocumentManager documentManager = new DocumentManager();
        documentManager.LoadingStopWords("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/stop.txt");
        documentManager.Load("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
        

        QueryProcessingBST queryProcessorBST = new QueryProcessingBST(documentManager.Inverted_Index_BST);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========== Test Menu ==========");
            
           System.out.println("1. Boolean Retrieval");
            System.out.println("2. Ranked Retrieval");
            System.out.println("3. Indexed Document");
            System.out.println("4. Indexed Token");
            System.out.println("5. Retrieve a term");

            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter a Boolean query: ");
                    String booleanQuery = scanner.nextLine();
                    LinkedList<Integer> booleanResults = queryProcessorBST.BooleanQuery(booleanQuery);
                   System.out.println("========== Query: "+booleanQuery+"==========");
                    if (booleanResults.empty()) {
                        System.out.println("No documents found.");
                    } else {
                        booleanResults.findFirst();
                        while (!booleanResults.last()) {
                            System.out.println("Doc ID: " + booleanResults.retrieve());
                            booleanResults.findNext();
                        }
                        System.out.println("Doc ID: " + booleanResults.retrieve()); 
                    }
                    break;

                case 2:
                    System.out.print("Enter a ranked query: ");
                    String rankedQuery = scanner.nextLine();
                     System.out.println("========== Ranked Query: "+rankedQuery+"==========");

                    Ranking2 ranking = new Ranking2(documentManager.index, documentManager.Inverted_Index_BST, rankedQuery);
                    ranking.generateRankedList();
                    ranking.displayRankedDocuments();
                    break;

                case 3:
                    
                    System.out.println("========== Indexed Tokens ==========: " );
                            System.out.println("Document ID   ,      Word Count");

                    documentManager.index.display();
                    break;

                case 4:
             documentManager.countTokensInFile("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
           int vocabularySize = documentManager.calculateVocabularySize("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
      
               System.out.println("Total number of tokens in the index: " + ((countWords)));
               System.out.println("Vocabulary size: " + (vocabularySize));
               
               System.out.println("========== Document Word Count ==========: " );
                             System.out.println("WORD   , DOCUMENT COUNT");

                            documentManager.Inverted_Index.display();
               break;
                case 5 :
                     System.out.println("Retrieve a term:");
                    System.out.println("1. Using index with lists");
                    System.out.println("2. Using inverted index with lists");
                    System.out.println("3. Using inverted index with BST");
                    System.out.print("Enter your choice: ");
                    int retrieveChoice = scanner.nextInt();
                    scanner.nextLine();
                    
                    {
                      switch(retrieveChoice)  {
                          
                          case 1:
                               System.out.println("Enter term to search about");
                                String searchTerm = scanner.nextLine();
                                LinkedList<Integer> docIDs=documentManager.index.SearchTermInDocuments(searchTerm);
                                if(docIDs.empty())
                                    System.out.println("There is no document that contains this word");
                                else{
                                    System.out.println("Documents containing the term " + searchTerm + ":");
                                    docIDs.display();}
                                break;
                                
                          case 2:
                               System.out.println("Enter term to search about");
                                String searchTerm2 = scanner.nextLine();
                                LinkedList<Integer> docIDsInverted=documentManager.Inverted_Index.searchWordInDocumentInverted(searchTerm2);
                                if(docIDsInverted.empty())
                                    System.out.println("There is no document that contains this word");
                                else{
                                    System.out.println("Documents containing the term " + searchTerm2 + ":");
                                    docIDsInverted.display();}
                                break;
                                
                          case 3:
                              
                              System.out.println("Enter term to search about");
                                String searchTerm3 = scanner.nextLine();
                                LinkedList<Integer> docIDsInvertedBST=documentManager.Inverted_Index_BST.searchWordInDocumentInvertedBST(searchTerm3);
                                if(docIDsInvertedBST.empty())
                                    System.out.println("There is no document that contains this word");
                                else{
                                    System.out.println("Documents containing the term " + searchTerm3 + ":");
                                    docIDsInvertedBST.display();}
                                break;
                              
                
                          
                          
                      }
                      
                      
                        
                        
                        
                    }
                    break;


                case 6:
                    System.out.println("Exiting..");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, Please try again");
            }
            System.out.println();
        }
    }
        

    }
    
        

