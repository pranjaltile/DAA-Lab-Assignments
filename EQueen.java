import java.util.Arrays;
class EQueen {

	static final int N = 8;

	// function to check if it is safe to place
	// a queen at a particular position
	static boolean isSafe(int[][] board, int row, int col)
	{

		// check if there is a queen in the same row to the
		// left
		for (int x = 0; x < col; x++)
			if (board[row][x] == 1)
				return false;

		// check if there is a queen in the upper left
		// diagonal
		for (int x = row, y = col; x >= 0 && y >= 0;
			x--, y--)
			if (board[x][y] == 1)
				return false;

		// check if there is a queen in the lower left
		// diagonal
		for (int x = row, y = col; x < N && y >= 0;
			x++, y--)
			if (board[x][y] == 1)
				return false;

		// if there is no queen in any of the above
		// positions, then it is safe to place a queen
		return true;
	}

	// function to solve the N-queens problem using
	// backtracking
	static boolean solveNQueens(int[][] board, int col)
	{

		// if all queens are placed, print the board
		if (col == N) {
			for (int[] row : board)
				System.out.println(Arrays.toString(row));
			System.out.println();
			return true;
		}

		// try placing a queen in each row of the current
		// column
		for (int i = 0; i < N; i++) {
			if (isSafe(board, i, col)) {
				board[i][col] = 1; // place the queen
				if (solveNQueens(board, col + 1))
					return true;

				// backtrack if placing the queen doesn't
				// lead to a solution
				board[i][col] = 0;
			}
		}

		// if no solution is found, return false
		return false;
	}

	// initialize the board with zeros and call the
	// solveNQueens function to solve the problem
	public static void main(String[] args)
	{
		int[][] board = new int[N][N];
		if (!solveNQueens(board, 0))
			System.out.println("No solution found");
	}
}

