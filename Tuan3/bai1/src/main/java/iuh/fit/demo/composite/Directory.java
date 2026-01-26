package iuh.fit.demo.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite class trong Composite Pattern
 * Đại diện cho thư mục - có thể chứa File hoặc Directory khác
 */
public class Directory extends FileSystemComponent {
    private List<FileSystemComponent> children;
    
    public Directory(String name) {
        super(name);
        this.children = new ArrayList<>();
    }
    
    @Override
    public void add(FileSystemComponent component) {
        children.add(component);
    }
    
    @Override
    public void remove(FileSystemComponent component) {
        children.remove(component);
    }
    
    @Override
    public long getSize() {
        long totalSize = 0;
        for (FileSystemComponent child : children) {
            totalSize += child.getSize();
        }
        return totalSize;
    }
    
    @Override
    public void display(int depth) {
        System.out.println(getIndent(depth) + "[DIR] " + name + "/ (" + getSize() + " bytes total)");
        for (FileSystemComponent child : children) {
            child.display(depth + 1);
        }
    }
    
    public List<FileSystemComponent> getChildren() {
        return new ArrayList<>(children);
    }
    
    public int getChildCount() {
        return children.size();
    }
}