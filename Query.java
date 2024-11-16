/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class Query {
    

    private InvertedIndex index;

    public Query(InvertedIndex index) {
        this.index = index;
    }
public LinkedList<Integer> processQuery(String query) {
    // تنظيف الاستعلام من الأحرف غير الضرورية وتحويله إلى أحرف صغيرة
query = query.replaceAll("[.,]", "").toLowerCase();

    String[] terms = query.split("\\s+");

    // قائمة لمعالجة الاستعلامات بترتيب الأولويات
    LinkedList<Integer> result = null;
    LinkedList<Integer> currentResult = null;

    for (int i = 0; i < terms.length; i++) {
        String term = terms[i];
   

        if (!term.equals("AND") && !term.equals("OR") && !term.equals("NOT")) {
            LinkedList<Integer> currentDocuments = getDocuments(term);
            if (currentDocuments == null) {
                currentDocuments = new LinkedList<>();
            }

            if (result == null) {
                result = new LinkedList<>();
                copyList(currentDocuments, result);
            }

            if (currentResult == null) {
                currentResult = new LinkedList<>();
                copyList(currentDocuments, currentResult);
            } else {
                currentResult = currentDocuments;
            }
        } else if (term.equals("AND")) {
            // معالجة عمليات AND
            if (i + 1 < terms.length) {
                LinkedList<Integer> nextDocuments = getDocuments(terms[++i]);
                if (nextDocuments == null) {
                    nextDocuments = new LinkedList<>();
                }
                currentResult = intersectLists(currentResult, nextDocuments);
            }
        } else if (term.equals("OR")) {
            // معالجة عمليات OR بعد انتهاء كل عمليات AND
            if (result == null) {
                result = new LinkedList<>();
                copyList(currentResult, result);
            } else {
                result = unionLists(result, currentResult);
            }
            currentResult = null;
        }
    }

    // دمج النتيجة النهائية إذا لم يكن هناك عملية OR في النهاية
    if (currentResult != null && result != null) {
        result = unionLists(result, currentResult);
    } else if (currentResult != null) {
        result = currentResult;
    }

    return result == null ? new LinkedList<>() : result;
}

    private LinkedList<Integer> getDocuments(String word) {
        index.Inverted_Index.findFirst();
        while (!index.Inverted_Index.last()) {
            Word currentWord = index.Inverted_Index.retrieve();
            if (currentWord.word.equals(word)) {
                return currentWord.getIDs();
            }
            index.Inverted_Index.findNext();
        }
        if (index.Inverted_Index.retrieve().word.equals(word)) {
            return index.Inverted_Index.retrieve().getIDs();
        }
        return new LinkedList<>();
    }

    private LinkedList<Integer> intersectLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
    LinkedList<Integer> intersection = new LinkedList<>();
    if (list1.empty() || list2.empty()) {
        return intersection; // إعادة قائمة فارغة إذا كانت أي من القائمتين فارغة
    }

    list1.findFirst();
    while (!list1.last()) {
        Integer item = list1.retrieve();
        if (contains(list2, item)) {
            intersection.insert(item);
        }
        list1.findNext();
    }
    if (list1.retrieve() != null && contains(list2, list1.retrieve())) {
        intersection.insert(list1.retrieve());
    }
    return intersection;
}

    private LinkedList<Integer> unionLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
    LinkedList<Integer> union = new LinkedList<>();
    if (!list1.empty()) {
        list1.findFirst();
        while (!list1.last()) {
            union.insert(list1.retrieve());
            list1.findNext();
        }
        if (list1.retrieve() != null) {
            union.insert(list1.retrieve());
        }
    }

    if (!list2.empty()) {
        list2.findFirst();
        while (!list2.last()) {
            Integer item = list2.retrieve();
            if (!contains(union, item)) {
                union.insert(item);
            }
            list2.findNext();
        }
        if (!contains(union, list2.retrieve())) {
            union.insert(list2.retrieve());
        }
    }

    return union;
}

    private boolean contains(LinkedList<Integer> list, Integer value) {
    if (list.empty()) {
        return false;
    }

    list.findFirst(); // التأكد من تعيين current
    while (!list.last()) {
        if (list.retrieve().equals(value)) {
            return true;
        }
        list.findNext();
    }
    return list.retrieve() != null && list.retrieve().equals(value);
}



    private void copyList(LinkedList<Integer> source, LinkedList<Integer> target) {
        source.findFirst();
        while (!source.last()) {
            target.insert(source.retrieve());
            source.findNext();
        }
        if (source.retrieve() != null) {
            target.insert(source.retrieve());
        }
    }





}





