public class matrix_multi_Starassen {
    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        if (n == 1)
            C[0][0] = A[0][0] * B[0][0];
        else {
            int[][] A11 = new int[n / 2][n / 2];
            int[][] A12 = new int[n / 2][n / 2];
            int[][] A21 = new int[n / 2][n / 2];
            int[][] A22 = new int[n / 2][n / 2];
            int[][] B11 = new int[n / 2][n / 2];
            int[][] B12 = new int[n / 2][n / 2];
            int[][] B21 = new int[n / 2][n / 2];
            int[][] B22 = new int[n / 2][n / 2];

            split(A, A11, 0, 0);
            split(A, A12, 0, n / 2);
            split(A, A21, n / 2, 0);
            split(A, A22, n / 2, n / 2);

            split(B, B11, 0, 0);
            split(B, B12, 0, n / 2);
            split(B, B21, n / 2, 0);
            split(B, B22, n / 2, n / 2);

            int[][] P = multiply(add(A11, A22), add(B11, B22));
            int[][] Q = multiply(add(A21, A22), B11);
            int[][] R = multiply(A11, sub(B12, B22));
            int[][] S = multiply(A22, sub(B21, B11));
            int[][] T = multiply(add(A11, A12), B22);
            int[][] U = multiply(sub(A21, A11), add(B11, B12));
            int[][] V = multiply(sub(A12, A22), add(B21, B22));

            int[][] C11 = add(sub(add(P, S), T), V);
            int[][] C12 = add(R, T);
            int[][] C21 = add(Q, S);
            int[][] C22 = add(sub(add(P, R), Q), U);

            join(C11, C, 0, 0);
            join(C12, C, 0, n / 2);
            join(C21, C, n / 2, 0);
            join(C22, C, n / 2, n / 2);
        }
        return C;
    }
    public int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }
    public void split(int[][] P, int[][] C, int iB, int jB) { //ib: Starting row index in parent matrix, jB: starting column index in parent matrix
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) //i1: index for iterating through the rows of the child matrix C.
        {                                                 //i2: index for iterating through the rows of the parent matrix P. It starts from the value of iB.
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)//j1: index for iterating through the columns of the child matrix C.
            {                                              //j2: index for iterating through the columns of the parent matrix P. It starts from the value of jB.
                C[i1][j1] = P[i2][j2];
            }
        }
    }
    public void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }
    public static void main(String[] args) {
        matrix_multi_Starassen s = new matrix_multi_Starassen();
        int N = 4;
        int[][] A = {{1, 2, 3, 4},
                {4, 3, 0, 1},
                {5, 6, 1, 1},
                {0, 2, 5, 6}};
        int[][] B = {{1, 0, 5, 1},
                {1, 2, 0, 2},
                {0, 3, 2, 3},
                {1, 2, 1, 2}};
        int[][] C = s.multiply(A, B);
        System.out.println("\nProduct of matrices A and  B : ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
            {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
