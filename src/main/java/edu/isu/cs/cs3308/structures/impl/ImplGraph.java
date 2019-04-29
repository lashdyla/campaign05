package edu.isu.cs.cs3308.structures.impl;

import java.util.Iterator;

/**
 * A class for graphs
 *
 * @author Dylan Lasher
 */
public class ImplGraph<V, E> extends AbstractGraph<V, E>
{
	void PrintConfig()
	{
		System.out.println("\nNetwork Configuration:\n\n");

		Iterator<V> vertices = this.vertices();
		Iterator<Edge<V, E>> edges = this.edges();
		for (int v = 0; v < numVertices(); v++)
		{
			System.out.println("\nv =\t" + v);
			V vertex = vertices.next();
			System.out.println("va =\t" + vertex.toString());
			System.out.println("i =\t" + this.inDegree(vertex));
			System.out.println("o =\t" + this.outDegree(vertex));
			Iterator<Edge<V, E>> outEdges = this.outgoingEdges(vertex);
			int outNum = this.outDegree(vertex);

			for (int e = 0; e < outNum; e++)
			{
				System.out.println("\nedge =\t" + e);
				Edge<V, E> edge = outEdges.next();
				System.out.println("s =\t" + edge.getSrc());
				System.out.println("d =\t" + edge.getDest());
				System.out.println("p =\t" + edge.getWeight());
				V[] edgeVertices = this.endVertices(edge);
				if (edge.getWeight() != null)
				{
					System.out.println(edgeVertices[0] + "\t->\t" + edgeVertices[1] + "\t<" + edge.getWeight() + ">");
				} else {
					System.out.println(edgeVertices[0] + "\t->\t" + edgeVertices[1]);
				}
			}
		}

		System.out.println("\n\n");
	}
}
