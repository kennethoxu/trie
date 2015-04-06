package com.kox;

import java.util.*;

/**
 * Created by Aleesa on 4/6/2015.
 */
public class Trie<T> {

    private TrieNode root;

    public Trie() {
        root = new TrieNode(null);
    }

    public void add(String val, T ref) {
        if( val == null || val.isEmpty() ) {
            return;
        }

        TrieNode currNode = root;
        for( Character c : val.toCharArray() ) {
            List<TrieNode> nextChildren = currNode.getChildren();

            TrieNode foundNode = null;
            for( TrieNode nextNode : nextChildren ) {
                if( nextNode.getValue().equals(c) ) {
                    foundNode = nextNode;
                    break;
                }
            }

            if( foundNode != null ) {
                currNode = foundNode;
            } else {
                TrieNode<T> newChild = new TrieNode<T>(c);
                currNode.addChild( newChild );
                currNode = newChild;
            }
        }

        currNode.addReference( ref );
    }

    public Set<T> get( String s ) {
        if( s == null || s.isEmpty() ) {
            return null;
        }

        boolean found = true;

        TrieNode currNode = root;
        for( Character c : s.toCharArray()) {
            List<TrieNode> nextChildren = currNode.getChildren();
            TrieNode foundNode = null;
            for( TrieNode nextNode : nextChildren ) {
                if( nextNode.getValue().equals(c) ) {
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

    private Set<T> getAllChildReferences( TrieNode node ) {
        return getAllChildReferencesHelper( new HashSet<T>(), node );
    }

    private Set<T> getAllChildReferencesHelper( Set<T> retList, TrieNode node ) {
        if( node == null ) {
            return retList;
        }

        if( node.getReferences() != null ) {
            retList.addAll((List<T>) node.getReferences());
        }
        for( TrieNode child : (List<TrieNode>)node.getChildren() ) {
            getAllChildReferencesHelper(retList, child );
        }
        return retList;
    }

    public void print() {

        int tillNextLevel = 1;
        Queue<TrieNode> arrayDequeue = new LinkedList<TrieNode>();
        arrayDequeue.add(root);

        while( !arrayDequeue.isEmpty()) {
            TrieNode currNode = arrayDequeue.poll();

            System.out.print( currNode.getValue() );

            for( TrieNode childNode : (List<TrieNode>)currNode.getChildren() ) {
                arrayDequeue.add(childNode);
            }

            tillNextLevel--;
            if( tillNextLevel == 0 ) {
                System.out.println("");
                tillNextLevel = arrayDequeue.size();
            }

        }
    }
}
