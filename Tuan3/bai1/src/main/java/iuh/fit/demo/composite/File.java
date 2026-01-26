package iuh.fit.demo.composite;

/**
 * Leaf class trong Composite Pattern
 * Đại diện cho một tập tin - không thể chứa component khác
 */
public class File extends FileSystemComponent {
    private String content;
    
    public File(String name, long size, String content) {
        super(name);
        this.size = size;
        this.content = content;
    }
    
    @Override
    public long getSize() {
        return size;
    }
    
    @Override
    public void display(int depth) {
        System.out.println(getIndent(depth) + "[FILE] " + name + " (" + size + " bytes)");
        if (content != null && !content.isEmpty()) {
            System.out.println(getIndent(depth + 1) + "Content: " + content);
        }
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
}