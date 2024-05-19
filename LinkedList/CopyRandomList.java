package LinkedList;

class Node{
    int data;
    Node next;
    Node random;
    Node(int data, Node next){
        this.next = next;
        this.data = data;
    }
}

public class CopyRandomList {
    
    public static Node createList(){
        Node node5 = new Node(5, null);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        node1.random = node4;
        node2.random = node3;
        node3.random = node5;
        node4.random = node3;
        node5.random = node2;
        return node1;
    }

    public static void displayList(Node head){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + "[" + (temp.random != null ? temp.random.data : null) + "]" + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
        
    }

    /**
     * Create copy of list which has same next elements.
     * 
     * @param head old list which needs to create a copy with elements has same next as old has next elements
     * @return void because it does not require, because new list's start eleement is head's next element.
     */
    public static void createNewListWithoutRandom(Node head){
        Node temp = head;
        while (temp != null) {
            Node node = new Node(temp.data,temp.next);
            temp.next = node;
            temp = node.next;
        }
    }



    public static void copyRandomElements(Node head){
        Node temp = head;
        while (temp != null) {
            temp.next.random = temp.random.next;
            temp = temp.next.next;
        }
    }

    public static Node connectToOriginal(Node head){
        Node original = head;
        Node copy = head.next;
        Node c = head.next;
        while(original != null){
            Node temp = null;
            if(copy.next != null){
                temp = copy.next.next;
            }
            original.next = copy.next;
            copy.next = temp;
            original = original.next;
            copy = copy.next;
        }
        return c;
    }
    
    public static void main(String a[]){
        Node head = createList();
        displayList(head); //     1[4] -> 2[3] -> 3[5] -> 4[3] -> 5[2] -> null
        createNewListWithoutRandom(head);
        displayList(head); //     1[4] -> 1[null] -> 2[3] -> 2[null] -> 3[5] -> 3[null] -> 4[3] -> 4[null] -> 5[2] -> 5[null] -> null
        copyRandomElements(head);
        displayList(head); //     1[4] -> 1[4] -> 2[3] -> 2[3] -> 3[5] -> 3[5] -> 4[3] -> 4[3] -> 5[2] -> 5[2] -> null
        Node copyHead = connectToOriginal(head);
        displayList(head); //     1[4] -> 2[3] -> 3[5] -> 4[3] -> 5[2] -> null
        displayList(copyHead); // 1[4] -> 2[3] -> 3[5] -> 4[3] -> 5[2] -> null
    }
    
}
