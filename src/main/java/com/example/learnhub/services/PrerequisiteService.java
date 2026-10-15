package com.example.learnhub.services;

import com.example.learnhub.structures.Graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PrerequisiteService {
    private static final int WHITE = 0;
    private static final int GRAY = 1;
    private static final int BLACK = 2;

    private final Graph graph = new Graph();

    public void addCourse(int courseId) {
        graph.addVertex(courseId);
    }

    public void addPrerequisite(int fromCourseId, int toCourseId, int hours) {
        graph.addEdge(fromCourseId, toCourseId, hours);
    }

    public List<Integer> reachableFrom(int startCourseId) {
        List<Integer> visitOrder = new ArrayList<>();
        Set<Integer> visitedVertices = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        visitedVertices.add(startCourseId);
        queue.addLast(startCourseId);

        while (!queue.isEmpty()) {
            int currentVertex = queue.removeFirst();
            for (Graph.Edge edge : graph.getNeighbors(currentVertex)) {
                int destination = edge.getDestination();
                if (!visitedVertices.contains(destination)) {
                    visitedVertices.add(destination);
                    visitOrder.add(destination);
                    queue.addLast(destination);
                }
            }
        }
        return visitOrder;
    }

    public int countIndependentTracks() {
        Map<Integer, List<Integer>> undirected = buildUndirectedAdjacency();
        Set<Integer> visitedVertices = new HashSet<>();
        int trackCount = 0;

        for (int vertex : graph.getVertices()) {
            if (visitedVertices.contains(vertex)) {
                continue;
            }
            trackCount++;
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.addLast(vertex);
            visitedVertices.add(vertex);
            while (!queue.isEmpty()) {
                int currentVertex = queue.removeFirst();
                List<Integer> neighbors = undirected.get(currentVertex);
                if (neighbors == null) {
                    continue;
                }

                for (int neighbor : neighbors) {
                    if (!visitedVertices.contains(neighbor)) {
                        visitedVertices.add(neighbor);
                        queue.addLast(neighbor);
                    }
                }
            }
        }
        return trackCount;
    }

    public List<Integer> findCycle() {
        Map<Integer, Integer> color = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int vertex : graph.getVertices()) {
            color.put(vertex, WHITE);
        }
        for (int vertex : graph.getVertices()) {
            if (color.get(vertex) == WHITE && result.isEmpty()) {
                result = dfsFindCycle(vertex, color, parent);
            }
        }
        return result;
    }

    public Map<Integer, Integer> shortestHoursFrom(int sourceCourseId) {
        Map<Integer, Integer> shortestDistances = new HashMap<>();
        Set<Integer> settledVertices = new HashSet<>();

        Map<Integer, Integer> distances = new HashMap<>();
        for (int vertex : graph.getVertices()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(sourceCourseId, 0);

        PriorityQueue<VertexDistance> queue = new PriorityQueue<>();
        queue.add(new VertexDistance(sourceCourseId, 0));

        while (!queue.isEmpty()) {
            VertexDistance currentPair = queue.poll();
            int currentVertex = currentPair.getVertex();

            if (settledVertices.contains(currentVertex)) {
                continue;
            }
            settledVertices.add(currentVertex);

            for (Graph.Edge edge : graph.getNeighbors(currentVertex)) {
                int destination = edge.getDestination();
                if (settledVertices.contains(destination)) {
                    continue;
                }
                int candidateDistance = currentPair.getDistance() + edge.getWeight();
                Integer knownDistance = distances.get(destination);
                if (knownDistance == null || candidateDistance < knownDistance) {
                    distances.put(destination, candidateDistance);
                    queue.add(new VertexDistance(destination, candidateDistance));
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : distances.entrySet()) {
            if (entry.getValue() != Integer.MAX_VALUE) {
                shortestDistances.put(entry.getKey(), entry.getValue());
            }
        }
        return shortestDistances;
    }

    public Graph getGraph() {
        return graph;
    }

    private Map<Integer, List<Integer>> buildUndirectedAdjacency() {
        Map<Integer, List<Integer>> undirected = new HashMap<>();
        for (int vertex : graph.getVertices()) {
            undirected.computeIfAbsent(vertex, key -> new ArrayList<>());
            for (Graph.Edge edge : graph.getNeighbors(vertex)) {
                int destination = edge.getDestination();
                undirected.computeIfAbsent(destination, key -> new ArrayList<>()).add(vertex);
                undirected.get(vertex).add(destination);
            }
        }
        return undirected;
    }

    private List<Integer> dfsFindCycle(int vertex, Map<Integer, Integer> color, Map<Integer, Integer> parent) {
        color.put(vertex, GRAY);
        List<Integer> cycle = new ArrayList<>();
        for (Graph.Edge edge : graph.getNeighbors(vertex)) {
            int nextVertex = edge.getDestination();
            Integer nextColor = color.getOrDefault(nextVertex, WHITE);
            if (nextColor == GRAY) {
                cycle = buildCyclePath(vertex, nextVertex, parent);
                break;
            }

            if (nextColor == WHITE) {
                parent.put(nextVertex, vertex);
                cycle = dfsFindCycle(nextVertex, color, parent);
                if (!cycle.isEmpty()) {
                    break;
                }
            }
        }
        color.put(vertex, BLACK);
        return cycle;
    }

    private List<Integer> buildCyclePath(int fromVertex, int toVertex, Map<Integer, Integer> parent) {
        List<Integer> path = new ArrayList<>();
        path.add(toVertex);
        int currentVertex = fromVertex;
        while (currentVertex != toVertex) {
            path.add(currentVertex);
            Integer previousVertex = parent.get(currentVertex);
            if (previousVertex == null) {
                break;
            }
            currentVertex = previousVertex;
        }

        path.add(toVertex);
        int leftIndex = 0;
        int rightIndex = path.size() - 1;

        while (leftIndex < rightIndex) {
            Integer temporary = path.get(leftIndex);
            path.set(leftIndex, path.get(rightIndex));
            path.set(rightIndex, temporary);
            leftIndex++;
            rightIndex--;
        }
        return path;
    }

    private static class VertexDistance implements Comparable<VertexDistance> {
        private final int vertex;
        private final int distance;

        private VertexDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        private int getVertex() {
            return vertex;
        }

        private int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(VertexDistance otherPair) {
            return Integer.compare(this.distance, otherPair.distance);
        }
    }
}