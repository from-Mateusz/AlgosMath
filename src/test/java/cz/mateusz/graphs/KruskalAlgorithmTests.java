package cz.mateusz.graphs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class KruskalAlgorithmTests {

    private int parent[] = new int[1000];
    private int rank[] = new int[1000];

    @Test
    public void given_graph_find_minimum_spanning_tree_then_print_path() {
        final Graph graph = new Graph(4, 5);
        graph.connect(0, 1, 10)
                .connect(0, 2, 6)
                .connect(2, 3, 4)
                .connect(3, 1, 15)
                .connect(0,3, 5);

        final KruskalAlgorithm kAlgorithm = new KruskalAlgorithm(graph);

        final Edge[] mstEdges = kAlgorithm.findMST();

        assertThat(mstEdges[0], equalTo(new Edge(2, 3, 4)));
        assertThat(mstEdges[1], equalTo(new Edge(0, 3, 5)));
        assertThat(mstEdges[2], equalTo(new Edge(0, 1, 10)));
    }

    // Minimum spanning tree
    private class KruskalAlgorithm {

        private Graph graph;

        public KruskalAlgorithm(Graph graph) {
            this.graph = graph;
        }

        private int find(Subset[] subsets, int vertex) {
            if(vertex == subsets[vertex].parent) {
                return vertex;
            }
            return subsets[vertex].parent = find(subsets, subsets[vertex].parent);
        }

        private void union(Subset[] subsets, int v, int w) {
            int vParent = find(subsets, v);
            int wParent = find(subsets, w);

            if(subsets[vParent].rank < subsets[wParent].rank) {
                subsets[vParent].parent = wParent;
            }
            else if(subsets[wParent].rank < subsets[vParent].rank) {
                subsets[wParent].parent = vParent;
            } else {
                subsets[vParent].parent = wParent;
                // Only if you span trees with equal depth, the rank (depth of the tree is going to up)
                subsets[wParent].rank++;
            }
        }

        public Edge[] findMST() {
            Edge[] _edges = new Edge[graph.vertices];

            Edge[] graphEdges = graph.edges;

            Arrays.sort(graphEdges);

            Subset[] subsets = new Subset[graph.vertices];

            for(int s = 0; s < subsets.length; s++) {
                subsets[s] = new Subset(s, 0);
            }

            int GRAPH_EDGE = 0;
            int RESULT_EDGE = 0;
            while(RESULT_EDGE < subsets.length - 1) {
                Edge edge = graphEdges[GRAPH_EDGE++];
                int v = find(subsets, edge.source);
                int w = find(subsets, edge.destination);

                if(v != w) {
                    _edges[RESULT_EDGE++] = edge;
                    union(subsets, v, w);
                }
            }
            return _edges;
        }
    }

    private class Subset {

        private int parent;

        private int rank;

        public Subset(int parent, int rank) {
            this.parent = parent;
            this.rank = rank;
        }

        public int getParent() {
            return parent;
        }

        public int getRank() {
            return rank;
        }
    }

    private class Graph {

        private Edge[] edges;

        private int vertices = 0;

        private Graph(int vertices, int edges) {
            this.vertices = vertices;
            this.edges = new Edge[edges];
        }

        public Graph connect(int source, int destination, int distance) {
            if(source < 0 || destination < 0)
                throw new IllegalArgumentException("Only positive vertices' numbers");
            if(source > (edges.length - 1) || destination > (edges.length -1))
                throw new IllegalArgumentException("Source or destination must not be greater than the total number of vertices");

            for(int e = 0; e < edges.length; e++) {
                if(edges[e] == null) {
                    edges[e] = new Edge(source, destination, Math.abs(distance));
                    break;
                }
            }

            return this;
        }

        public Edge[] getEdges() {
            return edges;
        }
    }

    private class Edge implements Comparable<Edge> {

        private int source;

        private int destination;

        private int distance;

        public Edge(int source, int destination, int distance) {
            this.source = source;
            this.destination = destination;
            this.distance = Math.abs(distance);
        }

        public int getSource() {
            return source;
        }

        public int getDestination() {
            return destination;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return source == edge.source && destination == edge.destination && distance == edge.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, distance);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "source=" + source +
                    ", destination=" + destination +
                    ", distance=" + distance +
                    '}';
        }
    }
}
