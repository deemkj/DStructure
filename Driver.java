
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
public class Driver {
    LinkedList<String> StopWords;
    Index index;
    InvertedIndex Inverted_Index;

    public Driver() {
        StopWords=new LinkedList<String> ();
        index=new Index();
        Inverted_Index=new InvertedIndex();
        
        
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
    
    public static void main (String[] args){
        Driver d=new Driver();
         d.LoadingStopWords("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/stop.txt");
        d.Load("/Users/deemkj/Documents/Java 2/phase2/DStructureProjrct/src/main/java/dataset.csv");
        
     d.index.display();
    d.Inverted_Index.display();
     //d.StopWords.display();
    }
    }
        

