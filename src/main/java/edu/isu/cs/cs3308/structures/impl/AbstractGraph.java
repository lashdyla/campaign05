package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Graph;

import java.util.*;

/**
 * @author Isaac Griffith
 * @author Dylan Lasher
 */
public abstract class AbstractGraph<V, E> implements Graph<V, E>
{

	Map<V, List<Edge<V, E>>> adjMap = new HashMap<>();

	/**
	 * Get number of graph vertices
	 */
	@Override
	public int numVertices() {
		return adjMap.keySet().size();
	}

	/**
	 * Get number of graph edges
	 */
	@Override
	public int numEdges()
	{
		int[] sum = {0};
		adjMap.values().forEach(v ->
		{
			sum[0] += v.size();
		});
		return sum[0];
	}

	/**
	 * Make and get new vertex
	 */
	@Override
	public V insertVertex(V v)
	{
		if (v != null || !adjMap.containsKey(v))
			adjMap.put(v, new LinkedList<>());

		return v;
	}

	/**
	 * Graph vertices - iteration
	 */
	@Override
	public Iterator<V> vertices() {
		return adjMap.keySet().iterator();
	}

	/**
	 * Get graph edges - iteration
	 */
	@Override
	public Iterator<Edge<V, E>> edges()
	{
		List<Edge<V, E>> edges = new ArrayList<>();

		for (List<Edge<V, E>> list : adjMap.values())
		{
			edges.addAll(list);
		}

		return edges.iterator();
	}

	/**
	 * Get vertex v and u edge
	 */
	@Override
	public Edge<V, E> getEdge(V v, V u)
	{
		if (v == null || u == null)
			return null;

		if (adjMap.containsKey(v))
		{
			for (Edge<V, E> edge : adjMap.get(v))
			{
				if (edge.getDest().equals(u))
					return edge;
			}
		}

		return null;
	}

	/**
	 * Get array with two endpoint vertices of edge
	 */
	@Override
	public V[] endVertices(Edge<V, E> e)
	{
		V[] ret = (V[]) new Object[2];
		ret[0] = e.getSrc();
		ret[1] = e.getDest();

		return ret;
	}

	/**
	 * Get other vertex of edge from edge and vertex
	 */
	@Override
	public V opposite(V v, Edge<V, E> e)
	{
		if (v != null && e != null)
		{

			if (e.getDest().equals(v))
				return e.getSrc();
			else
				return e.getDest();
		}

		return v;
	}

	/**
	 * Get outgoing edges from vertex
	 */
	@Override
	public int outDegree(V v)
	{
		if (v != null && adjMap.containsKey(v))
		{
			return adjMap.get(v).size();
		}

		return 0;
	}

	/**
	 * Get incoming edges to vertex
	 */
	@Override
	public int inDegree(V v)
	{
		int inDegree = 0;

		if (v != null)
		{
			for (V k : adjMap.keySet())
			{
				// prevent self loop
				if (!k.equals(v))
				{
					Edge<V, E> e = new Edge<>(k, v, null);

					if (adjMap.get(k).contains(e))
					{
						inDegree++;
					}
				}

			}
		}

		return inDegree;
	}

	/**
	 * Get outgoing edges of vertex - iteration
	 */
	@Override
	public Iterator<Edge<V, E>> outgoingEdges(V v)
	{
		if (v != null && adjMap.containsKey(v))
		{
			return adjMap.get(v).iterator();
		}

		return null;
	}

	/**
	 * Get all incoming edges to vertex - iteration
	 */
	@Override
	public Iterator<Edge<V, E>> incomingEdges(V v)
	{
		List<Edge<V, E>> edges = new ArrayList<>();

		if (v != null)
		{
			for (V k : adjMap.keySet())
			{
				// prevent self loop
				if (!k.equals(v))
				{
					for (Edge<V, E> edge : adjMap.get(k))
					{
						if (edge.getDest().equals(v))
							edges.add(edge);
					}
				}

			}
		}

		return edges.iterator();
	}

	/**
	 * Make and get new edge from vertex v to u
	 */
	@Override
	public void insertEdge(V v, V u, E e)
	{
		if (v == null || u == null)
			return;

		if (adjMap.containsKey(v))
		{
			List<Edge<V, E>> edges = adjMap.get(v);
			Edge<V, E> edge = new Edge<>(v, u, e);
			if (!adjMap.get(v).contains(edge))
				adjMap.get(v).add(edge);
		}
	}

	/**
	 * Remove graph edge
	 */
	@Override
	public void removeEdge(Edge<V, E> e)
	{
		if (e != null)
		{
			if (adjMap.containsKey(e.getSrc()))
			{
				adjMap.get(e.getSrc()).remove(e);
			}
		}
	}

	/**
	 * Remove vertex and edges from graph
	 */
	@Override
	public void removeVertex(V v)
	{
		if (v != null)
		{
			if (adjMap.containsKey(v))
			{
				adjMap.remove(v);
			}

			List<Edge<V, E>> edges = new ArrayList<>();
			for (List<Edge<V, E>> list : adjMap.values())
			{
				for (Edge<V, E> e : edges)
				{
					if (e.getDest().equals(v))
						edges.add(e);
				}
			}

			for (Edge<V, E> edge : edges)
			{
				adjMap.get(edge.getSrc()).remove(edge);
			}
		}
	}

}
