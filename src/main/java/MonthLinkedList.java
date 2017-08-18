import java.util.Objects;
import java.util.StringJoiner;

public class MonthLinkedList {

    private Node head;
    private Node tail;

    private int size;


    public static MonthLinkedList of(String ... args){
        MonthLinkedList list = new MonthLinkedList();

        for (String arg : args) {
            list.add(arg);
        }

        return list;
    }

    public void add(String element){
        if (!Month.contains(element)) throw new IllegalArgumentException();
        if (size == 0){
            head = new Node();
            tail = head;
            head.value = element;
            size ++;
            return;
        }

        Node newNode = new Node();
        tail.next = newNode;
        newNode.value = element;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }

    public boolean remove(String element){
        if (element == null) throw new NullPointerException();

        Node nde = head;

        while (nde.next != null){

            if (nde.value.equals(element)){
                Node prevNode = nde.prev;
                Node nextNode = nde.next;
                nextNode.prev = prevNode;
                prevNode.next = nextNode;
                size--;
                return true;
            }
            nde = nde.next;
        }

        return false;
    }

    public void remove(int index){
        if (index >= size) throw new IndexOutOfBoundsException("Actual size is " + size + ". Requested index is " + index);
        else if (index < 0) throw new IllegalArgumentException("Negative index");

        if (index == 0) {
            removeFirst();
            return;
        }
         else if (index == size - 1) {
            removeLast();
            return;
        }

        Node nde = getNode(index);

        Node prevNode = nde.prev;
        Node nextNode = nde.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
    }

    public void removeLast(){
        if (tail == null) throw new NullPointerException("Tail is null");
        tail = tail.prev;
        size--;
    }

    public void removeFirst(){
        if (head == null) throw new NullPointerException("Head is null");
        head = head.next;
        size--;
    }

    public String get(int i){
        if (i >= size) throw new IndexOutOfBoundsException("Actual size is " + size + ". Requested index is " + i);
        else if (i < 0) throw new IllegalArgumentException("Negative index");
        else if (size == 0) return head.value;

       return getNode(i).value;
    }

    private Node getNode(int i){
        if (i >= size) throw new IndexOutOfBoundsException("Actual size is " + size + ". Requested index is " + i);
        else if (i < 0) throw new IllegalArgumentException("Negative index");
        else if (size == 0) return head;

        if (i < size / 2)
            return getNodeFromHead(i);
        else
            return getNodeFromTail(i);

    }

    private Node getNodeFromTail(int index) {
        Node target = tail;
        for (int i = size - 1; i > index; i--)
            target = target.prev;

        return target;
    }

    private Node getNodeFromHead(int index) {
        Node target = head;

        for (int i = 0; i < index; i++ )
            target = target.next;

        return target;
    }

    public void set(int index, String value){
        if (!Month.contains(value)) throw new IllegalArgumentException();
        Node node = getNode(index);
        node.value = value;
    }


    public void sort(MonthComparator comparator){

        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j++){

                Node node = getNode(i);
                if (comparator.compare(get(i), get(j)) < 0){
                    String tmp = get(i);
                    set(i, get(j));
                    set(j, tmp);
                }
            }
        }

    }

    @Override
    public String toString() {
        Node node = head;
        StringJoiner joiner = new StringJoiner(", ");

        while (node != null) {
            joiner.add(node.value);
            node= node.next;
        }

        return joiner.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthLinkedList that = (MonthLinkedList) o;
        if (size != that.size) return false;
        if (head == null && that.head == null) return true;

        Node n1 = head;
        Node n2 = that.head;
        int i = 0;

        while (n1.equals(n2) && i < size - 1){
            n1 = n1.next;
            n2 = n2.next;
            i++;
        }

        return i == size - 1;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;

        Node nde = head;
        while (nde != null){
            hashCode *= 32;
            hashCode += nde.hashCode();
            nde = nde.next;
        }

        return hashCode;
    }


    // Node --------------------------------------------------------------------------------------

    private static class Node {
        private String value;
        private Node next;
        private Node prev;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

    }
}
