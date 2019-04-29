package edu.isu.cs.cs3308.structures.impl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Class for file parsing
 *
 * @author Dylan Lasher
 */
public class FileParser
{
	List<String> ReadFile(String file)
	{
		List<String> list = new LinkedList<>();

		try {
			list = Files.readAllLines(Paths.get(file));
		} catch (IOException ex)
		{
			System.out.println("Error: " + ex);
		}

		return list;
	}

	ImplGraph<String, Integer> Parse(List<String> list)
	{
		ImplGraph<String, Integer> graph = new ImplGraph<>();

		for (String node : list)
		{
			String[] split1 = node.split(":");
			String[] split2 = split1[1].split(" ");

			graph.insertVertex(split1[0]);

			for (int i = 1; i < split2.length; i++)
			{
				String[] split3 = split2[i].split("\\(");
				split2[i] = split3[0];

				graph.insertVertex(split2[i]);

				if (split3.length > 1)
				{
					String weight = split3[1].substring(0, split3[1].length() - 1);
					graph.insertEdge(split1[0], split2[i], parseInt(weight));
				} else {
					graph.insertEdge(split1[0], split2[i], null);
				}
			}
		}

		return graph;
	}

}