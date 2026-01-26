package iuh.fit.demo.adapter;

/**
 * Target interface trong Adapter Pattern
 * Định nghĩa interface mà client mong muốn (JSON)
 */
public interface JSONService {
    String processJSON(String jsonData);
    String convertToJSON(String data);
}