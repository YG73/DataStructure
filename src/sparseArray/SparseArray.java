package sparseArray;

import java.io.*;

public class SparseArray {
    public static void main(String[] args) {
        //用一个二维数组模拟五子棋————并用稀疏数组来优化
        //创建一个原始的二维数组11*11
        //0表示无子，1表示黑子，2表示白子
        int[][] array = new int[11][11];
        array[2][3] = 2;
        array[1][2] = 1;

        System.out.println("原始的二维数组:\n");
        for (int[] row : array) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将原始二维数组转为稀疏数组
        //1.先遍历原数组，获得非0数据的个数
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int[][] sparseArray = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArray[0][0] = array.length;
        sparseArray[0][1] = array.length;
        sparseArray[0][2] =sum;

        //遍历原始二维数组，将非0值放入稀疏数组
        int count = 0;//记录有几个非0数据
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] != 0) {
                    count++;
                    sparseArray[count][0] =i;
                    sparseArray[count][1] =j;
                    sparseArray[count][2] =array[i][j];
                }
            }
        }
        //打印稀疏数组
        System.out.println();
        System.out.println("get稀疏数组:");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.println();
            System.out.printf("%d\t%d\t%d\t",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println();

        //将稀疏数组恢复为原始二维数组
        //1.读取稀疏数组第一行获取原始数组大小
        int[][] array2 =new int[sparseArray[0][0]][sparseArray[0][1]];
        //2.读取稀疏数组第二行及以后数据，赋值给原始二维数组
        for (int i = 1; i<=sparseArray[0][2];i++){
            array2[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }
        //输出恢复后的二维数组
        System.out.println("\n恢复后的二维数组：\n");
        for (int[] row : array2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将稀疏数组写入磁盘
        String filePath = "src\\sparseArray.dat";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(sparseArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //读取磁盘中储存的稀疏数组
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            int[][] o =(int[][]) ois.readObject();
//            System.out.println("\n读取到的稀疏数组\n");
//            for (int[] row : o) {
//                for (int data : row) {
//                    System.out.printf("%d\t", data);
//                }
//                System.out.println();
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
