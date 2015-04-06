package com.kox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;

/**
 * Created by Aleesa on 1/2/2015.
 */
public class TestR {

    public static void main( String[] args ) {
        Trie<String> trie = new Trie<String>();

        addFile( trie, "romeo", "romeo" );
        addFile( trie, "hamlet", "hamlet" );
        trie.print();

        long start = System.nanoTime();
        Set<String> val = trie.get("vile");
        long end = System.nanoTime();
        System.out.print( val + " - " +  (end-start)/1000000.0 );
    }

    private static void addFile( Trie trie, String path, String ref ) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader("src/com/kox/" + path));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String[] tokens = sb.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            for (String s : tokens) {
                trie.add(s, ref);
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

}