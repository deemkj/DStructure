/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
 class DocRank {
    int id;
    int rank;
public DocRank(int id, int rank) {
    this.id = id;
    this.rank = rank;
}



    public void display() {
        System.out.println(id + "          " + rank);
    }
}

public class Ranking2 {

    static Index index;
    static InvertedIndexBST invertedIndex;
    static String query;
    static LinkedList<Integer> documentIds; // (document ids in query before ranking)
    static LinkedList<DocRank> rankedDocuments;

    public Ranking2(Index index, InvertedIndexBST invertedIndex, String query) {
        this.index = index;
        this.invertedIndex = invertedIndex;
        this.query = query;
        documentIds = new LinkedList<>();
        rankedDocuments = new LinkedList<>();
    }
    
    
    public static void generateRankedList() {
        findRelevantDocuments(query);
        if (documentIds.empty()) {
            return;
        }

        documentIds.findFirst();
        while (!documentIds.last()) {
            Document document = getDocumentById(documentIds.retrieve());
            if (document != null) {
                int score = calculateDocumentScore(document, query);
                DocRank docRank = new DocRank(documentIds.retrieve(), score);
                insertRankedDocument(docRank);
            }
            documentIds.findNext();
        }

        Document document = getDocumentById(documentIds.retrieve());
        if (document != null) {
            int score = calculateDocumentScore(document, query);
            DocRank docRank = new DocRank(documentIds.retrieve(), score);
            insertRankedDocument(docRank);
        }
    }
 public static void findRelevantDocuments(String query) {
        if (query == null) return;

        String[] terms = query.split("\\s+");
        for (String term : terms) {
            term = term.trim().toLowerCase();
            if (invertedIndex.isWordExist(term)) {
                LinkedList<Integer> termDocumentIds = invertedIndex.InvertedIndex.retrieve().docID;
                addDocumentIds(termDocumentIds);
            }
        }
    }

    public static Document getDocumentById(int id) {
        return index.getDocumentByID(id);
    }
    
 public static int calculateDocumentScore(Document document, String query) {
        if (query == null) return 0;

        String[] terms = query.split(" ");
        int totalScore = 0;

        for (int i =0 ; i< terms.length ;i++) {
            totalScore += countTermFrequency(document, terms[i].trim().toLowerCase());
        }
        return totalScore;
    }

    public static int countTermFrequency(Document document, String term) {
        if (document == null || document.Words.empty()) return 0;

        int count = 0;
        document.Words.findFirst();
        while (!document.Words.last()) {
            if (document.Words.retrieve().equalsIgnoreCase(term)) count++;
            document.Words.findNext();
        }
        if (document.Words.retrieve().equalsIgnoreCase(term)) count++;

        return count;
    }

   
   

    private static void addDocumentIds(LinkedList<Integer> termDocumentIds) {
        if (termDocumentIds.empty()) return;

        termDocumentIds.findFirst();
        while (!termDocumentIds.last()) {
            if (!isDocumentIdExists(documentIds, termDocumentIds.retrieve())) {
                insertDocumentIdSorted(termDocumentIds.retrieve());
            }
            termDocumentIds.findNext();
        }
        if (!isDocumentIdExists(documentIds, termDocumentIds.retrieve())) {
            insertDocumentIdSorted(termDocumentIds.retrieve());
        }
    }

    public void displayRankedDocuments() {
        if (rankedDocuments.empty()) {
            System.out.println("No documents to display.");
            return;
        }

        System.out.println("DocID\tScore");
        rankedDocuments.findFirst();
        while (!rankedDocuments.last()) {
            rankedDocuments.retrieve().display();
            rankedDocuments.findNext();
        }
        rankedDocuments.retrieve().display(); // Print the last element
    }

    public static void insertDocumentIdSorted(Integer id) {
        if (documentIds.empty()) {
            documentIds.insert(id);
            return;
        }

        documentIds.findFirst();
        while (!documentIds.last()) {
            if (id < documentIds.retrieve()) {
                Integer tempId = documentIds.retrieve();
                documentIds.update(id);
                documentIds.insert(tempId);
                return;
            } else {
                documentIds.findNext();
            }
        }

        if (id < documentIds.retrieve()) {
            Integer tempId = documentIds.retrieve();
            documentIds.update(id);
            documentIds.insert(tempId);
        } else {
            documentIds.insert(id);
        }
    }

    
    public static void insertRankedDocument(DocRank docRank) {
        if (rankedDocuments.empty()) {
            rankedDocuments.insert(docRank);
            return;
        }
        rankedDocuments.findFirst();
        while (!rankedDocuments.last()) {
            if (docRank.rank > rankedDocuments.retrieve().rank) {
                DocRank temp = rankedDocuments.retrieve();
                rankedDocuments.update(docRank);
                rankedDocuments.insert(temp);
                return;
            } else {
                rankedDocuments.findNext();
            }
        }
        if (docRank.rank > rankedDocuments.retrieve().rank) {
            DocRank temp = rankedDocuments.retrieve();
            rankedDocuments.update(docRank);
            rankedDocuments.insert(temp);
        } else {
            rankedDocuments.insert(docRank);
        }
    }

    public static boolean isDocumentIdExists(LinkedList<Integer> list, Integer id) {
        if (list.empty()) return false;
        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().equals(id)) return true;
            list.findNext();
        }
        return list.retrieve().equals(id);
    }
}
