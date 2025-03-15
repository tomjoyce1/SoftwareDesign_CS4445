package models.pathfinder;

import models.map.AirTrafficMap;
import java.util.List;
import java.util.LinkedList;
import java.util.Arrays; 
import java.util.PriorityQueue;
import java.util.Comparator;

// pathfinder class finding shortest path between two cells using dijkstra's algorithm

public class PathFinder {
    private final AirTrafficMap map;

    public PathFinder(AirTrafficMap map) {
        this.map = map;
    }

    public List<int[]> findShortestPath(int startRow, int startCol, int endRow, int endCol) {
        int rows = map.getRows();
        int cols = map.getCols();
        int[][] distances = new int[rows][cols];
        int[][][] previous = new int[rows][cols][2];
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        distances[startRow][startCol] = 0;

        // priorirty queue for dijkstra's algorithm
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[]{startRow, startCol, 0});

        int[][] directions = { {1,0}, {-1,0}, {0,1}, {0,-1} };

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1];

            if (visited[r][c]) {
                continue;
            }
            visited[r][c] = true;

            if (r == endRow && c == endCol) {
                break;
            }

            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                    int weight = 1;
                    if (distances[r][c] + weight < distances[nr][nc]) {
                        distances[nr][nc] = distances[r][c] + weight;
                        previous[nr][nc] = new int[]{r, c};
                        queue.add(new int[]{nr, nc, distances[nr][nc]});
                    }
                }
            }
        }

        // reconstructs path
        List<int[]> path = new LinkedList<>();
        if (distances[endRow][endCol] == Integer.MAX_VALUE) {
            return path;
        }
        for (int[] at = {endRow, endCol}; ; ) {
            path.add(0, at);
            if (at[0] == startRow && at[1] == startCol) {
                break;
            }
            at = previous[at[0]][at[1]];
        }
        return path;
    }
}
