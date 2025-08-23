public class Player {
    private Frame last;
    void play(byte[] fileBytes){
        Frame f = new Frame(fileBytes); 
        last = f;
        System.out.println("\u25B6 Playing " + fileBytes.length + " bytes");
        System.out.println("Cached last frame? " + (last!=null));
    }
}