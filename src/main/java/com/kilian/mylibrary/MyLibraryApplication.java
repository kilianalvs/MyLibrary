package com.kilian.mylibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class MyLibraryApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyLibraryApplication.class);
        Environment env = app.run(args).getEnvironment();

        displayStartupInfo(env);
    }

    private static void displayStartupInfo(Environment env) {
        String protocol = env.getProperty("server.ssl.enabled", "false").equals("true") ? "https" : "http";
        String serverPort = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        String hostAddress;

        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            hostAddress = "localhost";
        }

        String swaggerUrl = String.format("%s://%s:%s%s/swagger-ui.html", protocol, hostAddress, serverPort, contextPath);
        String apiDocsUrl = String.format("%s://%s:%s%s/v3/api-docs", protocol, hostAddress, serverPort, contextPath);

        System.out.println("\n----------------------------------------------------------");
        System.out.println("Application 'MyLibrary' is running! Access URLs:");
        System.out.println("Local: \t\t" + protocol + "://localhost:" + serverPort + contextPath);
        System.out.println("External: \t" + protocol + "://" + hostAddress + ":" + serverPort + contextPath);
        System.out.println("Swagger UI: \t" + swaggerUrl);
        System.out.println("API Docs: \t" + apiDocsUrl);
        System.out.println("----------------------------------------------------------");
    }
}
