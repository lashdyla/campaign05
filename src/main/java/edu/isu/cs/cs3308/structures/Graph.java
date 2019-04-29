package edu.isu.cs.cs3308.structures;

import edu.isu.cs.cs3308.structures.impl.Edge;

import java.util.Iterator;

/**
 * Interface - Graph operations
 *
 * @author Isaac Griffith
 * @author Dylan Lasher
 */
public interface Graph<V, E>
{

    public int numVertices();

    public int numEdges();

    public V insertVertex(V v);

    public Iterator<V> vertices();

    public Iterator<Edge<V, E>> edges();

    public Edge<V, E> getEdge(V v, V u);

    public V[] endVertices(Edge<V, E> e);

    public V opposite(V v, Edge<V, E> e);

    public int outDegree(V v);

    public int inDegree(V v);

    public Iterator<Edge<V, E>> outgoingEdges(V v);

    public Iterator<Edge<V, E>> incomingEdges(V v);

    public void insertEdge(V v, V u, E e);

    public void removeEdge(Edge<V, E> e);

    public void removeVertex(V v);
}
