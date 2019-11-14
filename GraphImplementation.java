import java.util.*;

public class GraphImplementation implements Graph
{
	public int[][] graph;
	public int size;
	public List<Integer> sortedList;

	public GraphImplementation(int s) {
		size = s;
		graph = new int[size][size];
		sortedList = new ArrayList<>();
	}

	public void addEdge(int v1, int v2) throws Exception {
		if (v1>=size || v2>=size) {
			throw new Exception();
		}
		graph[v1][v2] = 1;
	}

	public List<Integer> topologicalSort() {
		int[] numIncident = new int[size];
		for (int j=0; j<size; j++) {
			for (int i=0; i<size; i++) {
				if (graph[i][j] == 1) {
					numIncident[j]++;
					// if there's an edge from i to j, then the number of incident edge of j adds 1
				}
			}
		}

		while (sortedList.size() != size) {
		// the loop stops when all vertices are sorted (added to the sortedList)
			for (int i=0; i<numIncident.length; i++) {
				if (numIncident[i] == 0) {
					sortedList.add(i);
					numIncident[i] = -1;
					// delete vertex i by first setting the number of incident edges of i to -1 so that
					// this vertex will not be considered again
					for (int j=0; j<size; j++) {
						if (graph[i][j] != 0) {
							numIncident[j]--;
						}
					}
					// delete vertex i by decreasing the number of the incident edges of all its neighbors
					// by 1
				}
			}
		}

		return sortedList;

	}

	public List<Integer> neighbors(int vertex) throws Exception {
		if (vertex >= size) {
			throw new Exception();
		}
		List<Integer> neighbors = new ArrayList<>();
		for (int i=0; i<graph.length; i++) {
			if (graph[vertex][i] == 1) {
			// if there's an edge from vertex to i
				neighbors.add(i);
			}
		}
		return neighbors;
	}
}