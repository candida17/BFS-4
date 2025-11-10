// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this :No

// Your code here along with comments explaining your approach
/*
Using BFS traversal from the given click position we can reveal the board one by one.
If the click position is on the Mine then we change that cell to 'X' and return board.
We then count the mines around a particular cell and update its value to the count if count is > 0 
If no mines around then count becomes 0 and we can safely reveal and mark it blank.
We return the board after traversing all cells that were in the queue.
*/
class Solution {
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        this.dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { 1, 1 }, { -1, 1 },
                { 1, -1 } };
        //check if the click is on the Unrevealed Mine ie M
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] { click[0], click[1] });
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            //get the count of mines around
            int count = countMines(board, cur[0], cur[1]);
            if (count == 0) {
                //no mines around can safely reveal all boxes and change to B
                //get the neighbors
                for (int[] dir : dirs) {
                    int r = dir[0] + cur[0];
                    int c = dir[1] + cur[1];

                    if (r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'E') {
                        board[r][c] = 'B';
                        q.add(new int[] { r, c });
                    }
                }
            } else {
                //mines present around
                board[cur[0]][cur[1]] = (char) (count + '0');
            }
        }
        return board;

    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        //get the count from mine box
        for (int[] dir : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;

            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'M')
                count++;
        }
        return count;
    }
}
