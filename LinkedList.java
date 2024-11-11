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
    public T retrieve(){
        return current.data;
    }
   
   public void insert (T val) {
 Node<T> tmp;
if (empty()) {
current = head = new Node<T> (val);
}
else {
tmp = current.next;
current.next = new Node<T> (val);
current = current.next;
current.next = tmp;
}
   }
}
