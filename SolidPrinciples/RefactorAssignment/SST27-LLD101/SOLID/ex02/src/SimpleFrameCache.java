public class SimpleFrameCache implements FrameCache {
    private Frame lastFrame;

    public void cacheFrame(Frame frame) {
        this.lastFrame = frame;
    }
    public Frame getLastFrame() {
        return lastFrame;
    }

}
