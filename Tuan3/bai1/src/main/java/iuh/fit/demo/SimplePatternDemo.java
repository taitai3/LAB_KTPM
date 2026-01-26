package iuh.fit.demo;

import iuh.fit.demo.composite.*;
import iuh.fit.demo.observer.*;
import iuh.fit.demo.adapter.*;

/**
 * Simple Demo class without Unicode characters
 * Run this class to see all Design Patterns in action
 */
public class SimplePatternDemo {
    
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("                    DESIGN PATTERNS DEMO - TUAN 3");
        System.out.println("                    Sinh vien: [Nguyen tan tai]");
        System.out.println("                    MSSV: [22669451]");
        System.out.println("================================================================================");
        
        System.out.println("\n1. COMPOSITE PATTERN DEMO");
        System.out.println("--------------------------------------------------------------------------------");
        runCompositeDemo();
        
        System.out.println("\n\n2. OBSERVER PATTERN DEMO");
        System.out.println("--------------------------------------------------------------------------------");
        runObserverDemo();
        
        System.out.println("\n\n3. ADAPTER PATTERN DEMO");
        System.out.println("--------------------------------------------------------------------------------");
        runAdapterDemo();
        
    }
    
    public static void runCompositeDemo() {
        System.out.println("COMPOSITE PATTERN - File System Management");
        System.out.println("----------------------------------------------------------");
        
        // Create root directory
        Directory root = new Directory("MyComputer");
        
        // Create Documents folder
        Directory documents = new Directory("Documents");
        documents.add(new File("CV.docx", 1024, "Resume file"));
        documents.add(new File("Report.pdf", 2048, "Monthly report"));
        
        // Create Pictures folder
        Directory pictures = new Directory("Pictures");
        pictures.add(new File("avatar.jpg", 1536, "Profile picture"));
        pictures.add(new File("vacation.png", 3072, "Holiday photos"));
        
        // Create Projects folder with sub-folder
        Directory projects = new Directory("Projects");
        Directory webProject = new Directory("WebApp");
        webProject.add(new File("index.html", 512, "<html>...</html>"));
        webProject.add(new File("style.css", 256, "body { margin: 0; }"));
        webProject.add(new File("app.js", 1024, "console.log('Hello World');"));
        
        projects.add(webProject);
        projects.add(new File("README.md", 128, "# My Projects"));
        
        // Add all to root
        root.add(documents);
        root.add(pictures);
        root.add(projects);
        
        // Display structure
        System.out.println("\nFile System Structure:");
        root.display(0);
        
        System.out.println("\nSTATISTICS:");
        System.out.println("+ Total size: " + root.getSize() + " bytes");
        System.out.println("+ Composite Pattern allows uniform treatment of File and Directory");
    }
    
    public static void runObserverDemo() {
        System.out.println("OBSERVER PATTERN - Notification System");
        System.out.println("----------------------------------------------------------");
        
        // DEMO 1: Stock Price Notification
        System.out.println("DEMO 1: Stock Price Notification");
        Stock appleStock = new Stock("AAPL", 150.00);
        
        Investor investor1 = new Investor("Nguyen Van An", "an@gmail.com");
        Investor investor2 = new Investor("Tran Thi Binh", "binh@gmail.com");
        Investor investor3 = new Investor("Le Van Cuong", "cuong@gmail.com");
        
        // Register observers
        appleStock.attach(investor1);
        appleStock.attach(investor2);
        appleStock.attach(investor3);
        
        // Change price - all investors will be notified
        appleStock.setPrice(155.50); // Increase
        appleStock.setPrice(148.75); // Decrease
        
        System.out.println("\n----------------------------------------------------------");
        
        // DEMO 2: Task Status Notification
        System.out.println("DEMO 2: Task Status Notification");
        Task loginTask = new Task("Implement User Login", "TODO", "Dev Team");
        
        TeamMember projectManager = new TeamMember("Pham Van Duc", "Project Manager");
        TeamMember tester = new TeamMember("Hoang Thi E", "QA Tester");
        TeamMember developer = new TeamMember("Vu Van Phuc", "Senior Developer");
        
        // Register observers
        loginTask.attach(projectManager);
        loginTask.attach(tester);
        loginTask.attach(developer);
        
        // Change status - team will be notified
        loginTask.setStatus("In Progress");
        loginTask.setStatus("Testing");
        loginTask.setStatus("Done");
        
        System.out.println("\n+ Observer Pattern enables automatic notification on changes");
    }
    
    public static void runAdapterDemo() {
        System.out.println("ADAPTER PATTERN - XML to JSON Conversion");
        System.out.println("----------------------------------------------------------");
        
        // Create legacy XML service
        XMLService legacyXmlService = new XMLService();
        
        // Create adapter
        JSONService adapter = new XMLToJSONAdapter(legacyXmlService);
        
        // DEMO: Web service needs JSON but legacy system only understands XML
        System.out.println("Web Service sends JSON data:");
        String jsonRequest = "{\"user\":\"john_doe\",\"action\":\"login\",\"timestamp\":\"2024-01-26\"}";
        System.out.println(">> JSON Request: " + jsonRequest);
        
        System.out.println("\nAdapter is processing...");
        String jsonResponse = adapter.processJSON(jsonRequest);
        
        System.out.println("\n<< JSON Response: " + jsonResponse);
        
        System.out.println("\n----------------------------------------------------------");
        System.out.println("BENEFITS OF ADAPTER PATTERN:");
        System.out.println("+ Web service can use JSON interface");
        System.out.println("+ Legacy XML service is reused");
        System.out.println("+ No need to change existing code");
        System.out.println("+ Easy integration between old and new systems");
    }
}