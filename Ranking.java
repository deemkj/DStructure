class DocRank{
    int id ;
    int rank ;


public DocRank(int id , int rank){
this.id = id ;
this.rank = rank ;
}

public void display(){
    System.out.println(id+ " "+rank);
}



public class Ranking {
static String query;
static InvertedIndexBST invertBST;
static index index1;
static LinkedList<Integer>allDocInquery;
static LinkedList<DocRank>allDocRank;
}

public class Ranking(InvertedIndexBST invertBST , index index1 , String query){
this.invertBST = invertBST ;
this.index1 = index1 ; 
this.query=query;
allDocInquery = new LinkedList<Integer>();
allDocRank = new LinkedList<DocRank>();
}
    
    
    
    
public static void displayALLdocument_withscore(){
    if(allDocRank.empty())
        System.out.println(" this list is empty ");
    return ;
}
 
System.out.println("Document ID" , "Score");
allDocRank.findfirst();
while(!allDocRank.last())
{
    allDocRank.retrieve().display();
    allDocRank.findnext();
}
allDocRank.retrieve().display();
}

public static Document get_doc_given_id(int id) {
    return index1.get_document_given_id(id);
}

public static Document getDoc_beGivenID(int id )
return index1.getDoc_beGivenID(id);
}

public static int term_frequency_in_doc(Document d, String term) {
    int freq = 0;
    LinkedList<String> words = d.words;
    if (words.empty()) return 0;
    words.findFirst();
    while (!words.last()) {
        if (words.retrieve().equalsIgnoreCase(term))
            freq++;
        words.findNext();
    }
    if (words.retrieve().equalsIgnoreCase(term))
        freq++;
    return freq;
}

public static int get_doc_rank_score(Document d, String Query) 
{
    if (Query.length() == 0) return 0;
    String terms[] = Query.split(" ");
    int sum_freq = 0;
    for (int i = 0; i < terms.length; i++)
    {
        sum_freq += term_frequency_in_doc(d, terms[i].trim().toLowerCase());
    }
    return sum_freq;
}

public static void RankQuery(String Query) {
    LinkedList<Integer> A = new LinkedList<Integer>();
    if (Query.length() == 0) return;
    String terms[] = Query.split(" ");
    boolean found = false;
    for (int i = 0; i < terms.length; i++)
    {
        found = inverted.search_word_in_inverted(terms[i].trim().toLowerCase());
        if (found)
            A = inverted.inverted_index.retrieve().doc_IDS;
            Adding_in_1_List_sorted(A);
    }
}

public static void Adding_in_1_List_sorted(LinkedList<Integer> A) 
{
    if (A.empty()) 
        return;
    A.findFirst();
    while (!A.empty()) {
        boolean found = existsIn_result(all_doc_in_query, A.retrieve());
        if (!found) { // not found in result
            insert_sorted_Id_list(A.retrieve());
        } // end if
        if (!A.last())
            A.findNext();
        else
            break;
    }
}

public static boolean existsIn_result(LinkedList<Integer> result, Integer id) 
{
    if (result.empty()) return false;
    result.findFirst();
    while (!result.last()) {
        if (result.retrieve().equals(id)) {
            return true;
        }
        result.findNext();
    }
    if (result.retrieve().equals(id)) {
            return true;
}

    return false;
}

public static void insert_sorted_Id_list(Integer id) 
{
    if (all_doc_in_query.empty()) {
        all_doc_in_query.insert(id);
        return;
    }
    all_doc_in_query.findFirst();
    
    while (!all_doc_in_query.last()) {
        if (id < all_doc_in_query.retrieve()) {
            Integer id1 = all_doc_in_query.retrieve();
            all_doc_in_query.update(id);
            all_doc_in_query.insert(id1);
            return;
        } else
            all_doc_in_query.findNext();
    }
    
    if (id < all_doc_in_query.retrieve()) {
        Integer id1 = all_doc_in_query.retrieve();
        all_doc_in_query.update(id);
        all_doc_in_query.insert(id1);
        return;
    }
  else
 all_doc_in_query.insert(id1);
}

public static void insert_sorted_in_list() 
{
    RankQuery(Query); // to fill all_doc_in_query
    if (all_doc_in_query.empty()) {
        System.out.println("empty query");
        return;
    }
    all_doc_in_query.findFirst();
    while (!all_doc_in_query.last()) {
        Document d = get_doc_given_id(all_doc_in_query.retrieve());
        int Rank = get_doc_rank_score(d, Query);
        insert_sorted_list(new Doc_Rank(all_doc_in_query.retrieve(), Rank));
        all_doc_in_query.findNext();
    }
    
    Document d = get_doc_given_id(all_doc_in_query.retrieve());
    int Rank = get_doc_rank_score(d, Query);
    insert_sorted_list(new Doc_Rank(all_doc_in_query.retrieve(), Rank));
}

public static void insert_sorted_list(Doc_Rank dr) 
{
    if (all_doc_ranked.empty()) {
        all_doc_ranked.insert(dr);
        return;
    }
    all_doc_ranked.findFirst();
    
    while (!all_doc_ranked.last()) {
        if (dr.rank < all_doc_ranked.retrieve().rank) {
            Doc_Rank dr1 = all_doc_ranked.retrieve();
            all_doc_ranked.update(dr);
            all_doc_ranked.insert(dr1);
            return;
        } else
            all_doc_ranked.findNext();
    }
    
    if (dr.rank > all_doc_ranked.retrieve().rank) {
        Doc_Rank dr1 = all_doc_ranked.retrieve();
        all_doc_ranked.update(dr);
        all_doc_ranked.insert(dr1);
       return;
    }
  all_doc_ranked.insert(dr1);
}

}
