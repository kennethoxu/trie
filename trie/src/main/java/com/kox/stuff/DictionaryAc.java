package com.kox.stuff;

import java.util.Set;

/**
 * Created by Aleesa on 5/8/2015.
 *
 * My take an n-ary tree specifically designed to do fast lookups in specific words and substring(0, x) of those words.
 * T is the type of reference associated with the word. Ac stands for autocomplete. This was designed for the
 * purpose for autocompletion in phones with commodity hardware to do fast asnychronous lookups on multi-variabled
 * contact information (name, phone, email.. etc) for the purpose of autocompletion.
 *
 *
 */
public interface DictionaryAc<T> {

    /**
     * Adds a specific string to the trie, with the reference ref attached to that String
     * Strings are processed at lower case.
     */
    public void add( String val, T ref );

    /**
     * Adds a specific string and all suffix variations of that string attached to the reference.
     */
    public void addSuffixes( String val, T ref);

    /**
     * Returns a set of references that point to a particular String. Additionally, also returns words that contain
     * the input string starting from index 0.
     *
     * ie: psychologica will trigger on psychological
     * but sychological will not
     */
    public Set<T> getAc(String val);

    /**
     * Removes a specific reference from a String
     */
    public void remove( String val, T ref );

    /**
     * Removes all references from a String
     */
    public void remove( String val );

}
