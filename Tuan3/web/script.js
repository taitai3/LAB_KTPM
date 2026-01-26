// Global variables to store pattern instances
let fileSystem = null;
let stock = null;
let task = null;
let investors = [];
let teamMembers = [];

// Tab switching functionality
function showTab(tabName) {
    // Hide all tabs
    const tabs = document.querySelectorAll('.tab-content');
    tabs.forEach(tab => tab.classList.remove('active'));
    
    // Remove active class from all buttons
    const buttons = document.querySelectorAll('.tab-button');
    buttons.forEach(button => button.classList.remove('active'));
    
    // Show selected tab
    document.getElementById(tabName).classList.add('active');
    
    // Add active class to clicked button
    event.target.classList.add('active');
}

// ============================================================================
// COMPOSITE PATTERN IMPLEMENTATION
// ============================================================================

class FileSystemComponent {
    constructor(name) {
        this.name = name;
        this.size = 0;
    }
    
    getName() {
        return this.name;
    }
    
    getSize() {
        return this.size;
    }
    
    display(depth = 0) {
        return '';
    }
}

class File extends FileSystemComponent {
    constructor(name, size, content = '') {
        super(name);
        this.size = size;
        this.content = content;
    }
    
    display(depth = 0) {
        const indent = '  '.repeat(depth);
        return `${indent}📄 ${this.name} (${this.size} bytes)\n`;
    }
}

class Directory extends FileSystemComponent {
    constructor(name) {
        super(name);
        this.children = [];
    }
    
    add(component) {
        this.children.push(component);
    }
    
    remove(component) {
        const index = this.children.indexOf(component);
        if (index > -1) {
            this.children.splice(index, 1);
        }
    }
    
    getSize() {
        return this.children.reduce((total, child) => total + child.getSize(), 0);
    }
    
    display(depth = 0) {
        const indent = '  '.repeat(depth);
        let result = `${indent}📁 ${this.name}/ (${this.getSize()} bytes total)\n`;
        
        this.children.forEach(child => {
            result += child.display(depth + 1);
        });
        
        return result;
    }
    
    getChildCount() {
        return this.children.length;
    }
}

// Composite Pattern Functions
function createFileSystem() {
    fileSystem = new Directory('MyComputer');
    
    // Create Documents folder
    const documents = new Directory('Documents');
    documents.add(new File('CV.docx', 1024, 'Resume file'));
    documents.add(new File('Report.pdf', 2048, 'Monthly report'));
    
    // Create Pictures folder
    const pictures = new Directory('Pictures');
    pictures.add(new File('avatar.jpg', 1536, 'Profile picture'));
    pictures.add(new File('vacation.png', 3072, 'Holiday photos'));
    
    // Create Projects folder
    const projects = new Directory('Projects');
    const webProject = new Directory('WebApp');
    webProject.add(new File('index.html', 512, '<html>...</html>'));
    webProject.add(new File('style.css', 256, 'body { margin: 0; }'));
    webProject.add(new File('app.js', 1024, 'console.log("Hello");'));
    
    projects.add(webProject);
    projects.add(new File('README.md', 128, '# My Projects'));
    
    // Add all to root
    fileSystem.add(documents);
    fileSystem.add(pictures);
    fileSystem.add(projects);
    
    displayFileSystem();
    showNotification('✅ File system created successfully!', 'success');
}

function addFile() {
    if (!fileSystem) {
        showNotification('❌ Please create file system first!', 'error');
        return;
    }
    
    const fileName = prompt('Enter file name:');
    const fileSize = parseInt(prompt('Enter file size (bytes):'));
    
    if (fileName && fileSize) {
        const newFile = new File(fileName, fileSize, 'User created file');
        fileSystem.add(newFile);
        displayFileSystem();
        showNotification(`✅ File "${fileName}" added successfully!`, 'success');
    }
}

function addDirectory() {
    if (!fileSystem) {
        showNotification('❌ Please create file system first!', 'error');
        return;
    }
    
    const dirName = prompt('Enter directory name:');
    
    if (dirName) {
        const newDir = new Directory(dirName);
        fileSystem.add(newDir);
        displayFileSystem();
        showNotification(`✅ Directory "${dirName}" added successfully!`, 'success');
    }
}

function calculateSize() {
    if (!fileSystem) {
        showNotification('❌ Please create file system first!', 'error');
        return;
    }
    
    const totalSize = fileSystem.getSize();
    const childCount = fileSystem.getChildCount();
    
    document.getElementById('file-stats').innerHTML = `
        <div class="highlight">📊 STATISTICS:</div>
        <div>• Total size: <span class="success">${totalSize} bytes</span></div>
        <div>• Number of items: <span class="success">${childCount}</span></div>
        <div>• Composite Pattern allows uniform treatment of File and Directory</div>
    `;
    
    showNotification('📊 Size calculated successfully!', 'success');
}

