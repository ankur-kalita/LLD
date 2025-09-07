package com.example.video;
import java.nio.file.Path;

public class VideoPipelineFacade {
    Decoder decoder;
    FilterEngine filter;
    Encoder encoder;
    SharpenAdapter sharpenAdapter;


    public VideoPipelineFacade(Decoder decoder, FilterEngine filter, Encoder encoder, SharpenAdapter sharpenAdapter) {
        this.decoder = decoder;
        this.filter = filter;
        this.encoder = encoder;
        this.sharpenAdapter = sharpenAdapter;
    }

    public Path process(Path src, Path out, boolean gray, Double scale, Integer sharpenStrength) {
        Frame[] frames = decoder.decode(src);
        if (gray) {
            frames = filter.grayscale(frames);
        }
        if (scale != null) {
            frames = filter.scale(frames, scale);
        }
        if (sharpenStrength != null) {
            frames = sharpenAdapter.sharpen(frames, sharpenStrength);
        }
        return encoder.encode(frames, out);
    }


}
