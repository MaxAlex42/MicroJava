import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream s = new FileInputStream("");
            Reader r = new InputStreamReader(s);
            Scanner.init(r);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
