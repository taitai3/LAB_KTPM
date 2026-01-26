package iuh.fit.demo.adapter;

/**
 * Adapter class trong Adapter Pattern
 * Chuyển đổi giữa XML và JSON
 */
public class XMLToJSONAdapter implements JSONService {
    private XMLService xmlService;
    
    public XMLToJSONAdapter(XMLService xmlService) {
        this.xmlService = xmlService;
    }
    
    @Override
    public String processJSON(String jsonData) {
        System.out.println("[ADAPTER] Adapter nhan JSON data: " + jsonData);
        
        // Convert JSON to XML
        String xmlData = convertJSONToXML(jsonData);
        System.out.println("[ADAPTER] Adapter chuyen doi sang XML: " + xmlData);
        
        // Process using XML service
        String processedXML = xmlService.processXML(xmlData);
        
        // Convert back to JSON
        String resultJSON = convertXMLToJSON(processedXML);
        System.out.println("[ADAPTER] Adapter chuyen doi ket qua ve JSON: " + resultJSON);
        
        return resultJSON;
    }
    
    @Override
    public String convertToJSON(String data) {
        // Convert XML to JSON format
        return convertXMLToJSON(data);
    }
    
    private String convertJSONToXML(String jsonData) {
        // Simple JSON to XML conversion
        String xmlData = jsonData
            .replace("{", "<root>")
            .replace("}", "</root>")
            .replace("\"", "")
            .replace(":", "><![CDATA[")
            .replace(",", "]]><");
        
        return xmlData + "]]>";
    }
    
    private String convertXMLToJSON(String xmlData) {
        // Simple XML to JSON conversion
        String jsonData = xmlData
            .replace("<", "{\"")
            .replace(">", "\":")
            .replace("</", ",\"end_")
            .replace("Processed: [", "{\"processed\":\"")
            .replace("]", "\"}");
        
        return jsonData;
    }
}