function displayFileSystem() {
    if (fileSystem) {
        document.getElementById('file-tree').innerHTML = `<pre>${fileSystem.display()}</pre>`;
    }
}

// ============================================================================
// OBSERVER PATTERN IMPLEMENTATION
// ============================================================================

class Subject {
    constructor() {
        this.observers = [];
    }
    
    attach(observer) {
        this.observers.push(observer);
    }
    
    detach(observer) {
        const index = this.observers.indexOf(observer);
        if (index > -1) {
            this.observers.splice(index, 1);
        }
    }
    
    notifyObservers(message) {
        this.observers.forEach(observer => {
            observer.update(message);
        });
    }
}

class Stock extends Subject {
    constructor(symbol, price) {
        super();
        this.symbol = symbol;
        this.price = price;
    }
    
    setPrice(newPrice) {
        const oldPrice = this.price;
        this.price = newPrice;
        
        const changeType = newPrice > oldPrice ? '📈 TĂNG' : '📉 GIẢM';
        const changePercent = ((newPrice - oldPrice) / oldPrice * 100).toFixed(2);
        
        const message = `Cổ phiếu ${this.symbol}: ${changeType} từ ${oldPrice} → ${newPrice} (${changePercent}%)`;
        this.notifyObservers(message);
    }
}

class Task extends Subject {
    constructor(name, status, assignee) {
        super();
        this.name = name;
        this.status = status;
        this.assignee = assignee;
    }
    
    setStatus(newStatus) {
        const oldStatus = this.status;
        this.status = newStatus;
        
        const message = `Task '${this.name}' (Assignee: ${this.assignee}): ${oldStatus} → ${newStatus}`;
        this.notifyObservers(message);
    }
}

class Investor {
    constructor(name, email) {
        this.name = name;
        this.email = email;
    }
    
    update(message) {
        addNotification('stock-notifications', `💰 Investor ${this.name} (${this.email}) nhận thông báo:\n   ${message}\n   📧 Email notification sent!`);
    }
}

class TeamMember {
    constructor(name, role) {
        this.name = name;
        this.role = role;
    }
    
    update(message) {
        addNotification('task-notifications', `👨‍💻 ${this.role} ${this.name} nhận thông báo:\n   ${message}\n   📱 Slack notification sent!`);
    }
}

// Observer Pattern Functions
function updateStockPrice() {
    const symbol = document.getElementById('stock-symbol').value;
    const price = parseFloat(document.getElementById('stock-price').value);
    
    if (!symbol || !price) {
        showNotification('❌ Please enter stock symbol and price!', 'error');
        return;
    }
    
    if (!stock || stock.symbol !== symbol) {
        stock = new Stock(symbol, price);
        // Re-attach existing investors
        investors.forEach(investor => stock.attach(investor));
        showNotification(`📈 Stock ${symbol} created with price ${price}`, 'success');
    } else {
        stock.setPrice(price);
    }
}

function addInvestor() {
    const name = prompt('Enter investor name:');
    const email = prompt('Enter investor email:');
    
    if (name && email) {
        const investor = new Investor(name, email);
        investors.push(investor);
        
        if (stock) {
            stock.attach(investor);
        }
        
        updateInvestorsList();
        showNotification(`👤 Investor ${name} added successfully!`, 'success');
    }
}

function updateTaskStatus() {
    const taskName = document.getElementById('task-name').value;
    const status = document.getElementById('task-status').value;
    
    if (!taskName) {
        showNotification('❌ Please enter task name!', 'error');
        return;
    }
    
    if (!task || task.name !== taskName) {
        task = new Task(taskName, 'TODO', 'Dev Team');
        // Re-attach existing team members
        teamMembers.forEach(member => task.attach(member));
        showNotification(`📋 Task '${taskName}' created`, 'success');
    }
    
    task.setStatus(status);
}

function addTeamMember() {
    const name = prompt('Enter team member name:');
    const role = prompt('Enter role (e.g., Developer, Tester, PM):');
    
    if (name && role) {
        const member = new TeamMember(name, role);
        teamMembers.push(member);
        
        if (task) {
            task.attach(member);
        }
        
        updateTeamMembersList();
        showNotification(`👥 Team member ${name} added successfully!`, 'success');
    }
}

function updateInvestorsList() {
    const listElement = document.getElementById('investors-list');
    listElement.innerHTML = investors.map(investor => 
        `<div class="observer-item">👤 ${investor.name} (${investor.email})</div>`
    ).join('');
}

