package class16;

import common.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树算法之Prim
 * 1）可以从任意节点出发来寻找最小生成树
 * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
 * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
 * 6）当所有点都被选取，最小生成树就得到了
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C05_Prim {

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
        for (Graph.Edge edge : primMST(graphInfo.graph)) {
            System.out.println("edge[weigh=" + edge.weigh + ", from=" + edge.from + ", to=" + edge.to);
        }
    }

    public static Set<Graph.Edge> primMST(common.Graph graph) {
        // 小根堆
        PriorityQueue<common.Graph.Edge> queue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weigh));
        Set<Graph.GNode> lock = new HashSet<>();    // 遍历后会被锁住的节点
        Set<Graph.Edge> ans = new HashSet<>();      // 返回结果
        for (Graph.GNode gn : graph.nodes.values()) {
            if (!lock.contains(gn)) {
                lock.add(gn);       // 锁住节点
                // 找到最小边,解锁新的节点
                queue.addAll(gn.edges);
                while (!queue.isEmpty()) {
                    Graph.Edge edge = queue.poll();
                    Graph.GNode node = edge.to;
                    if (!lock.contains(node)) {
                        lock.add(node);             // 锁住节点
                        ans.add(edge);              // 添加边
                        queue.addAll(node.edges);
                    }
                }
            }
            break;
        }
        return ans;
    }
}
