package dummyTrie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/////////////////////////////////////////////////////////////////
// Calculating number of different substrings of string with dummy trie (O(N*N)).  
// Tested on http://codeforces.com/gym/100181/ problem C.
//////////////////////////////////////////////////////

public class Main {

	static int states=0;
	private static class Node //Node of trie
	{
		private ArrayList<Edge> edges;
		private Node suffLink; // in first version suffix link not used
		public Node()
		{
			edges=new ArrayList<Edge>();
			//suffLink=new Node();
		}
		public void setSuffLink(Node to)
		{
			suffLink=to;
		}
		public Node addEdgeWithLetter(char e)	//return node, which corresponding new edge
		{
			for(Edge i:edges)                              // if edge with letter already exist 
			{											   // return node, which corresponding this edge
				if(i.getLetter()==e)
				{
					return i.getNode();
				}
			
			}
			
			states++;
			Node newNode=new Node();		
			edges.add(new Edge(e,newNode));
			return newNode;										
		}
		public ArrayList<Edge> getEdges()
		{
			return this.edges;
		}
		
	}
	
	private static class Edge // edges between nodes
	{
		private char letter;
		private Node to; // Corresponding node
		public char getLetter()
		{
			return letter;
		}
		public Node getNode()
		{
			return to;
		}
		public Edge(char _letter, Node _to)
		{
			letter=_letter;
			to=_to;
		}
		public void setLetter(char _letter)
		{
			letter=_letter;
		}
		public void setNode(Node _to)
		{
			to=_to;
		}
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf=new BufferedReader(new FileReader("unequal.in"));
		PrintWriter pw=new PrintWriter("unequal.out");
		//System.out.println("Enter string");
		String str=bf.readLine();
		Node mainNode=new Node();
		ArrayList<Node> leafNodes=new ArrayList<Node>();
		for(int i=0; i<str.length(); i++)
		{
			leafNodes.add(mainNode);
			ArrayList<Node> newWave=new ArrayList<Node>();
			for(Node curNode:leafNodes)
				newWave.add(curNode.addEdgeWithLetter(str.charAt(i)));
			leafNodes=newWave;
		}
//		System.out.println("maybe done");
		pw.println(states);
		pw.close();
	}

}
