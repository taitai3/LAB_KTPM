# Composite Pattern - File System Management

## Sơ đồ UML

```
                    FileSystemComponent
                    +------------------+
                    | - name: String   |
                    | - size: long     |
                    +------------------+
                    | + getName()      |
                    | + getSize()      |
                    | + display()      |
                    | + add()          |
                    | + remove()       |
                    +------------------+
                            ^
                            |
                +-----------+-----------+
                |                       |
         +------+------+        +-------+-------+
         |    File     |        |   Directory   |
         +-------------+        +---------------+
         | - content   |        | - children    |
         +-------------+        +---------------+
         | + display() |        | + display()   |
         | + getSize() |        | + add()       |
         +-------------+        | + remove()    |
                                | + getSize()   |
                                +---------------+
```

## Mô tả:
- **FileSystemComponent**: Abstract class định nghĩa interface chung
- **File**: Leaf node - chỉ chứa dữ liệu
- **Directory**: Composite node - có thể chứa File hoặc Directory khác