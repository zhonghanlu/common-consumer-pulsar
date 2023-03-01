package com.ymxc.service;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@Slf4j
public class QueryPulsarMessage {
    public static void main(String[] args) throws Exception {
        String url = String.format("jdbc:presto://%s", "47.100.91.189:8081");
        Connection connection = DriverManager.getConnection(url, "admin", "admin123123");
        String query = String.format("select * from pulsar");
        log.info("Executing query:()", query);
        ResultSet res = connection.createStatement().executeQuery(query);
        while (res.next()) {
            ResultSetMetaData rsmd = res.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            for (int i = 1; 1 <= columnsNumber; i++) {
                if (i > 1) System.out.print(",");
                String columnValue = res.getString(i);
                System.out.print(columnValue + "" + rsmd.getColumnName(1));
            }

        }
    }
}
