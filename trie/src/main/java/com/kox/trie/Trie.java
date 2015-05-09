package com.kox.trie;

import java.util.Set;

/**
 * Created by Aleesa on 5/8/2015.
 *
 * A n-ary tree specifically designed to do fast lookups in specific words and substring(0, x) of those words.
 * T is the type of reference associated with the word. Add and get terms are caseinsensitive.
 *
 *
 */
public interface Trie<T> {

    /**
     * Adds a specific string to the trie, with the reference ref attached to that String
     * Strings are processed at lower case.
     */
    public void add(String val, T ref);

    /**
     * Returns a set of references that point to a particular String. Additionally, also returns words that contain
     * the input string starting from index 0.
     *
     * ie: psychologica will trigger on psychological
     * but sychological will not
     */
    public Set<T> get( String s );
}
