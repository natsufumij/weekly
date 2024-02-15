import java.util.LinkedList;
import java.util.List;

public class MyTree<T> implements TreeNode<T>{
    private final T data;
    private LinkedList<TreeNode<T>> children;
    public MyTree(T d){
        data=d;
        children=new LinkedList<>();
    }
    public List<TreeNode<T>> getChildren(){
        return children;
    }
    public boolean isLeaf(){
        return children.isEmpty();
    }
    @Override
    public T getData() {
        return data;
    }    
}

interface TreeNode<T>{
    List<TreeNode<T>> getChildren();
    boolean isLeaf();
    T getData();
}