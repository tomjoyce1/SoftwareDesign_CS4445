package models.pathfinder;

import models.map.AirTrafficMap;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PathFinder {
    private final AirTrafficMap map;

    public PathFinder(AirTrafficMap map) {
        this.map = map;
    }

    public List<int[]> findShortestPath(int startRow, int startCol, int endRow, int endCol) {
        int rows = map.getRows();
        int cols = map.getCols();
        int[][] distances = initializeDistances(rows, cols, startRow, startCol);
        int[][][] previous = new int[rows][cols][2];
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<int[]> queue = initializeQueue(startRow, startCol);

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            visited[r][c] = true;

            if (r == endRow && c == endCol) {
                break;
            }

            updateNeighbors(queue, distances, previous, visited, directions, r, c);
        }

        return reconstructPath(previous, startRow, startCol, endRow, endCol, distances);
    }

    private int[][] initializeDistances(int rows, int cols, int startRow, int startCol) {
        int[][] distances = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE);
        }
        distances[startRow][startCol] = 0;
        return distances;
    }

    private PriorityQueue<int[]> initializeQueue(int startRow, int startCol) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        queue.add(new int[]{startRow, startCol, 0});
        return queue;
    }

    private void updateNeighbors(PriorityQueue<int[]> queue, int[][] distances, int[][][] previous, boolean[][] visited, int[][] directions, int r, int c) {
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (isValidCell(nr, nc, distances.length, distances[0].length) && !visited[nr][nc]) {
                int weight = 1;
                if (distances[r][c] + weight < distances[nr][nc]) {
                    distances[nr][nc] = distances[r][c] + weight;
                    previous[nr][nc] = new int[]{r, c};
                    queue.add(new int[]{nr, nc, distances[nr][nc]});
                }
            }
        }
    }

    private boolean isValidCell(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    private List<int[]> reconstructPath(int[][][] previous, int startRow, int startCol, int endRow, int endCol, int[][] distances) {
        List<int[]> path = new LinkedList<>();
        if (distances[endRow][endCol] == Integer.MAX_VALUE) {
            return path;
        }
        int[] at = {endRow, endCol};
        while (true) {
            path.addFirst(at);
            if (at[0] == startRow && at[1] == startCol) {
                break;
            }
            at = previous[at[0]][at[1]];
        }
        return path;
    }
}