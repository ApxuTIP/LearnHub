package com.example.learnhub.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGraph {

    public static class Edge {

        private final int destination;
        private final int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public int getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Edge{destination=" + destination + ", weight=" + weight + "}";
        }
    }

    private final Map<Integer, List<Edge>> adjacency = new HashMap<>();
    private final List<Integer> vertices = new ArrayList<>();

    public void addVertex(int vertex) {
        if (!adjacency.containsKey(vertex)) {
            adjacency.put(vertex, new ArrayList<>());
            vertices.add(vertex);
        }
    }

    public void addEdge(int from, int to, int weight) {
        addVertex(from);
        addVertex(to);
        adjacency.get(from).add(new Edge(to, weight));
    }

    public List<Edge> getNeighbors(int vertex) {
        List<Edge> edges = adjacency.get(vertex);
        if (edges == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(edges);
    }

    public List<Integer> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public boolean containsVertex(int vertex) {
        return adjacency.containsKey(vertex);
    }
}