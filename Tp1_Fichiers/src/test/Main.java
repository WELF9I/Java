package test;

import java.io.*;
import java.util.Date;
import static java.lang.System.*;

public class Main {
    public static void main(String[] args) throws IOException {
        out.println("Répertoire courant :"+System.getProperty("user.dir"));
        File rep=new File(".");
        out.println("Chemin relatif"+ rep.getPath());
        out.println("Chemin absolut"+ rep.getAbsolutePath());
        out.println("Chemin canonique"+ rep.getCanonicalPath());
        for(File élément:rep.listRoots()){
            out.println("Racine :"+élément);
        }
        for(File élément:rep.listFiles()){
            if(élément.isDirectory()){
                out.print(élément.getName()+"\t");
                if(élément.getName().length()<8);{out.print("\t");
                out.println("<REP>");}
            }
        }

        for(File élément:rep.listFiles()){
            if(élément.isFile()){
                out.print(élément.getName()+"\t");
                if(élément.getName().length()<8);{out.println("\t");}
                out.printf("%tc",new Date(élément.lastModified()));
                out.printf("\t%10d octets\n",élément.length());
            }
        }
}}