/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deemkj
 */
public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    

    public LinkedList() {
        head = current = null;
    }

    public boolean empty() {
        return head == null;
    }

    public boolean last() {
        if (current == null) {
            return false; 
        }
        return current.next == null;
    }
    
    public void findFirst(){
        current=head;
    }
   
    public void findNext(){
        current=current.next;
    }
    //public T retrieve(){
     //   return current.data;
   // }
   
   public void insert(T val) {
    if (empty()) {
        current = head = new Node<T>(val);
    } else {
        if (current == null) {
          
            findFirst(); 
        }
        Node<T> tmp = current.next;
        current.next = new Node<T>(val);
        current = current.next;
        current.next = tmp;
    }
}


    void display() {
      Node<T> temp=head;
       while(temp!=null){
           System.out.println(temp.data);
           temp=temp.next;
       }
        
    }
    
    public T retrieve() {
    if (current == null) {
        throw new IllegalStateException("Current node is null. Ensure that findFirst() or findNext() is called before retrieve().");
    }
    return current.data;
}
///////////////////////////////
public boolean contains(T val) {
    Node<T> temp = head;
    while (temp != null) {
        if (temp.data.equals(val)) {
            return true;
        }
        temp = temp.next;
    }
    return false;
}
public int size() {
    int count = 0;
    Node<T> temp = head;
    while (temp != null) {
        count++;
        temp = temp.next;
    }
    return count;
}
public void remove(T val) {
    if (empty()) return;

    if (head.data.equals(val)) {
        head = head.next;
        if (head == null) {
            current = null;
        }
        return;
    }

    Node<T> prev = head;
    Node<T> temp = head.next;

    while (temp != null) {
        if (temp.data.equals(val)) {
            prev.next = temp.next;
            if (temp == current) {
                current = prev;
            }
            return;
        }
        prev = temp;
        temp = temp.next;
    }
}


public void update(T newValue) {
    //if (current == null) {
       // throw new IllegalStateException("Current node is null. Ensure that findFirst() or findNext() is called before updateCurrent().");
   // }
    current.data = newValue; // Update the data in the current node
}




}