function updateTeamMembersList() {
    const listElement = document.getElementById('team-members-list');
    listElement.innerHTML = teamMembers.map(member => 
        `<div class="observer-item">👥 ${member.role}: ${member.name}</div>`
    ).join('');
}

function addNotification(elementId, message) {
    const notificationsElement = document.getElementById(elementId);
    const notification = document.createElement('div');
    notification.className = 'notification';
    notification.innerHTML = `<pre>${message}</pre>`;
    notificationsElement.appendChild(notification);
    notificationsElement.scrollTop = notificationsElement.scrollHeight;
}

// ============================================================================
// ADAPTER PATTERN IMPLEMENTATION
// ============================================================================

class XMLService {
    processXML(xmlData) {
        // Simulate XML processing
        const processedData = xmlData.replace(/</g, '[').replace(/>/g, ']');
        return `Processed: ${processedData}`;
    }
    
    convertToXML(data) {
        return `<data>${data}</data>`;
    }
}

class XMLToJSONAdapter {
    constructor(xmlService) {
        this.xmlService = xmlService;
    }
    
    processJSON(jsonData) {
        // Step 1: Show JSON input
        document.getElementById('step1-content').textContent = jsonData;
        
        // Step 2: Convert JSON to XML
        const xmlData = this.convertJSONToXML(jsonData);
        document.getElementById('step2-content').textContent = xmlData;
        
        // Step 3: Process using XML service
        const processedXML = this.xmlService.processXML(xmlData);
        document.getElementById('step3-content').textContent = processedXML;
        
        // Step 4: Convert back to JSON
        const resultJSON = this.convertXMLToJSON(processedXML);
        document.getElementById('step4-content').textContent = resultJSON;
        
        return resultJSON;
    }
    
    convertJSONToXML(jsonData) {
        try {
            const obj = JSON.parse(jsonData);
            let xml = '<root>';
            
            for (const [key, value] of Object.entries(obj)) {
                xml += `<${key}>${value}</${key}>`;
            }
            
            xml += '</root>';
            return xml;
        } catch (e) {
            return '<error>Invalid JSON</error>';
        }
    }
    
    convertXMLToJSON(xmlData) {
        // Simple XML to JSON conversion for demo
        const result = {
            processed: true,
            original_xml: xmlData,
            timestamp: new Date().toISOString()
        };
        
        return JSON.stringify(result, null, 2);
    }
}

// Adapter Pattern Functions
function processJSON() {
    const jsonInput = document.getElementById('json-input').value;
    
    if (!jsonInput.trim()) {
        showNotification('❌ Please enter JSON data!', 'error');
        return;
    }
    
    // Clear previous results
    for (let i = 1; i <= 4; i++) {
        document.getElementById(`step${i}-content`).textContent = '';
    }
    
    // Create services
    const xmlService = new XMLService();
    const adapter = new XMLToJSONAdapter(xmlService);
    
    // Process with delay for visual effect
    setTimeout(() => {
        try {
            adapter.processJSON(jsonInput);
            showNotification('🔄 JSON processed successfully through XML adapter!', 'success');
        } catch (error) {
            showNotification('❌ Error processing JSON: ' + error.message, 'error');
        }
    }, 500);
}

// ============================================================================
// UTILITY FUNCTIONS
// ============================================================================

function showNotification(message, type = 'info') {
    // Create notification element
    const notification = document.createElement('div');
    notification.style.cssText = `
        position: fixed;
        top: 20px;
        right: 20px;
        padding: 15px 20px;
        border-radius: 8px;
        color: white;
        font-weight: bold;
        z-index: 1000;
        max-width: 300px;
        word-wrap: break-word;
    `;
    
    // Set color based on type
    switch (type) {
        case 'success':
            notification.style.background = '#48bb78';
            break;
        case 'error':
            notification.style.background = '#f56565';
            break;
        default:
            notification.style.background = '#4299e1';
    }
    
    notification.textContent = message;
    document.body.appendChild(notification);
    
    // Remove after 3 seconds
    setTimeout(() => {
        if (notification.parentNode) {
            notification.parentNode.removeChild(notification);
        }
    }, 3000);
}

// Initialize the application
document.addEventListener('DOMContentLoaded', function() {
    // Create initial file system
    createFileSystem();
    
    // Add some sample investors and team members
    investors.push(new Investor('Nguyễn Văn A', 'nva@email.com'));
    investors.push(new Investor('Trần Thị B', 'ttb@email.com'));
    updateInvestorsList();
    
    teamMembers.push(new TeamMember('Lê Văn C', 'Project Manager'));
    teamMembers.push(new TeamMember('Phạm Thị D', 'Developer'));
    updateTeamMembersList();
    
    showNotification('🎉 Design Patterns Demo loaded successfully!', 'success');
});