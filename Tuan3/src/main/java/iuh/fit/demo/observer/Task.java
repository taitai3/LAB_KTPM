package iuh.fit.demo.observer;

/**
 * Concrete Subject - Task
 * Thông báo khi trạng thái công việc thay đổi
 */
public class Task extends Subject {
    private String name;
    private String status;
    private String assignee;
    
    public Task(String name, String initialStatus, String assignee) {
        super();
        this.name = name;
        this.status = initialStatus;
        this.assignee = assignee;
    }
    
    public void setStatus(String newStatus) {
        String oldStatus = this.status;
        this.status = newStatus;
        
        String statusIcon = getStatusIcon(newStatus);
        String message = String.format(
            "Task '%s' (Assignee: %s): %s %s -> %s %s",
            name, assignee, getStatusIcon(oldStatus), oldStatus, statusIcon, newStatus
        );
        
        notifyObservers(message);
    }
    
    private String getStatusIcon(String status) {
        switch (status.toLowerCase()) {
            case "todo": return "[TODO]";
            case "in progress": return "[PROGRESS]";
            case "testing": return "[TESTING]";
            case "done": return "[DONE]";
            case "blocked": return "[BLOCKED]";
            default: return "[STATUS]";
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getAssignee() {
        return assignee;
    }
}