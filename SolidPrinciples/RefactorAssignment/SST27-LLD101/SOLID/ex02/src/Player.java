public class Player implements MediaPlayer{
    private FrameCreator creator;
    private FrameCache cache;

    public Player(FrameCreator creator, FrameCache cache) {
        this.creator = creator;
        this.cache = cache;
    }

    public void play(byte[] fileBytes){
        Frame frame = creator.createFrame(fileBytes);
        cache.cacheFrame(frame);
        Frame last = cache.getLastFrame();
        System.out.println("\u25B6 Playing " + fileBytes.length + " bytes");
        System.out.println("Cached last frame? " + (last!=null));
    }
}