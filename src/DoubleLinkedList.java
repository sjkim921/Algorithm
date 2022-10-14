public class DoubleLinkedList<T> {
    Node<T> head = null;
    Node<T> tail = null;

    class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addNode(T data) {
        if (this.head == null) {
            this.head = new Node<T>(data);
            this.tail = this.head;
            return;
        }

        Node<T> node = this.head;
        while (node.next != null) {
            node = node.next;
        }

        //tail뒤에 새 데이터를 넣는다
        node.next = new Node<T>(data);
        node.next.prev = node;
        this.tail = node.next;
    }

    public void printAllNodes() {
        Node<T> node = this.head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public Node<T> searchNodeFromHead(T data) {
        Node<T> node = this.head;
        while (node != null) {
            if (node.data == data) {
                System.out.println("리스트에 있는 데이터입니다.");
                return node;
            }
            node = node.next;
        }
        System.out.println("리스트에 없는 데이터입니다.");
        return null;
    }

    public Node<T> searchNodeFromTail(T data) {
        Node<T> node = this.tail;
        while (node.prev != null) {
            if (node.data == data) {
                System.out.println("리스트에 있는 데이터입니다.");
                return node;
            }
            node = node.prev;
        }
        System.out.println("리스트에 없는 데이터입니다.");
        return null;
    }

    public void addNodeAhead(T stdData, T newData) {
        Node<T> node = this.head;
        while (node != null) {
            if (node.data == stdData) {
                Node<T> prevNode = node.prev;
                Node<T> newNode = new Node<T>(newData);
                //1.리스트 head 다음에 삽입(prevNode가 null)
                if (prevNode == null) {
                    this.head = newNode;
                    newNode.next = node;
                    node.prev = newNode;
                    return;
                }
                //2.리스트 중간에 삽입
                prevNode.next = newNode;
                newNode.prev = prevNode;
                node.prev = newNode;
                newNode.next = node;
                return;
            }
            node = node.next;
        }
        System.out.println(stdData + "은(는) 리스트에 없는 데이터이므로 마지막에 삽입합니다.");
        addNode(newData);
    }

}
