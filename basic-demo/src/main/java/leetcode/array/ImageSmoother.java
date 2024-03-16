package leetcode.array;

public class ImageSmoother {

    public static void main(String[] args) {
        int[][] a = {{1,1,1},{1,0,1},{1,1,1}};
        imageSmoother(a);
    }

    public static int[][] imageSmoother(int[][] img) {
         int row = img.length;
         int col = img[0].length;
         int[][] newImg = new int[row] [col];
         for (int i = 0; i < row; i++) {
             for (int j = 0; j < col; j++) {
                 int num = 9;
                 int a1 = -1, a2 = -1, a3 = -1, a4 = -1, a5 = img[i][j], a6 = -1, a7 = -1, a8 = -1, a9 = -1;
                 if (j - 1 < 0 && i - 1 < 0) {
                     a1 = 0;
                     a4 = 0;
                     a7 = 0;
                     num -= 3;
                 } else {
                     a1 = img[i-1][j-1];
                     a4 = img[i][j-1];
                     a7 = img[i+1][j-1];
                 }
                 if (i - 1 < 0) {
                     a1 = 0;
                     a2 = 0;
                     a3 = 0;
                     num -= 3;
                 } else {
                     a1 = img[i-1][j-1];
                     a2 = img[i-1][j];
                     a3 = img[i-1][j+1];
                 }
                if (i + 1 == row) {
                     a7 = 0;
                     a8 = 0;
                     a9 = 0;
                    num -= 3;
                 } else {
                     a7 = img[i+1][j-1];
                     a8 = img[i+1][j];
                     a9 = img[i+1][j+1];
                 }
                if (j + 1 == col) {
                     a3 = 0;
                     a6 = 0;
                     a9 = 0;
                    num -= 3;
                 } else {
                     a3 = img[i-1][j+1];
                     a6 = img[i][j+1];
                     a9 = img[i+1][j+1];
                 }
                 if (a1 < 0) a1 = img[i-1][j-1];
                 if (a2 < 0) a1 = img[i-1][j];
                 if (a3 < 0) a1 = img[i-1][j+1];
                 if (a4 < 0) a1 = img[i][j-1];
                 if (a6 < 0) a1 = img[i][j+1];
                 if (a7 < 0) a1 = img[i+1][j-1];
                 if (a8 < 0) a1 = img[i+1][j];
                 if (a9 < 0) a1 = img[i+1][j+1];
                 int sum = a1 + a2 + a3+ a4+ a5+ a6 + a7 + a8 + a9;
                 newImg[i][j] = sum / num;
             }
         }
         return newImg;
    }
}