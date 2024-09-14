import java.util.Scanner;

public class Solution {
    static class UnionFind {
        int[] parent;
        int[] rank;
        int[] enemy;

        // Initialize the UnionFind structure
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            enemy = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Each person is their own leader initially
                rank[i] = 0;   // Rank is 0 for all
                enemy[i] = -1; // No enemies initially
            }
        }

        // Find with path compression
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Union by rank
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }

        // Set x and y as friends
        public int setFriends(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            // If x and y are enemies, it's a contradiction
            if (rootX == find(enemy[rootY])) {
                return -1;
            }

            // Union friends
            union(x, y);

            // Union their enemies
            int enemyX = enemy[rootX];
            int enemyY = enemy[rootY];

            if (enemyX != -1 && enemyY != -1) {
                union(enemyX, enemyY);
            }

            // Link enemies to common enemies
            enemy[find(x)] = (enemyX != -1) ? find(enemyX) : find(enemyY);
            if (enemy[find(x)] != -1) {
                enemy[find(enemy[find(x)])] = find(x);
            }

            return 0;
        }

        // Set x and y as enemies
        public int setEnemies(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            // If x and y are friends, it's a contradiction
            if (rootX == rootY) {
                return -1;
            }

            // Union x's enemies with y and y's enemies with x
            if (enemy[rootX] != -1) {
                union(enemy[rootX], y);
            }
            if (enemy[rootY] != -1) {
                union(enemy[rootY], x);
            }

            // Set them as each other's enemies
            enemy[find(x)] = find(y);
            enemy[find(y)] = find(x);

            return 0;
        }

        // Check if x and y are friends
        public int areFriends(int x, int y) {
            return (find(x) == find(y)) ? 1 : 0;
        }

        // Check if x and y are enemies
        public int areEnemies(int x, int y) {
            return (find(x) == find(enemy[find(y)])) ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        UnionFind uf = new UnionFind(n);
        StringBuilder result = new StringBuilder();

        while (true) {
            int c = scanner.nextInt();
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            if (c == 0 && x == 0 && y == 0) {
                break; // End of input
            }

            int outcome = 0;

            switch (c) {
                case 1: // setFriends
                    outcome = uf.setFriends(x, y);
                    if (outcome == -1) {
                        result.append("-1\n");
                    }
                    break;
                case 2: // setEnemies
                    outcome = uf.setEnemies(x, y);
                    if (outcome == -1) {
                        result.append("-1\n");
                    }
                    break;
                case 3: // areFriends
                    result.append(uf.areFriends(x, y)).append("\n");
                    break;
                case 4: // areEnemies
                    result.append(uf.areEnemies(x, y)).append("\n");
                    break;
            }
        }

        System.out.print(result.toString());
        scanner.close();
    }
}
