package iuh.fit.demo.observer;

/**
 * Concrete Observer - Investor
 * Nhận thông báo về thay đổi giá cổ phiếu
 */
public class Investor implements Observer {
    private String name;
    private String email;
    
    public Investor(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    @Override
    public void update(String message) {
        System.out.println("[INVESTOR] " + name + " (" + email + ") nhan thong bao:");
        System.out.println("   " + message);
        System.out.println("   [EMAIL] Email notification sent!");
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
}