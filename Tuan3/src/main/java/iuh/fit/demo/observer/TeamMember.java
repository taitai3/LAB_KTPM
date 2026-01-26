package iuh.fit.demo.observer;

/**
 * Concrete Observer - TeamMember
 * Nhận thông báo về thay đổi trạng thái task
 */
public class TeamMember implements Observer {
    private String name;
    private String role;
    
    public TeamMember(String name, String role) {
        this.name = name;
        this.role = role;
    }
    
    @Override
    public void update(String message) {
        System.out.println("[TEAM] " + role + " " + name + " nhan thong bao:");
        System.out.println("   " + message);
        System.out.println("   [SLACK] Slack notification sent!");
    }
    
    public String getName() {
        return name;
    }
    
    public String getRole() {
        return role;
    }
}