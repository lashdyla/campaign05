package edu.isu.cs.cs3308.structures.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Weighted graph class for implementation
 *
 * @author Dylan Lasher
 */
public class Driver
{

	private static String currentFile = GetFile("0");
	private static ImplGraph<String, Integer> graph = new ImplGraph<>();

	public static void main(String[] args) throws IOException
	{
		ParseGraph();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true)
		{
			String selection = GetUserRequest(br);
			ProcessUserRequest(selection, br);
			if (selection.equals("4"))
				break;
		}

		br.close();
	}

	private static String GetUserRequest(BufferedReader br) throws IOException
	{
		System.out.println("\nMenu:\n==========\n" +
						"0: Select a Graph File (Current: " + currentFile + ")\n" +
						"1: Print The Current Network Configuration\n" +
						"2: View Routing Table for a Node\n" +
						"3: Find Shortest Path from One Node to Another\n" +
						"4: Exit"
		);
		System.out.println("\nSelection: ");
		String request = br.readLine();
		return request;
	}

	private static void ProcessUserRequest(String selection, BufferedReader br) throws IOException
	{
		switch (selection)
		{
			case "0":
				GetUserFileSelection(br);
				break;
			case "1":
				PrintConfiguration();
				break;
			case "2":
				GetRoutingTable(br);
				break;
			case "3":
				GetShortestPath(br);
				break;
			case "4":
				System.out.println("\nThank you");
				break;
			default:
				System.out.println("Error - Invalid selection: " + selection);
		}
	}

	private static void GetUserFileSelection(BufferedReader br) throws IOException
	{
		System.out.println("\nPlease elect a file:");
		for (int i = 0; i < 10; i++)
			System.out.println(i + ": " + GetFile(i + ""));


		String selection = br.readLine();
		try {
			String file = GetFile(selection);
			currentFile = file;
			ParseGraph();
		} catch (Exception e)
		{
			System.out.println("Error - Invalid selection");
		}
	}

	private static String GetFile(String index)
	{
		switch (index)
		{
			case "0":
				return "data/test.graph";
			case "1":
				return "data/test_weighted.graph";
			case "2":
				return "data/unweighted_100.graph";
			case "3":
				return "data/unweighted_250.graph";
			case "4":
				return "data/unweighted_500.graph";
			case "5":
				return "data/unweighted_1000.graph";
			case "6":
				return "data/weighted_100.graph";
			case "7":
				return "data/weighted_250.graph";
			case "8":
				return "data/weighted_500.graph";
			case "9":
				return "data/weighted_1000.graph";
			default:
				throw new IllegalArgumentException();
		}
	}

	private static void PrintConfiguration() {
		graph.PrintConfig();
	}

	private static void ParseGraph()
	{
		FileParser fp = new FileParser();
		List<String> list = fp.ReadFile(currentFile);
		graph = fp.Parse(list);

		System.out.println("Number of Vertices:\t" + graph.numVertices());
		System.out.println("Number of Edges:\t" + graph.numEdges());
	}

	private static void GetRoutingTable(BufferedReader br) throws IOException
	{
		System.out.println("\nNode you want to see: ");
		String inputNode = br.readLine();
	}

	private static void GetShortestPath(BufferedReader br) throws IOException
	{
		System.out.println("\nInitial node:");
		String inputNodeA = br.readLine();
		System.out.println("Final node:");
		String inputNodeB = br.readLine();

		System.out.println("\nPath:");
	}
}