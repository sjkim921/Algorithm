package tree;

public class BinarySearchTree {
    Node head;

    public BinarySearchTree() {
        this.head = null;
    }

    class Node {
        Node left;
        Node right;
        int value;

        public Node(int data) {
            this.left = null;
            this.right = null;
            this.value = data;
        }
    }

    public boolean insertNode(int data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
            return true;
        }

        Node node = this.head;
        while (true) {
            if (data == node.value) {
                System.out.println("중복 값은 입력할 수 없습니다: " + data);
                return false;
            } else if (data < node.value) { //Tree 왼쪽
                if (node.left == null) {
                    node.left = newNode;
                    break;
                }
                node = node.left;
            } else { //Tree 오른쪽
                if (node.right == null) {
                    node.right = newNode;
                    break;
                }
                node = node.right;
            }
        }
        return true;
    }

    public Node search(int data) {
        Node node = this.head;
        if (node == null) {
            return null;
        }

        while (node != null) {
            if (data == node.value) {
                return node;
            } else if (data < node.value) { //왼쪽 탐색
                node = node.left;
            } else { //오른쪽 탐색
                node = node.right;
            }
        }
        return null;
    }
}
