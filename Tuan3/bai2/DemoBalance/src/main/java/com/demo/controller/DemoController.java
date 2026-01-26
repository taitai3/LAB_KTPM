package com.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController {

    @Value("${server.port:8080}")
    private String serverPort;

    @GetMapping("/")
    public Map<String, Object> home() throws UnknownHostException {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Load Balanced Spring Boot App!");
        response.put("hostname", InetAddress.getLocalHost().getHostName());
        response.put("port", serverPort);
        response.put("timestamp", LocalDateTime.now());
        response.put("instance", System.getenv().getOrDefault("INSTANCE_NAME", "default"));
        return response;
    }

    @GetMapping("/api/users")
    public Map<String, Object> getUsers() throws UnknownHostException {
        Map<String, Object> response = new HashMap<>();
        response.put("users", new String[]{"John", "Jane", "Bob", "Alice"});
        response.put("served_by", InetAddress.getLocalHost().getHostName());
        response.put("port", serverPort);
        return response;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> status = new HashMap<>();
        status.put("status", "UP");
        status.put("service", "demo-app");
        return status;
    }
}