/**
 * Queue.java
 * 
 * @author Bobby Yan
 * @version 1.0 Apr 19 2023
 * A class for a simple queue
 */

 class Queue<E> {
    private Node<E> head;
  
    /**
     * enqueue
     * 
     * Enqueue a item to the queue
     *
     * @param item the item to add
     */
    public void enqueue(E item) {
      Node<E> tempNode = head;
  
      if (head == null) {
        head = new Node<E>(item, null);
        return;
      }

      
      while (tempNode.getNext() != null) {
        tempNode = tempNode.getNext();
      }
    
      tempNode.setNext(new Node<E>(item, null));
      return;
    }

    /**
     * dequeue
     * 
     * Removes the first item of the queue
     * 
     */
    public void dequeue(){
      if (head == null){
        return;
      }
      head = head.getNext();
      return;
    }

    /**
     * getIndex
     * 
     * Gets the value of a index in the queue
     * 
     * @param index a index in the queue
     * @return the value of the item at the index
     */
    public E getIndex(int index) {
      Node<E> tempNode = this.head;
  
      if (index >= this.size()){
        return null;
      }
  
      for (int i = 1; i < index; i++) {
        tempNode = tempNode.getNext();
      }
      return tempNode.getItem();
  
    }

    /**
     * size 
     * 
     * Gets the size of the queue
     * 
     * @return the size of the queue
     */
    public int size() {
      int size = 0;
      Node<E> tempNode = head;
  
      if (head == null) {
        return size;
      }
  
      while (tempNode.getNext() != null) {
        tempNode = tempNode.getNext();
        size++;
      }
  
      return size + 1;
    }
    
    private class Node<T> {
      private T item;
      private Node<T> next;
  
      Node(T i, Node<T> n) {
        this.item = i;
        this.next = n;
      }
  
      public Node<T> getNext() {
        return this.next;
      }
  
      public void setNext(Node<T> next) {
        this.next = next;
      }
  
      public T getItem() {
        return this.item;
      }
      

    }
  }