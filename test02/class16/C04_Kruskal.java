package class16;

import common.Graph;

/**
 * 最小生成树算法之Kruskal
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C04_Kruskal {

    public static void main(String[] args) {
        /**
         * 图:          形状           方向(权重)                                  值
         *               a          a - b(1), a - c(7), a - d(3)                a = 1, b = 2
         *            /  |  \       b - c(4), b - d(1), b - e(9), b - f(6)      c = 3, d = 4
         *           c - b - d      c - e(10),d - f(4), e - f(1)                e = 5, f = 6
         *            \ / \ |
         *             e - f
         */
        Graph.GraphInfo graphInfo = common.Graph.generatorGraph(
                "1:1:2,7:1:3,3:1:4,4:2:3,1:2:4,9:2:5,6:2:6,10:3:5,4:4:6,1:5:6");
    }
}
