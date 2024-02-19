public class CircularlyLinkedList <E>{

    private Node<E> tail= null;
    private int size= 0;

    public CircularlyLinkedList() {}

    public int getSize() {
        return size;
    }
    public boolean isEmpty() {
        return size==0;
    }
    public E first(){
        if(isEmpty())return null;
        return tail.getNext().getElement();
    }
    public E last(){
        if(isEmpty())return null;
        return tail.getElement();
    }
    public void rotate(){
        if(tail!=null){
            tail=tail.getNext();
        }
    }
    public void addFirst(E e){
        if(size==0){
            tail = new Node<>(e,null);
            tail.setNext(tail);
        }else{
            tail.setNext(new Node<>(e,tail.getNext()));
        }
        size++;
    }
    public void  addLast(Object e){
        addFirst((E) e);
        tail=tail.getNext();
    }
    public E removFirst(){
        if(isEmpty())return null;
        Node<E> head=tail.getNext();
        if (head==tail)tail=null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }

public static CircularlyLinkedList<?> concat(CircularlyLinkedList<?> L,CircularlyLinkedList<?> M){
    if (L.size==0) {
        return M;
    }

    if (M.size==0) {
        return L;
    }
    Node<?> node=M.tail.getNext();
    for (int i = 0; i <M.size ; i++) {
    L.addLast(node.getElement());
        node=node.getNext();
    }
    return L;
}

    @Override
    public CircularlyLinkedList<E> clone() {
        try {
            CircularlyLinkedList<E> clonedList = (CircularlyLinkedList<E>) super.clone();
            if (size > 0) {
                clonedList.tail = new Node<>(tail.element, null);
                Node<E> current = tail.next;
                Node<E> clonedCurrent = clonedList.tail;
                while (current != tail) {
                    clonedCurrent.next = new Node<>(current.element, null);
                    current = current.next;
                    clonedCurrent = clonedCurrent.next;
                }
                clonedCurrent.next = clonedList.tail;
            }
            return clonedList;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
    private int calculateSize(CircularlyLinkedList<E> list) {
        int count = 0;
        Node<E> currentNode = list.tail.getNext();
        while (currentNode != null) {
            count++;
            currentNode = currentNode.getNext();
            if (currentNode == list.tail.getNext()) {
                break;
            }
        }
        return count;
    }
    public boolean hasSameSequence(CircularlyLinkedList<E> L, CircularlyLinkedList<E> M) {
        if (L.isEmpty() && M.isEmpty()) {
            return true;
        }

        if (L.isEmpty() || M.isEmpty()) {
            return false;
        }

        Node<E> startingNodeM = M.tail.getNext();
        Node<E> currentNodeL = L.tail.getNext();

        while (true) {
            Node<E> currentNodeM = startingNodeM;

            while (currentNodeL.getNext() != L.tail.getNext()) {
                if (!currentNodeL.getElement().equals(currentNodeM.getElement())) {
                    break;
                }

                currentNodeL = currentNodeL.getNext();
                currentNodeM = currentNodeM.getNext();
            }

            if (currentNodeL.getNext() == L.tail.getNext()) {
                return true;
            }

            startingNodeM = startingNodeM.getNext();

            if (startingNodeM == M.tail.getNext()) {
                break;
            }
        }

        return false;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CircularlyLinkedList<?> otherList = (CircularlyLinkedList<?>) obj;

        if (size_() != otherList.size_()) {
            return false;
        }

        Node<E> currentNode = tail;
        Node<?> otherCurrentNode = otherList.tail;

        for (int i = 0; i < size_(); i++) {
            if (!currentNode.getElement().equals(otherCurrentNode.getElement())) {
                return false;
            }

            currentNode = currentNode.getNext();
            otherCurrentNode = otherCurrentNode.getNext();
        }

        return true;
    }

    public int size_(){
        if(isEmpty())return 0;
        Node<E> x=tail.getNext();
        int counter =1;
        while (x!=tail ){
            x=x.getNext();
            counter++;
            }
        return counter;
    }
    public static class Node<E>{
        private E element ;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}
