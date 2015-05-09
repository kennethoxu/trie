package com.kox.trie;

import com.kox.stuff.DictionaryAc;
import com.kox.stuff.TrieAc;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Set;

/**
 * Created by Aleesa on 5/8/2015.
 */
public class TrieTest {

    private static DictionaryAc<String> trie;
    private static long baseHeapSize;

    @BeforeClass
    public static void init() {

        baseHeapSize = Runtime.getRuntime().totalMemory();
        trie = new TrieAc<String>();

        int totalWords = 0;
        long start = System.nanoTime();
        totalWords += addFile(trie, "romeo", "Romeo and Juliet" );
        totalWords += addFile(trie, "hamlet", "Hamlet" );
        totalWords += addFile(trie, "unibomber", "Unibomber Manifesto" );
        totalWords += addFile(trie, "jameskingbible", "Biblerino" );
        totalWords += addFile(trie, "warandpeace", "War and Peace" );
        totalWords += addFile(trie, "titanicscreenplay", "Titanic" );
        long durr = System.nanoTime() - start;
        System.out.println("Total words indexed: " + totalWords + " in " + durr / 1000000000.0 + " s");
    }

    @AfterClass
    public static void cleanup() {
        long heapSizeAfter = Runtime.getRuntime().totalMemory();
        System.out.println( "heapSize " + (heapSizeAfter - baseHeapSize)/1000000L + " MB"  );
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
        timedTest("420");
        timedTest("blaze");
        timedTest("it");
        timedTest("yolo");
        timedTest("swag");
    }

    private void timedTest(String searchString) {
        long start = System.nanoTime();
        Set<String> val = trie.getAc(searchString);
        long durr = System.nanoTime() - start;
        System.out.println( searchString + "  -- " + val + " - " + durr / 1000000.0 + " ms");
    }

    private static int addFile( DictionaryAc dictionary, String path, String ref ) {
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
                dictionary.addSuffixes(s, ref);
                count++;
            }
            return count;
        } catch (Exception e ) {
            e.printStackTrace();
            return 0;
        }
    }
}
