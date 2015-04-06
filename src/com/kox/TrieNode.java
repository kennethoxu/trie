package com.kox;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleesa on 4/6/2015.
 */
public class TrieNode<T> {

    private final Character value;
    private List<TrieNode> children = new ArrayList<TrieNode>();
    private List<T> references;

    public TrieNode( Character c ) {
        this.value = c;
    }

    public void addChild( TrieNode node ) {
        this.children.add( node );
    }

    public void addReference( T ref ) {
        if( references == null ) {
            references = new ArrayList<T>();
        }
        references.add( ref );
    }

    public Character getValue() {
        return value;
    }

    public List<TrieNode> getChildren() {
        return children;
    }

    public List<T> getReferences() {
        return references;
    }
}
