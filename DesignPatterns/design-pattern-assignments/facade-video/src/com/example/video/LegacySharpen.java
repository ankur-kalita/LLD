package com.example.video;

/** Odd legacy API expecting a 'handle' string; returns a new handle string. */
public class LegacySharpen {
    public String applySharpen(String framesHandle, int strength) { 
        return "HANDLE:" + strength; 
    }
    // Legacy api refers to an old outdated interface or system that is still in use
    // but may not be compatible with modern systems or technologies.
    // So we use the adapter to make a bridge between the old and the new code.
}
