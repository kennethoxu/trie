package com.kox.trie;

import com.kox.stuff.Dictionary2;
import com.kox.stuff.Trie;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

/**
 * Created by Aleesa on 5/8/2015.
 */
public class TrieTest {

    private static Dictionary2<String> trie = new Trie<String>();

    @BeforeClass
    public static void init() {
        int totalWords = 0;
        totalWords += addFile(trie, "romeo", "Romeo and Juliet" );
        totalWords += addFile(trie, "hamlet", "Hamlet" );
        totalWords += addFile(trie, "unibomber", "Unibomber Manifesto" );
        totalWords += addFile(trie, "jameskingbible", "Biblerino" );
        totalWords += addFile(trie, "warandpeace", "War and Peace" );
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
        timedTest("of");
    }

    @Test
    public void testSnoopDog() {
        timedTest("smoke");
        timedTest("weed");
        timedTest("every");
        timedTest("day");
    }

    private void timedTest(String searchString) {
        long start = System.nanoTime();
        Set<String> val = trie.getAc(searchString);
        long durr = System.nanoTime() - start;
        System.out.println( searchString + "  -- " + val + " - " + durr / 1000000.0 + " ms");
    }

    private static int addFile( Dictionary2 dictionary, String path, String ref ) {
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
                dictionary.add(s, ref);
                count++;
            }
            return count;
        } catch (Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }
}
