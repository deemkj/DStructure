/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class QueryProcessing {

    static InvertedIndex inverted;

    public QueryProcessing(InvertedIndex inverted) {
        this.inverted = inverted;
    }
    
    public static LinkedList<Integer> BooleanQuery(String query) {

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
    String ors[] = query.split("OR");

    A = andQ(ors[0]);
    for (int i = 1; i < ors.length; i++) {
        B = andQ(ors[i]);
        A = ORQ(A, B);
    }
    return A;
}


    public static LinkedList<Integer> andQ(String query) {
        LinkedList<Integer> A = new LinkedList<>();
        LinkedList<Integer> B = new LinkedList<>();
        String[] terms = query.split("AND");

        if (terms.length == 0) return A;

        // معالجة الكلمة الأولى
        boolean found = inverted.isWordExist(terms[0].trim().toLowerCase());
        if (found) {
            A = inverted.Inverted_Index.retrieve().docID;
        }

        for (int i = 1; i < terms.length; i++) {
            found = inverted.isWordExist(terms[i].trim().toLowerCase()); 
            if (found) {
                B = inverted.Inverted_Index.retrieve().docID;
                A = andQ(A, B);
            }
        }

        return A;
    }

    public static LinkedList<Integer> andQ(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> result = new LinkedList<>();
        if (a.empty() || b.empty())
            return result;

        a.findFirst();
        while (true) {
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
        LinkedList<Integer> A = new LinkedList<>();
        LinkedList<Integer> B = new LinkedList<>();
        String[] terms = query.split("OR");

        if (terms.length == 0) return A;

        // معالجة الكلمة الأولى
        boolean found = inverted.isWordExist(terms[0].trim().toLowerCase());
        if (found) {
            A = inverted.Inverted_Index.retrieve().docID;
        }

        for (int i = 1; i < terms.length; i++) {
            found = inverted.isWordExist(terms[i].trim().toLowerCase()); 
            if (found) {
                B = inverted.Inverted_Index.retrieve().docID;
                A = ORQ(A, B);
            }
        }

        return A;
    }

    
    
    
    
    
    public static LinkedList<Integer> ORQ(LinkedList<Integer> a, LinkedList<Integer> b) {
        LinkedList<Integer> result = new LinkedList<>();
        if (a.empty() && b.empty())
            return result;

        a.findFirst();
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

    
    
    
    
}
    
    
    
    
}
