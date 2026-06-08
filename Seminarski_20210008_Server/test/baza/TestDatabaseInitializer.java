/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;

/**
 *
 * @author N
 */
class TestDatabaseInitializer {
    
    private static boolean initialized = false;

    public static void init(Connection conn) throws Exception {
        if (initialized) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("../memorijska_baza.sql"))) {

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }
            for (String statement : sql.toString().split(";")) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    conn.createStatement().execute(trimmed);
                }
            }
        }
        
        initialized = true;
    }
}
