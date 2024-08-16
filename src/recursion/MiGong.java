package recursion;

public class MiGong {
    public static void main(String[] args) {
        //用二维数组来模拟迷宫
        int[][] map = new int[8][7];
        //其中1代表墙
        //将地图四周围上墙
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //添加挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //查看当前地图情况
        System.out.println("当前地图情况：");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        if (findWay(map, 1, 1)) {
            //查看当前地图情况
            System.out.println("老鼠成功找到出口！\n");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("这个迷宫没有出口？");
        }

    }

    //使用递归，完成老鼠自动寻路的功能
    //对传入的参数进行说明：map表示地图，i与j代表开始行走的坐标
    //规定：1代表墙；0代表该路未走过；2代表该路可以走；3代表这条路走过，但是是死路
    public static Boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//设置终点为(7,6)坐标
            return true;
        } else if (map[i][j] == 0) {//如果此路还未走过，老鼠会遵循下->右->上->左的顺序行走
            map[i][j] = 2;//假定可以走
            if (findWay(map, i + 1, j)) {
                return true;
            } else if (findWay(map, i, j + 1)) {
                return true;
            } else if (findWay(map, i - 1, j)) {
                return true;
            } else if (findWay(map, i, j - 1)) {
                return true;
            } else {//若执行到这儿，说明这是一条死路
                map[i][j] = 3;
                return false;
            }
        } else {//若map[i][j] != 0,则有可能1，2，3.
            //1与3是死路，不走。而2是已经走过了，若继续走会发生重复递归，因此也不能走
            return false;
        }
    }
}
