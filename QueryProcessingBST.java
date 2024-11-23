/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class QueryProcessingBST {
     static InvertedIndexBST inverted;

    public QueryProcessingBST(InvertedIndexBST inverted) {
        this.inverted = inverted;
    }

    public static LinkedList<Integer> andQ(String query) { // just add IDs in the lists then send it to andQ(override method )
                                                           // to return the intersection 
        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();
        String[] terms = query.split("AND");

        if (terms.length == 0) return L1;

   
        boolean found = inverted.isWordExist(terms[0].trim().toLowerCase());
        if (found) {
            L1 = inverted.InvertedIndex.retrieve().docID; // retrieve the list of IDs in word object
        }

        for (int i = 1; i < terms.length; i++) {
            found = inverted.isWordExist(terms[i].trim().toLowerCase()); 
            if (found) {
                L2 = inverted.InvertedIndex.retrieve().docID;
                L1 = andQ(L1, L2);
            }
        }

        return L1;
    }

    public static LinkedList<Integer> andQ(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> result = new LinkedList<>();
        if (a.empty() || b.empty())
            return result;

        a.findFirst(); //Nested loop
        while (true) {   /// for each id in A list compare it with all IDs in B list if the id exists in both list it will add it in the result list
            boolean found = isExist(result, a.retrieve());
            if (!found) {
                b.findFirst();
                while (true) {
                    if (b.retrieve().equals(a.retrieve())) {
                        result.insert(a.retrieve());
                        break;
                    }
                    if (!b.last())
                        b.findNext();
                    else
                        break;
                }
            }
            if (!a.last())
                a.findNext();
            else
                break;
        }
        return result;
    }

    public static boolean isExist(LinkedList<Integer> a, Integer id) {
        if (a.empty())
            return false;
        a.findFirst();
        while (!a.last()) {
            if (a.retrieve().equals(id)) 
                return true;
            a.findNext();
        }
        return a.retrieve() != null && a.retrieve().equals(id);
    }
    // OR queries
    
    
    public static LinkedList<Integer> ORQ(String query) {
        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();
        String[] terms = query.split("OR");

        if (terms.length == 0) return L1;

      
        boolean found = inverted.isWordExist(terms[0].trim().toLowerCase());
        if (found) {
            L1 = inverted.InvertedIndex.retrieve().docID;   // for the first word
        }

        for (int i = 1; i < terms.length; i++) {
            found = inverted.isWordExist(terms[i].trim().toLowerCase()); 
            if (found) {
                L2 = inverted.InvertedIndex.retrieve().docID;
                L1 = ORQ(L1, L2);
            }
        }

        return L1;
    }

    
    
    
    
    
    public static LinkedList<Integer> ORQ(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> result = new LinkedList<>();
        if (a.empty() && b.empty())
            return result;

        a.findFirst();    // first add IDs of A in result 
        while (!a.empty()) {
            boolean found = isExist(result, a.retrieve());
            if (!found) {
                result.insert(a.retrieve());
            }
                
           
            if (!a.last())
                a.findNext();
            else
                break;
        }
        b.findFirst(); 
        while(!b.empty()){
                        boolean found = isExist(result, b.retrieve());
                         if (!found) {
                         result.insert(b.retrieve());
                        }
                         if(!b.last())
                             b.findNext();
                         else
                             break;
        }

            
            
            
        
        return result;
    }

    
     public static LinkedList<Integer> BooleanQuery(String query) {  // here we start the query 
         query=query.replaceAll("[^a-zA-Z0-9\\sANDOR]", " ").replaceAll("\\s+", " ").trim();

    if (!query.contains("AND") && !query.contains("OR"))
        return andQ(query);
    else if (query.contains("AND") && !query.contains("OR"))
        return andQ(query);
    else if (!query.contains("AND") && query.contains("OR"))
        return ORQ(query);
    else
        return MixQuery(query);
}
    public static LinkedList<Integer> MixQuery(String query) {
    LinkedList<Integer> A = new LinkedList<Integer>();
    LinkedList<Integer> B = new LinkedList<Integer>();
    if (query.length() == 0) return A;
    String ORList[] = query.split("OR");
    A = andQ(ORList[0]);
    for (int i = 1; i < ORList.length; i++) {
        B = andQ(ORList[i]);
        A = ORQ(A, B);
    }
    return A;
}
    
    
    
}

    

