public class Frame { 
    byte[] data; 
    
    Frame(byte[] d)
    { 
        this.data=d; 
    }
    byte[] getData() {
        return data;
    } 
}
