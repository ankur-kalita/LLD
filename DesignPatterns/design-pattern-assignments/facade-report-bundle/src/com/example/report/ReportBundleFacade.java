package com.example.report;
import java.nio.file.Path;
import java.util.Map;

public class ReportBundleFacade {
    AuditLog log;
    JsonWriter writer;
    Zipper zipper;

    public ReportBundleFacade(AuditLog log, JsonWriter writer, Zipper zipper) {
        this.log = log;
        this.writer = writer;
        this.zipper = zipper;
    }

    public Path export(Map<String,Object> data, Path outDir, String fileName) {
        Path json = writer.write(data, outDir, fileName);
        Path zip = zipper.zip(json, outDir.resolve(fileName + ".zip"));
        log.log("exported " + zip);
        return zip;
    }
}
