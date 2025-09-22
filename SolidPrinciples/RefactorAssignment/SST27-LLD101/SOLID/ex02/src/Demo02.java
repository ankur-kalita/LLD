public class Demo02 {
    public static void main(String[] args) {
        FrameCreator frameCreator = new DefaultFrameCreator();
        FrameCache frameCache = new SimpleFrameCache();
        MediaPlayer player = new Player(frameCreator, frameCache);
        player.play(new byte[]{1,2,3,4});
    }
}
