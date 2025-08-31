import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * FAULTY "Singleton": public constructor, getInstance() returns a NEW instance each time,
 * not thread-safe, reload allowed anytime, mutable global state, reflection+serialization-friendly.
 */
public class AppSettings implements Serializable {
    // private static final long serialVersionUID = 1L;
    // When you mark a class Serializable, Java assigns it a hidden unique ID (serialVersionUID). 
    // This ID is used during serialization/deserialization to check class compatibility.

    private final Properties props = new Properties();

    //reflection in java lets you inspect and manipulate classes at runtime even if access modifiers are private...
    private AppSettings() { 
        if (uniqueInstance != null) {
            throw new IllegalStateException("Already initialized.");
        }
    } // should not be public for true singleton // reflection can be used to break singleton

    private static volatile AppSettings uniqueInstance;

    public static AppSettings getInstance() {
        if(uniqueInstance == null) {
            synchronized (AppSettings.class) { // Is the class object representing my class used here as the synchronization lock
                if(uniqueInstance == null) {
                    uniqueInstance = new AppSettings();
                }
            }
        }
        return uniqueInstance; 
    }

    public synchronized void loadFromFile(Path file) {
        try (InputStream in = Files.newInputStream(file)) {
            props.clear();
            props.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public synchronized String get(String key) {
        return props.getProperty(key);
    }

    // private Object readResolve() {
    //     return getInstance(); // help against deseriliazation
    // }
    // Because by default, Java creates a new object during deserialization, bypassing getInstance().
    // Serialization → converting an object into a byte stream (so you can save it to a file, send it over a network, etc.).
    // Deserialization → converting that byte stream back into a live object in memory.
}
