public class SingleLinkedList<T> {
    Node<T> head;

    class Node<T> {
        T data;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addLastNode(T data) {
        if (head == null) {
            head = new Node<T>(data);
            return;
        }

        Node<T> node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<T>(data);
    }

    public void printNode() {
        Node<T> node = this.head;
        if (node == null) System.out.println("SingleLinkedList is empty.");

        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public Node<T> findNode(T data) {
        Node<T> node = this.head;
        while (node != null) {
            if (node.data == data) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public void addAfterNode(T stdData, T newData) {
        if (findNode(stdData) == null) {
            addLastNode(newData);
            return;
        }

        Node<T> node = findNode(stdData);
        Node<T> nextNode = node.next;
        node.next = new Node<T>(newData);
        node.next.next = nextNode;
    }

    public void deleteNode(T targetData) {
        if (findNode(targetData) == null)
            return;

        Node<T> node = this.head;
        //1.첫번째 노드인 경우
        if (node.data == targetData) {
            this.head = node.next;
            return;
        }
        while (node.next != null) {
            //2.중간 노드인 경우
            if (node.next.data == targetData) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
        }

    }
}
