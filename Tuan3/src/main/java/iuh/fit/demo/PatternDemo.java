package iuh.fit.demo;

import iuh.fit.demo.composite.*;
import iuh.fit.demo.observer.*;
import iuh.fit.demo.adapter.*;

/**
 * Class demo riêng để test các Design Pattern
 * Chạy class này để xem kết quả và chụp minh chứng
 */
public class PatternDemo {
    
    public static void main(String[] args) {
        System.out.println("*** DESIGN PATTERNS DEMO - TUAN 3 ***");
        System.out.println("Sinh vien: [Ten cua ban]");
        System.out.println("MSSV: [MSSV cua ban]");
        System.out.println("================================================================================");
        
        // Chay tung demo mot cach ro rang
        System.out.println("\n1. COMPOSITE PATTERN DEMO");
        runCompositeDemo();
        
        System.out.println("\n\n2. OBSERVER PATTERN DEMO");
        runObserverDemo();
        
        System.out.println("\n\n3. ADAPTER PATTERN DEMO");
        runAdapterDemo();
        
        System.out.println("\n================================================================================");
        System.out.println("TAT CA DEMO DA HOAN THANH!");
        System.out.println("Ban co the chup man hinh de lam minh chung");
        System.out.println("================================================================================");
    }
    
    public static void runCompositeDemo() {
        System.out.println("*** COMPOSITE PATTERN - Quan ly File System ***");
        System.out.println("--------------------------------------------------");
        
        // Tao cau truc thu muc phuc tap
        Directory root = new Directory("MyComputer");
        
        // Thu muc Documents
        Directory documents = new Directory("Documents");
        documents.add(new File("CV.docx", 1024, "Ho so xin viec"));
        documents.add(new File("Report.pdf", 2048, "Bao cao thang"));
        
        // Thu muc Pictures
        Directory pictures = new Directory("Pictures");
        pictures.add(new File("avatar.jpg", 1536, "Anh dai dien"));
        pictures.add(new File("vacation.png", 3072, "Anh du lich"));
        
        // Thu muc Projects voi sub-folder
        Directory projects = new Directory("Projects");
        Directory webProject = new Directory("WebApp");
        webProject.add(new File("index.html", 512, "<html>...</html>"));
        webProject.add(new File("style.css", 256, "body { margin: 0; }"));
        webProject.add(new File("app.js", 1024, "console.log('Hello');"));
        
        projects.add(webProject);
        projects.add(new File("README.md", 128, "# My Projects"));
        
        // Them tat ca vao root
        root.add(documents);
        root.add(pictures);
        root.add(projects);
        
        // Hien thi cau truc
        root.display(0);
        
        System.out.println("\n*** THONG KE: ***");
        System.out.println("+ Tong dung luong: " + root.getSize() + " bytes");
        System.out.println("+ Composite Pattern cho phep xu ly File va Directory giong nhau");
    }
    
    public static void runObserverDemo() {
        System.out.println("*** OBSERVER PATTERN - He thong thong bao ***");
        System.out.println("--------------------------------------------------");
        
        // DEMO 1: Stock Price Notification
        System.out.println("*** DEMO 1: Thong bao gia co phieu ***");
        Stock appleStock = new Stock("AAPL", 150.00);
        
        Investor investor1 = new Investor("Nguyen Van An", "an@gmail.com");
        Investor investor2 = new Investor("Tran Thi Binh", "binh@gmail.com");
        Investor investor3 = new Investor("Le Van Cuong", "cuong@gmail.com");
        
        // Dang ky observers
        appleStock.attach(investor1);
        appleStock.attach(investor2);
        appleStock.attach(investor3);
        
        // Thay doi gia - tat ca investors se nhan thong bao
        appleStock.setPrice(155.50); // Tang
        appleStock.setPrice(148.75); // Giam
        
        System.out.println("\n------------------------------");
        
        // DEMO 2: Task Status Notification
        System.out.println("*** DEMO 2: Thong bao trang thai task ***");
        Task loginTask = new Task("Implement User Login", "TODO", "Dev Team");
        
        TeamMember projectManager = new TeamMember("Pham Van Duc", "Project Manager");
        TeamMember tester = new TeamMember("Hoang Thi E", "QA Tester");
        TeamMember developer = new TeamMember("Vu Van Phuc", "Senior Developer");
        
        // Dang ky observers
        loginTask.attach(projectManager);
        loginTask.attach(tester);
        loginTask.attach(developer);
        
        // Thay doi trang thai - team se nhan thong bao
        loginTask.setStatus("In Progress");
        loginTask.setStatus("Testing");
        loginTask.setStatus("Done");
        
        System.out.println("\n+ Observer Pattern cho phep thong bao tu dong khi co thay doi");
    }
    
    public static void runAdapterDemo() {
        System.out.println("*** ADAPTER PATTERN - Chuyen doi XML <-> JSON ***");
        System.out.println("--------------------------------------------------");
        
        // Tao legacy XML service
        XMLService legacyXmlService = new XMLService();
        
        // Tao adapter
        JSONService adapter = new XMLToJSONAdapter(legacyXmlService);
        
        // DEMO: Web service can JSON nhung legacy system chi hieu XML
        System.out.println("*** Web Service gui du lieu JSON: ***");
        String jsonRequest = "{\"user\":\"john_doe\",\"action\":\"login\",\"timestamp\":\"2024-01-26\"}";
        System.out.println(">> JSON Request: " + jsonRequest);
        
        System.out.println("\n*** Adapter dang xu ly... ***");
        String jsonResponse = adapter.processJSON(jsonRequest);
        
        System.out.println("\n<< JSON Response: " + jsonResponse);
        
        System.out.println("\n------------------------------");
        System.out.println("*** LOI ICH CUA ADAPTER PATTERN: ***");
        System.out.println("+ Web service co the su dung JSON");
        System.out.println("+ Legacy XML service duoc tai su dung");
        System.out.println("+ Khong can thay doi code hien tai");
        System.out.println("+ De dang tich hop he thong cu va moi");
    }
}