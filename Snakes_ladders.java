// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes 
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
/*
Idea is to flatten the 2D board into 1D array and perform BFS traversal on it
Start by adding the first index into the queue and roll a dice with the result being anywhere between 1 to 6
Add those many to the current position, if landed at snake or ladder ie a cell not marked as -1 then go to that position
return the moves taken to reach n^2 in the end.
*/

class Solution {
    public int snakesAndLadders(int[][] board) {
        //to flatten the board into 1D array
        int n= board.length;
        int[] flat = new int[n*n +1];
        boolean leftToRight = true;
        int idx = 1;
        //flatten the board
        for(int i = n-1; i>=0; i--) {
            if(leftToRight) {
                //from left to right
                for(int j=0; j<n;j++) flat[idx++] = board[i][j];
            } else {
                //from right to left
                for(int j = n-1; j>=0; j--) flat[idx++] = board[i][j];

            }
            leftToRight = !leftToRight;
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited= new boolean[n*n +1];
        int moves = 0;
        //add initial index
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i <size;i++) {
                int cur = q.poll();
                //check if the cur is n^2
                if(cur == (n*n)) return moves;

                //roll dice from 1 to 6
                for(int dice =1; dice <=6; dice++) {
                    int next = cur + dice;
                    //if the destination is > n^2 then break
                    if(next > n*n) continue;
                    //if snake or ladder is encountered next will jump to the number present at that index
                    if(flat[next] != -1) next = flat[next];
                    //if normal move
                    if(!visited[next]) {
                        q.add(next);
                        visited[next]=true;
                    }
                }
            }
            moves++;
        }
        return -1;//not able to reach n^2
        
    }
}
