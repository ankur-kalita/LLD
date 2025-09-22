public class DefaultFrameCreator implements FrameCreator {
    public Frame createFrame(byte[] data) {
        return new Frame(data);
    }
}
