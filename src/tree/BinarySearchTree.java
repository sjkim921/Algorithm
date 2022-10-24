package tree;

public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        this.root = null;
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
        if (this.root == null) {
            this.root = newNode;
            return true;
        }

        Node node = this.root;
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
        Node node = this.root;
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

    public boolean deleteNode(int data) {
        boolean searched = false;

        if (this.root == null) {
            System.out.println("트리가 존재하지 않습니다.");
            return false;
        }

        if (this.root.left == null && this.root.right == null) {
            System.out.println(data + "삭제완료.");
            this.root = null;
            return true;
        }

        Node parentNode = this.root;
        Node node = this.root;
        while (node != null) {
            if (data < node.value) {
                parentNode = node;
                node = node.left;
            } else if (data > node.value) {
                parentNode = node;
                node = node.right;
            } else {
                searched = true;
                break;
            }
        }

        if (!searched) {
            System.out.println("트리에 존재하지 않는 값입니다.");
            return false;
        }

        if (node.left == null && node.right == null) { // 1.leaf노드 - 부모노드의 값과는 같을 수 없음.
            if (data < parentNode.value) {
                parentNode.left = null;
                node = null;
            } else {
                parentNode.right = null;
                node = null;
            }
            System.out.println(data + "삭제완료.");
            return true;
        } else if (node.left != null && node.right == null) { // 2-1.왼쪽에 child노드 1개
            if (data < parentNode.value) {
                parentNode.left = node.left;
                node = null;
            } else {
                parentNode.right = node.left;
                node = null;
            }
            System.out.println(data + "삭제완료.");
            return true;
        } else if (node.right != null && node.left == null) { // 2-2.오른쪽에 child노드 1개
            if (data < parentNode.value) {
                parentNode.left = node.right;
                node = null;
            } else {
                parentNode.right = node.left;
                node = null;
            }
            System.out.println(data + "삭제완료.");
            return true;

        } else { // 2-3.child노드 2개
        }
        return false;
    }
}
