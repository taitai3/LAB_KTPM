package iuh.fit.demo.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject abstract class trong Observer Pattern
 * Quản lý danh sách observers và thông báo cho họ
 */
public abstract class Subject {
    private List<Observer> observers;
    
    public Subject() {
        this.observers = new ArrayList<>();
    }
    
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("Observer da duoc dang ky thanh cong!");
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer da duoc huy dang ky!");
    }
    
    protected void notifyObservers(String message) {
        System.out.println("\n[NOTIFICATION] Dang gui thong bao den " + observers.size() + " observer(s)...");
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    public int getObserverCount() {
        return observers.size();
    }
}