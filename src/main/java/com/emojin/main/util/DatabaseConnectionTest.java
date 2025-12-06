package com.emojin.main.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * Database Connection Tester
 * This will run on application startup and verify database connection
 * Remove or comment out this component after verifying connection works
 */
@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("✅ DATABASE CONNECTION SUCCESSFUL!");
            System.out.println("📊 Database: " + connection.getCatalog());
            System.out.println("🔗 URL: " + connection.getMetaData().getURL());
            System.out.println("👤 User: " + connection.getMetaData().getUserName());
            System.out.println("✨ Database is ready to use!");
        } catch (Exception e) {
            System.err.println("❌ DATABASE CONNECTION FAILED!");
            System.err.println("Error: " + e.getMessage());
            System.err.println("\n💡 Tips:");
            System.err.println("1. Check your database credentials in application-[profile].properties");
            System.err.println("2. Ensure database server is running");
            System.err.println("3. Verify network connectivity to database host");
            System.err.println("4. Check if database name exists");
            throw e;
        }
    }
}
