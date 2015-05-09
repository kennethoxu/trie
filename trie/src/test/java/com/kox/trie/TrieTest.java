package com.kox.trie;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

/**
 * Created by Aleesa on 5/8/2015.
 */
public class TrieTest {

    private static TrieImpl<String> trieImpl = new TrieImpl<String>();

    @BeforeClass
    public static void init() {
        int totalWords = 0;
        totalWords += addFile(trieImpl, "romeo", "Romeo and Juliet" );
        totalWords += addFile(trieImpl, "hamlet", "Hamlet" );
        totalWords += addFile(trieImpl, "unibomber", "Unibomber Manifesto" );
        System.out.println("Total words indexed: " + totalWords);
    }

    @Test
    public void testHamlet() {
        timedTest("POLONIUS");
    }

    @Test
    public void testRomeo() {
        timedTest("Montague");
    }

    @Test
    public void testUnibomber() {
        timedTest("gay");
    }

    @Test
    public void testAll() {
        timedTest("the");
    }

    private void timedTest(String searchString) {
        long start = System.nanoTime();
        Set<String> val = trieImpl.get(searchString);
        long durr = System.nanoTime() - start;
        System.out.println( searchString + "  -- " + val + " - " + durr / 1000000.0 + " ms");
    }

    private static int addFile( TrieImpl trieImpl, String path, String ref ) {
        try {
            final BufferedReader br = new BufferedReader(new FileReader("assets/" + path));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String[] tokens = sb.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");

            int count = 0;
            for (String s : tokens) {
                trieImpl.add(s, ref);
                count++;
            }
            return count;
        } catch (Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }
}
