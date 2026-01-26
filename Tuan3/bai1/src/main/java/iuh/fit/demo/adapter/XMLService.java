package iuh.fit.demo.adapter;

/**
 * Adaptee class trong Adapter Pattern
 * Legacy service chỉ hỗ trợ XML
 */
public class XMLService {
    
    public String processXML(String xmlData) {
        System.out.println("[XML] XMLService dang xu ly du lieu XML...");
        
        // Simulate XML processing
        String processedData = xmlData.replace("<", "[").replace(">", "]");
        
        System.out.println("[XML] XMLService da xu ly xong!");
        return "Processed: " + processedData;
    }
    
    public String convertToXML(String data) {
        System.out.println("[XML] Chuyen doi du lieu sang XML format...");
        
        // Simple conversion to XML format
        return "<data>" + data + "</data>";
    }
    
    public boolean validateXML(String xmlData) {
        // Simple XML validation
        return xmlData.contains("<") && xmlData.contains(">");
    }
}