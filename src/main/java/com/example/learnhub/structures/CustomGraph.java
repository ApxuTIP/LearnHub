package com.example.learnhub.structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomGraph {

    public static class Edge {
        public final int to;
        public final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
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

    public List<Edge> neighbors(int vertex) {
        List<Edge> edges = adjacency.get(vertex);
        if (edges == null) {
            return List.of();
        }
        return edges;
    }

    public List<Integer> vertices() {
        return vertices;
    }

    public int vertexCount() {
        return vertices.size();
    }
}