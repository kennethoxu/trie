package com.kox.stuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleesa on 4/6/2015.
 */
public class RefNode<T> {

    private final char value;
    private final List<RefNode> children = new ArrayList<RefNode>();
    private List<T> references;

    public RefNode(char c) {
        this.value = c;
    }

    public void addChild( RefNode node ) {
        this.children.add( node );
    }

    public void addReference( T ref ) {
        if( references == null ) {
            references = new ArrayList<T>();
        }
        references.add( ref );
    }

    public char getValue() {
        return value;
    }

    public List<RefNode> getChildren() {
        return children;
    }

    public List<T> getReferences() {
        return references;
    }
}
