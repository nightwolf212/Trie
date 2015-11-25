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
// with suffix link time of executing is about 11000ms
//////////////////////////////////////////////////////

public class Main {

	static int states=0;
	static boolean end=false;
	private static class Node //Node of trie
	{
		private ArrayList<Edge> edges;
		private Node suffLink; // in first version suffix link not used
		public Node()
		{
			edges=new ArrayList<Edge>();
			suffLink=null;
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
					end=true;
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
		public Node getNodeBySuffixLink()
		{
			return this.suffLink;
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
		//BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
		//PrintWriter pw=new PrintWriter("unequal.out");
		PrintWriter pw=new PrintWriter(System.out);
		
		//System.out.println("Enter string");
		
		String str=bf.readLine();
		long tm=System.currentTimeMillis();
		Node dummy=new Node();
		Node mainNode=new Node();
		for(int i=0; i<26; i++)
		{
			Edge e=new Edge((char)(i+'a'),mainNode);
			dummy.getEdges().add(e);
		}
		mainNode.setSuffLink(dummy);
		ArrayList<Node> leafNodes=new ArrayList<Node>();
		Node deepest=mainNode;
		for(int i=0; i<str.length(); i++)
		{
			end=false;
			Node cn=deepest;
			deepest=deepest.addEdgeWithLetter(str.charAt(i));
		
			Node prevLeaf=deepest;
			while(!end)
			{
				
				cn=cn.getNodeBySuffixLink();
				Node newNode=cn.addEdgeWithLetter(str.charAt(i));
				prevLeaf.setSuffLink(newNode);
				prevLeaf=newNode;
				
			}
		}
		pw.println(states);
		pw.print(System.currentTimeMillis()-tm);
		pw.close();
	}

}
