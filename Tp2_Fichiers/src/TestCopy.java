import java.io.*;

public class TestCopy {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("/home/welf9i/Documents/in.txt");
        File outputFile = new File("/home/welf9i/Documents/out.txt");
        FileReader in = new FileReader(inputFile);
        FileWriter out = new FileWriter(outputFile);
        int c;
        while ((c = in.read()) != -1) {
            out.write(c);
        }
        in.close();
        out.close();
    }
}
