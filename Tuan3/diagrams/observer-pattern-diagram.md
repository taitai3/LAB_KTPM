# Observer Pattern - Stock Price & Task Status Notification

## Sơ đồ UML

```
                    Subject
                    +------------------+
                    | - observers      |
                    +------------------+
                    | + attach()       |
                    | + detach()       |
                    | + notifyAll()    |
                    +------------------+
                            ^
                            |
                +-----------+-----------+
                |                       |
         +------+------+        +-------+-------+
         |   Stock     |        |     Task      |
         +-------------+        +---------------+
         | - price     |        | - status      |
         | - symbol    |        | - name        |
         +-------------+        +---------------+
         | + setPrice()|        | + setStatus() |
         +-------------+        +---------------+
                                        
                    Observer
                    +------------------+
                    | + update()       |
                    +------------------+
                            ^
                            |
                +-----------+-----------+
                |                       |
         +------+------+        +-------+-------+
         |  Investor   |        | TeamMember    |
         +-------------+        +---------------+
         | - name      |        | - name        |
         +-------------+        +---------------+
         | + update()  |        | + update()    |
         +-------------+        +---------------+
```

## Mô tả:
- **Subject**: Interface cho các đối tượng có thể được quan sát
- **Stock/Task**: Concrete subjects - thông báo khi có thay đổi
- **Observer**: Interface cho các đối tượng quan sát
- **Investor/TeamMember**: Concrete observers - nhận thông báo