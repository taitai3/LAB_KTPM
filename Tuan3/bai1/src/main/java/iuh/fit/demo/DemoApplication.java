package iuh.fit.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import iuh.fit.demo.composite.*;
import iuh.fit.demo.observer.*;
import iuh.fit.demo.adapter.*;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        
        System.out.println("=".repeat(80));
        System.out.println("🎯 DEMO DESIGN PATTERNS - TUẦN 3");
        System.out.println("=".repeat(80));
        
        // Demo Composite Pattern
        demoCompositePattern();
        
        System.out.println("\n" + "=".repeat(80));
        
        // Demo Observer Pattern
        demoObserverPattern();
        
        System.out.println("\n" + "=".repeat(80));
        
        // Demo Adapter Pattern
        demoAdapterPattern();
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("✅ DEMO HOÀN THÀNH!");
        System.out.println("=".repeat(80));
    }
    
    private static void demoCompositePattern() {
        System.out.println("📁 COMPOSITE PATTERN - HỆ THỐNG QUẢN LÝ THỦ MỤC VÀ TẬP TIN");
        System.out.println("-".repeat(60));
        
        // Tạo root directory
        Directory root = new Directory("root");
        
        // Tạo các file
        File file1 = new File("document.txt", 1024, "Nội dung tài liệu quan trọng");
        File file2 = new File("image.jpg", 2048, "Hình ảnh minh họa");
        
        // Tạo thư mục con
        Directory documents = new Directory("Documents");
        Directory images = new Directory("Images");
        Directory projects = new Directory("Projects");
        
        // Thêm file vào thư mục
        documents.add(file1);
        documents.add(new File("readme.md", 512, "Hướng dẫn sử dụng"));
        
        images.add(file2);
        images.add(new File("logo.png", 1536, "Logo công ty"));
        
        // Tạo project structure
        Directory projectA = new Directory("ProjectA");
        projectA.add(new File("main.java", 2048, "public class Main {}"));
        projectA.add(new File("config.properties", 256, "server.port=8080"));
        
        projects.add(projectA);
        
        // Thêm tất cả vào root
        root.add(documents);
        root.add(images);
        root.add(projects);
        
        // Hiển thị cấu trúc
        System.out.println("Cấu trúc thư mục:");
        root.display(0);
        
        System.out.println("\n📊 Thống kê:");
        System.out.println("- Tổng dung lượng: " + root.getSize() + " bytes");
        System.out.println("- Số thư mục con: " + root.getChildCount());
    }
    
    private static void demoObserverPattern() {
        System.out.println("👁️ OBSERVER PATTERN - HỆ THỐNG THÔNG BÁO");
        System.out.println("-".repeat(60));
        
        // Demo Stock Price Notification
        System.out.println("📈 DEMO: Thông báo giá cổ phiếu");
        Stock vn30 = new Stock("VN30", 1000.0);
        
        Investor investor1 = new Investor("Nguyễn Văn A", "nva@email.com");
        Investor investor2 = new Investor("Trần Thị B", "ttb@email.com");
        
        vn30.attach(investor1);
        vn30.attach(investor2);
        
        vn30.setPrice(1050.0); // Tăng giá
        vn30.setPrice(980.0);  // Giảm giá
        
        System.out.println("\n" + "-".repeat(40));
        
        // Demo Task Status Notification
        System.out.println("📋 DEMO: Thông báo trạng thái công việc");
        Task task = new Task("Phát triển tính năng login", "TODO", "Developer A");
        
        TeamMember pm = new TeamMember("Lê Văn C", "Project Manager");
        TeamMember tester = new TeamMember("Phạm Thị D", "Tester");
        TeamMember dev = new TeamMember("Hoàng Văn E", "Developer");
        
        task.attach(pm);
        task.attach(tester);
        task.attach(dev);
        
        task.setStatus("In Progress");
        task.setStatus("Testing");
        task.setStatus("Done");
    }
    
    private static void demoAdapterPattern() {
        System.out.println("🔄 ADAPTER PATTERN - CHUYỂN ĐỔI XML SANG JSON");
        System.out.println("-".repeat(60));
        
        // Legacy XML Service
        XMLService xmlService = new XMLService();
        
        // Adapter để chuyển đổi
        JSONService jsonAdapter = new XMLToJSONAdapter(xmlService);
        
        // Client code sử dụng JSON
        String jsonData = "{\"name\":\"John\",\"age\":30,\"city\":\"Hanoi\"}";
        
        System.out.println("📥 Input JSON data: " + jsonData);
        System.out.println();
        
        // Process thông qua adapter
        String result = jsonAdapter.processJSON(jsonData);
        
        System.out.println();
        System.out.println("📤 Final result: " + result);
        
        System.out.println("\n💡 Lợi ích của Adapter Pattern:");
        System.out.println("- Client có thể sử dụng JSON mà không cần biết về XML");
        System.out.println("- Legacy XMLService được tái sử dụng");
        System.out.println("- Không cần thay đổi code hiện tại");
    }
}