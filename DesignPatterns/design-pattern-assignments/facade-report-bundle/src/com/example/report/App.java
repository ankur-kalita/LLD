package com.example.report;
import java.nio.file.*; import java.util.*;

public class App {
    public static void main(String[] args) {
        Map<String,Object> data = Map.of("name", "Quarterly");
        JsonWriter jw = new JsonWriter(); 
        Zipper z = new Zipper(); 
        AuditLog log = new AuditLog();
        Path json = jw.write(data, Path.of("out"), "report");
        Path zip = z.zip(json, Path.of("out", "report.zip"));
        log.log("exported " + zip);
        System.out.println("DONE " + zip);
        // TODO: Replace the above with a single call to ReportBundleFacade.export(...)
        // A Facade is a class that serves as a front-facing interface that masks complex underlying structural code.
        // The Facade pattern improves the readability and usability of a software library by hiding the interaction 
        // of its components behind a single interface. It applies single responsibility principle to separate the
        // interface from the implementation.
        ReportBundleFacade facade = new ReportBundleFacade(log, jw, z);
        Path zip2 = facade.export(data, Path.of("out"), "report2");
        System.out.println("DONE " + zip2);

    }
}
