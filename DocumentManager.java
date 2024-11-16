
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
public class DocumentManager {
    LinkedList<String> StopWords;
    Index index;
    InvertedIndex Inverted_Index;
    InvertedIndexBST Inverted_Index_BST;

    public DocumentManager() {
        StopWords=new LinkedList<String> ();
        index=new Index();
        Inverted_Index=new InvertedIndex();
        Inverted_Index_BST=new InvertedIndexBST();
        
    }
    
    
  public int calculateVocabularySize(String fileName) {
    LinkedList<String> allWords = new LinkedList<>();
    try {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) continue; 

            String[] words = line.toLowerCase().split("\\s+");
            for (String word : words) {
                word = word.replaceAll("^[^a-zA-Z]+|[^a-zA-Z]+$", "");
                if (!word.isEmpty() && !isWordInList(allWords, word)) {
                    allWords.insert(word);
                }
            }
        }
        scanner.close();
    } catch (Exception e) {
        System.out.println("Error reading the file: " + e.getMessage());
    }

    return allWords.size();
}

private boolean isWordInList(LinkedList<String> list, String word) {
    if (list.empty()) return false;
    list.findFirst();
    while (!list.last()) {
        if (list.retrieve().equals(word)) return true;
        list.findNext();
    }
    return list.retrieve().equals(word); // تحقق من العنصر الأخير
}
    public int countTokensInFile(String fileName) {
    int tokenCount = 0;

    try {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

     
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine(); 

            if (!line.isEmpty()) {
                String[] tokens = line.split("\\s+");
                tokenCount += tokens.length; 
            }
        }
        scanner.close();
    } catch (Exception e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

    return tokenCount;
}


    public void LoadingStopWords(String fN){
        
        
         try {
            File f = new File(fN);
            Scanner s = new Scanner(f);
         
         
            while (s.hasNextLine()) {
                String word = s.nextLine();
                StopWords.insert(word);
               

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

    public LinkedList<String> cleanAndExtractWords (String DocLine, int id){
        LinkedList<String> words =new LinkedList<String>();
   
        DocLine = DocLine.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");

        String [] ExtractedWords =DocLine.split("\\s+");
        
        for(int i =0 ; i<ExtractedWords.length ; i++){
            if(!isExistINStopWords(ExtractedWords[i])){
                words.insert(ExtractedWords[i]);
                Inverted_Index.add(ExtractedWords[i],id);
                Inverted_Index_BST.add(ExtractedWords[i], id);
            }}
        
        return words;
    }
    public boolean isExistINStopWords(String w){
        if(StopWords.empty())
        return false;
        StopWords.findFirst();
        while(!StopWords.last()){
            if(StopWords.retrieve().equals(w))
                return true;
            StopWords.findNext();
        }
         if(StopWords.retrieve().equals(w))
                return true;
         return false;
    }
    public void displayThegivenIDs(LinkedList<Integer> DocIDs){
        DocIDs.findFirst();
        while(!DocIDs.last()){
            
            Document d=index.getDocumentByID(DocIDs.retrieve());
            if(d!=null)
               d.display();
        
         DocIDs.findNext();
        }
         Document d=index.getDocumentByID(DocIDs.retrieve());
            if(d!=null)
               d.display();
        }
        
  
    public static void main (String[] args){
       
    
        DocumentManager documentManager = new DocumentManager();
        documentManager.LoadingStopWords("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/stop.txt");
        documentManager.Load("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
        

        QueryProcessing queryProcessor = new QueryProcessing(documentManager.Inverted_Index);
        QueryProcessingBST queryProcessorBST = new QueryProcessingBST(documentManager.Inverted_Index_BST);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("========== Test Menu ==========");
            System.out.println("1. Boolean Retrieval");
            System.out.println("2. Ranked Retrieval");
            System.out.println("3. Indexed Documents");
            System.out.println("4. Indexed Tokens");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // استهلاك السطر الجديد

            switch (choice) {
                case 1:
                    System.out.print("Enter a Boolean query: ");
                    String booleanQuery = scanner.nextLine();
                    LinkedList<Integer> booleanResults = queryProcessor.BooleanQuery(booleanQuery);
                    System.out.println("Unranked Documents:");
                    if (booleanResults.empty()) {
                        System.out.println("No documents found.");
                    } else {
                        booleanResults.findFirst();
                        while (!booleanResults.last()) {
                            System.out.println("Doc ID: " + booleanResults.retrieve());
                            booleanResults.findNext();
                        }
                        System.out.println("Doc ID: " + booleanResults.retrieve()); // طباعة العنصر الأخير
                    }
                    break;

                case 2:
                    System.out.print("Enter a ranked query: ");
                    String rankedQuery = scanner.nextLine();
                    Ranking2 ranking = new Ranking2(documentManager.index, documentManager.Inverted_Index_BST, rankedQuery);
                    ranking.generateRankedList();
                    ranking.displayRankedDocuments();
                    break;

                case 3:
                    System.out.println("Number of documents in the index: " + documentManager.index.getDocumentCount());
                    break;

                case 4:
                int totalTokens = documentManager.countTokensInFile("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
                int vocabularySize = documentManager.calculateVocabularySize("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
               System.out.println("Total number of tokens in the index: " + totalTokens);
               System.out.println("Vocabulary size: " + vocabularySize);
               break;


                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
        

    }
    
        

