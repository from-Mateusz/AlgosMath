package cz.mateusz.graphs;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class EulerCircuit {

    @Test
    public void given_connected_vertices_then_determine_if_graph_is_connected() {
        Graph graph = new Graph(3);
        graph.connect(0,1);
        graph.connect(0,2);
        graph.connect(1,2);
        assertThat(graph.isConnected(), is(true));
    }

    @Test
    public void given_connected_graph_then_determine_if_it_is_an_euler_circuit() {
        Graph graph = new Graph(3);
        graph.connect(0,1);
        graph.connect(0,2);
        graph.connect(1,2);
        assertThat(graph.isEulerCircuit(), is(true));
    }

    private static class Graph {

        private final LinkedList<Integer>[] adjacents;

        private int vertices;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacents = new LinkedList[vertices];
            for(int i = 0; i < adjacents.length; i++) adjacents[i] = new LinkedList<>();
        }

        public void connect(int start, int end) {
            adjacents[start].add(end);
            adjacents[end].add(start);
        }

        public boolean isConnected() {
            boolean[] visited = new boolean[vertices];
            bfs(0, adjacents, visited);
            for(int v = 0; v < visited.length; v++) {
                if(!visited[v]) return false;
            }
            return true;
        }

        public boolean isEulerCircuit() {
            if(!isConnected()) return false;
            for(int v = 0; v < adjacents.length; v++) {
                boolean isEvenDegree = getVertexDegree(v) % 2 == 0;
                if(!isEvenDegree) return false;
            }
            return true;
        }

        private int getVertexDegree(int v) {
            return adjacents[v].size();
        }

        private void bfs(int start, LinkedList<Integer>[] adjacents, boolean[] visited) {

            visited[start] = true;

            LinkedList<Integer> vs = new LinkedList<>();

            vs.push(adjacents[start].get(0));

            while(!vs.isEmpty()) {
                int vertex = vs.pop();
                for(int lev = 0; lev < adjacents[vertex].size(); lev++) {
                    int adjacent = adjacents[vertex].get(lev);
                    if(!visited[adjacent]) {
                        vs.add(adjacent);
                        visited[adjacent] = true;
                    }
                }
            }
        }
    }
}
