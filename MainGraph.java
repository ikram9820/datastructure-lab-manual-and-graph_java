import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainGraph {

	public static void main(String args[]) {

		Graph g = new Graph();
		Scanner sc = new Scanner(System.in);
		boolean run = true;
		while (run) {
			System.out.print(
					"0:exit  1:insert vertex  2:delete vertex  3:add edge b/w vertices  4:remove edge b/w vertices"
							+ "\n5:depth first traversal  6:find vertex in Graph  " + "\nenter operation No.");
			int ch = 0;
			ch = sc.nextInt();

			switch (ch) {
			case 0:

				run = false;
				System.out.println(".....exit.....");
				break;
			case 1:
				System.out.print("enter data : ");
				System.out.println(g.addVertex(sc.nextInt()));
				break;
			case 2:
				System.out.print("enter data : ");
				System.out.println(g.delVert(sc.nextInt()));
				break;
			case 3:
				System.out.print("enter vertex1 : ");
				int vert1 = sc.nextInt();
				System.out.print("enter vertex2 : ");
				System.out.println(g.addEdge(vert1, sc.nextInt()));
				break;
			case 4:
				System.out.print("enter vertex1 : ");
				int vert = sc.nextInt();
				System.out.print("enter vertex2 : ");
				System.out.println(g.removeEdge(vert, sc.nextInt()));

				break;
			case 5:
				System.out.print("depth first traversal:");
				g.dft();
				System.out.println("");
				break;
			case 6:
				System.out.print("enter data to find:");
				System.out.print(g.dfs(sc.nextInt()));
				System.out.println("");
				break;

			default:
				System.out.println("invalid option");
			}// end switch
		} // end while loop
	} // end void main()

} // end Lab class

class Vertex {
	public int data;
	public LinkedList<Vertex> adjacency;
	public boolean visited;

	public Vertex(int data) {
		visited = false;
		adjacency = new LinkedList<Vertex>();
		this.data = data;
	}// end Vertex constructor
}// end Vertex class

class Graph {
	private ArrayList<Vertex> vertices;

	public Graph() {
		vertices = new ArrayList<Vertex>();
	}// end Graph constructor

	public String addVertex(int v) {
		Vertex vert = new Vertex(v);
		for (int i = 0; i < vertices.size(); i++)
			if (vertices.get(i).data == vert.data)
				return "same vertex is not allowed  \n ";

		vertices.add(vert);
		return vert.data + " is added to graph   \n";
	}// end addVertex()

	public String delVert(Integer vert) {

		for (int i = 0; i < vertices.size(); i++) {

			if (vertices.get(i).data == vert) {
				vertices.remove(i);
				return vert + " is deleted from graph  \n ";
			} // end if

		} // end for
		return vert + " is not found in graph   \n";
	}// end delVert()

	public String addEdge(int u, int v) {
		int i = 0, j = 0;
		Vertex vertU = null, vertV = null;

		for (i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).data == u) {
				vertU = vertices.get(i);
				break;
			}
		}

		for (j = 0; j < vertices.size(); j++) {
			if (vertices.get(j).data == v) {
				vertV = vertices.get(j);
				break;
			}
		}

		if (vertU != null && vertV != null) {
			vertU.adjacency.add(vertV);
			vertV.adjacency.add(vertU);
			return u + " and " + v + " is connected through edge   \n";
		} else if (vertU == null)
			return "vertex " + u + " is not available in graph   \n";
		else if (vertV == null)
			return "vertex " + v + " is not available in graph   \n";
		return "there is no edge between " + u + " and    " + v + "\n";

	}// end addEdge()

	public String removeEdge(int u, int v) {
		int i = 0, j = 0;
		Vertex vertU = null, vertV = null;
		for (i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).data == u) {
				vertU = vertices.get(i);
				break;
			}
		}

		for (j = 0; j < vertices.size(); j++) {
			if (vertices.get(j).data == v) {
				vertV = vertices.get(j);
				break;
			}
		}

		if (vertU != null && vertV != null) {
			vertU.adjacency.remove(vertV);
			vertV.adjacency.remove(vertU);
			return u + " and " + v + " is unconnected through edge  \n";
		}
		if (vertU == null)
			return "vertex " + u + " is not available in graph  \n";
		if (vertV == null)
			return "vertex " + v + " is not available in graph  \n";
		return "there is no edge between " + u + " and    " + v + "\n";

	}// end removeEdge()

	public void dft() {
		Stack<Vertex> s = new Stack<Vertex>(10);

		Vertex v = vertices.get(0);
		System.out.print(v.data + " ");
		v.visited = true;
		s.push(v);
		while (!s.isEmpty()) {
			v = getUnVisitedAdjacency(s.peak());
			if (v == null)
				s.pop();
			else {
				System.out.print(v.data + " ");
				v.visited = true;
				s.push(v);
			}

		}
		for (int i = 0; i < vertices.size(); i++)
			vertices.get(i).visited = false;

	}// end dft()

	public String dfs(int item) {
		Stack<Vertex> s = new Stack<Vertex>(10);

		Vertex v = vertices.get(0);
		v.visited = true;
		if (v.data == item)
			return item + " found in graph\n";
		s.push(v);
		while (!s.isEmpty()) {
			v = getUnVisitedAdjacency(s.peak());
			if (v == null)
				s.pop();
			else {
				if (v.data == item)
					return item + " found in graph\n";
				v.visited = true;
				s.push(v);
			}

		}
		for (int i = 0; i < vertices.size(); i++)
			vertices.get(i).visited = false;

		return item + " didn't find in graph\n";
	}// end dfs()

	public Vertex getUnVisitedAdjacency(Vertex v) {
		for (int i = 0; i < v.adjacency.size(); i++) {
			if (v.adjacency.get(i).visited == false)
				return v.adjacency.get(i);
		}
		return null;

	}// end getUnVisitedAdjacency()

}// end Graph

class Stack<T> {
	private T arr[];
	private int top;

	public Stack(int size) {
		arr = (T[]) new Object[size];
		top = -1;
	}

	public void push(T item) {
		if (top == arr.length)
			return;
		arr[++top] = item;
	}

	public T pop() {
		if (top == -1)
			return null;
		return arr[top--];
	}

	public T peak() {
		if (top == -1)
			return null;

		return arr[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}
}