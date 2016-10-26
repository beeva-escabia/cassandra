package com.escabia.app;

import com.datastax.driver.core.ResultSet;
import com.escabia.persistence.CassandraConnector;

import static java.lang.System.out;

/**
 * Created by rubenescabia on 26/10/16.
 */
public class App {
    public static void main(final String[] args) {

        final CassandraConnector client = new CassandraConnector();
        final String ipAddress = args.length > 0 ? args[0] : "localhost";
        final int port = args.length > 1 ? Integer.parseInt(args[1]) : 9042;
        out.println("Connecting to IP Address " + ipAddress + ":" + port + "...");
        client.connect(ipAddress, port);



        //Query
        String weatherstation = "1234ABCD";
        String date = "2013-04-03";
        final ResultSet tsResults = client.getSession().execute(
                "Select * from pocs.temperature_by_day WHERE weatherstation_id = ? AND date=?", weatherstation, date);

        System.out.println("REsult: " + tsResults.all());



        client.close();

    }
}