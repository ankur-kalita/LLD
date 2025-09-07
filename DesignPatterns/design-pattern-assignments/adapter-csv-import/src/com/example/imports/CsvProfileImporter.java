import java.nio.file.Path;

public class CsvProfileImporter implements ProfileImporter {
    private final NaiveCsvReader reader;
    private final ProfileService service;

    public CsvProfileImporter(NaiveCsvReader reader, ProfileService service) {
        this.reader = reader;
        this.service = service;
    }

    @Override
    public int importFrom(Path csvFile) {
        int count = 0;
        for (String[] row : reader.read(csvFile)) {
            if (row.length < 3) continue; // skip invalid rows
            String id = row[0].trim();
            String email = row[1].trim();
            String displayName = row[2].trim();
            try {
                service.createProfile(id, email, displayName);
                count++;
            } catch (Exception e) {
                System.err.println("Failed to create profile for row: " + String.join(",", row) + " due to " + e.getMessage());
            }
        }
        return count;
    }
}
