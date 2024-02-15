import java.util.ArrayDeque;
import java.util.Queue;

public class QueueDemo1 {
    public static void main(String[] args) {
        var data = prepareData();
        visiteTree(data);
    }   

    private static void visiteTree(TreeNode<String> tree){
        Queue<TreeNode<String>> queue = new ArrayDeque<>();
        queue.offer(tree);
        while(!queue.isEmpty()){
            var node = queue.poll();
            System.out.print(node.getData()+">");
            var children = node.getChildren();
            for(TreeNode<String> n: children){
                queue.offer(n);
            }
        }
        System.out.println(".");
    }
    
    /**
     * A
     * |  |
     * B   C
     * | |   |
     * D E   F
     * | | |  
     * G H I
     * @return
     */
    private static TreeNode<String> prepareData(){
        var a = new MyTree<>("A");
        var b = new MyTree<>("B");
        var c = new MyTree<>("C");
        var d = new MyTree<>("D");
        var e = new MyTree<>("E");
        var f = new MyTree<>("F");
        var g = new MyTree<>("G");
        var h = new MyTree<>("H");
        var i = new MyTree<>("I");
        a.getChildren().add(b);
        a.getChildren().add(c);
        b.getChildren().add(d);
        b.getChildren().add(e);
        c.getChildren().add(f);
        d.getChildren().add(g);
        e.getChildren().add(h);
        e.getChildren().add(i);
        return a;
    }
}
