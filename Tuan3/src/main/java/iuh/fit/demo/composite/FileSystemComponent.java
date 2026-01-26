package iuh.fit.demo.composite;

/**
 * Abstract Component class for Composite Pattern
 * Define common interface for both File and Directory
 */
public abstract class FileSystemComponent {
    protected String name;
    protected long size;
    
    public FileSystemComponent(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public abstract long getSize();
    public abstract void display(int depth);
    
    // Default implementation - chỉ Directory mới override
    public void add(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot add to a file");
    }
    
    public void remove(FileSystemComponent component) {
        throw new UnsupportedOperationException("Cannot remove from a file");
    }
    
    // Helper method để tạo indentation
    protected String getIndent(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        return indent.toString();
    }
}