package file;


import java.io.*;
import java.util.Date;
import static java.lang.System.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        File rep = new File(".");
        System.out.println("Non filtré----------------------------------");
        for (File élément : rep.listFiles()){
            out.print(élément.getName()+"\t");
            if(élément.getName().length()<8){
                out.print("\t");
            }
            if(élément.isDirectory()){
                out.print("<REP>");
            }
            out.printf("\t%tc",new Date(élément.lastModified()));
            out.printf("\t%5d octets\n",élément.length());
        }
        out.println("Filtré---------------------------------------------");
        for (File élément : rep.listFiles(new Filtre('b'))){
            out.print(élément.getName()+"\t");
            if(élément.getName().length()<8){
                out.print("\t");
            }
            if(élément.isDirectory()){
                out.print("<REP>");
            }
            out.printf("\t%tc",new Date(élément.lastModified()));
            out.printf("\t%5d octets\n",élément.length());
        }
    }
}
