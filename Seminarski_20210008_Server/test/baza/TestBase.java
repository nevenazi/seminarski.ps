/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.Connection;
import org.junit.BeforeClass;
import repository.db.DbConnectionFactory;

/**
 *
 * @author N
 */
public class TestBase {
    protected static boolean initialized = false;

    @BeforeClass
    public static void init() throws Exception {
        Connection conn = DbConnectionFactory.getInstance().getConnection();

        if (!initialized) {
            TestDatabaseInitializer.init(conn);
            initialized = true;
        }
    }
}
