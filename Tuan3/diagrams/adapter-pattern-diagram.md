# Adapter Pattern - XML to JSON Conversion

## Sơ đồ UML

```
                    Target (JSONService)
                    +------------------+
                    | + processJSON()  |
                    +------------------+
                            ^
                            |
                    +-------+-------+
                    |    Adapter    |
                    +---------------+
                    | - xmlService  |
                    +---------------+
                    | + processJSON()|
                    +---------------+
                            |
                            v
                    Adaptee (XMLService)
                    +------------------+
                    | + processXML()   |
                    +------------------+
                    
                    
    Client Code                 Adapter                    Legacy System
    +-----------+              +---------+                +-------------+
    |           |  JSON        |         |  XML           |             |
    |  Web App  |  --------->  | Adapter |  --------->    | XML Service |
    |           |              |         |                |             |
    +-----------+              +---------+                +-------------+
```

## Mô tả:
- **Target**: Interface mà client mong muốn (JSON)
- **Adapter**: Chuyển đổi từ XML sang JSON
- **Adaptee**: Service hiện tại chỉ hỗ trợ XML
- **Client**: Ứng dụng web cần dữ liệu JSON