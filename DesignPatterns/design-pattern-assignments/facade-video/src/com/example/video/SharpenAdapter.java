package com.example.video;

import java.util.Arrays;

public class SharpenAdapter {
    private final LegacySharpen legacySharpen;

    public SharpenAdapter(LegacySharpen legacySharpen) {
        this.legacySharpen = legacySharpen;
    }

     // Simulate: convert frames to a handle, call legacy, then back to frames
    public Frame[] sharpen(Frame[] frames, int strength) {
        String handle = "FRAMES:" + frames.length;
        String resultHandle = legacySharpen.applySharpen(handle, strength);
        return Arrays.copyOf(frames, frames.length);
    }
}
