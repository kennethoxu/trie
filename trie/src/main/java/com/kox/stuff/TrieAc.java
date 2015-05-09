package com.kox.stuff;

import java.util.*;

/**
 * Created by Aleesa on 4/6/2015.
 */
public class TrieAc<T> implements DictionaryAc<T> {

    /**
     * Reserved char for the root that has no meaning
     */
    private static final char ROOT_CHAR = '$';
    private final RefNode root;

    public TrieAc() {
        root = new RefNode(ROOT_CHAR);
    }

    public void add(String val, T ref) {
        if( val == null || val.isEmpty() ) {
            return;
        }

        RefNode currNode = root;
        for( char c : val.toCharArray() ) {
            List<RefNode> nextChildren = currNode.getChildren();

            RefNode foundNode = null;
            for( RefNode nextNode : nextChildren ) {
                if( nextNode.getValue() == c ) {
                    foundNode = nextNode;
                    break;
                }
            }

            if( foundNode != null ) {
                currNode = foundNode;
            } else {
                RefNode<T> newChild = new RefNode<T>(c);
                currNode.addChild( newChild );
                currNode = newChild;
            }
        }

        currNode.addReference( ref );
    }

    public void addSubStrings(String val, T ref) {
        for( int i = 1; i<val.length(); i++ ) {
            add( val.substring(i), ref );
        }
    }

    public Set<T> getAc(String s) {
        if( s == null || s.isEmpty() ) {
            return null;
        }

        s = s.toLowerCase(Locale.getDefault());

        boolean found = true;
        RefNode currNode = root;
        for( char c : s.toCharArray()) {
            List<RefNode> nextChildren = currNode.getChildren();
            RefNode foundNode = null;
            for( RefNode nextNode : nextChildren ) {
                if( nextNode.getValue() == c ) {
                    foundNode = nextNode;
                    break;
                }
            }

            if( foundNode != null ) {
                currNode = foundNode;
            } else {
                found = false;
                break;
            }
        }

        if( found ) {
            if( currNode.getReferences() == null || currNode.getReferences().isEmpty() ) {
                return getAllChildReferences(currNode);
            } else {
                return new HashSet<T>(currNode.getReferences());
            }
        } else {
            return null;
        }
    }

    private Set<T> getAllChildReferences( RefNode node ) {
        return getAllChildReferencesHelper( new HashSet<T>(), node );
    }

    private Set<T> getAllChildReferencesHelper( Set<T> retList, RefNode node ) {
        if( node == null ) {
            return retList;
        }

        if( node.getReferences() != null ) {
            retList.addAll((List<T>) node.getReferences());
        }
        for( RefNode child : (List<RefNode>)node.getChildren() ) {
            getAllChildReferencesHelper(retList, child );
        }
        return retList;
    }

    public void print() {

        int tillNextLevel = 1;
        Queue<RefNode> arrayDequeue = new LinkedList<RefNode>();
        arrayDequeue.add(root);

        while( !arrayDequeue.isEmpty()) {
            RefNode currNode = arrayDequeue.poll();

            System.out.print( currNode.getValue() );

            for( RefNode childNode : (List<RefNode>)currNode.getChildren() ) {
                arrayDequeue.add(childNode);
            }

            tillNextLevel--;
            if( tillNextLevel == 0 ) {
                System.out.println("");
                tillNextLevel = arrayDequeue.size();
            }

        }
    }

    @Override
    public void remove(String val, T ref) {
        //we dont do this shit
    }

    @Override
    public void remove(String val) {
        //we dont do this shit
    }
}
