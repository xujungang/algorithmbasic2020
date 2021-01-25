package class16;

import common.Graph;

import java.util.*;

/**
 * 图的拓扑排序算法
 * 1）在图中找到所有入度为0的点输出
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 * 要求：有向图且其中没有环
 * 应用：事件安排、编译顺序
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C03_TopologySort {

    public static void main(String[] args) {
        /**
         * 图:          形状           方向              值
         *              a        a -> b, a -> d     a = 1, b = 2
         *            /   \      b -> c, b -> e     c = 3, d = 4
         *          b - c  d             c -> f     e = 5, f = 6
         *         /    | / \    d -> g, e -> f     g = 7
         *        e --- f    g   f -> d
         */
        Graph.GraphInfo graphInfo = common.Graph.generatorGraph("0:1:2,0:1:4,0:2:3,0:2:5,0:3:6,0:4:7,0:5:6,0:6:4");
        // a b c e f d g
        // 1 2 3 5 6 4 7
        List<Graph.GNode> gNodes = topSort(graphInfo.graph);
        for (Graph.GNode gn : gNodes) {
            System.out.print(gn);
        }
        System.out.println();
    }

    public static List<Graph.GNode> topSort(Graph graph) {
        if (graph == null || graph.nodes == null || graph.nodes.isEmpty()) {
            return new ArrayList<>();
        }
        // 记录每个GNode的剩余入度
        Map<Graph.GNode, Integer> countMap = new HashMap<>();
        // 记录入度为0的GNode
        Queue<Graph.GNode> queue = new LinkedList<>();
        for (Graph.GNode gn : graph.nodes.values()) {
            countMap.put(gn, gn.in);
            if (gn.in == 0) {
                queue.add(gn);
            }
        }
        List<Graph.GNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            Graph.GNode gn = queue.poll();
            ans.add(gn);
            for (Graph.GNode node : gn.nexts) {
                countMap.put(node, countMap.get(node) - 1);
                if (countMap.get(node) == 0) {
                    queue.add(node);
                }
            }
        }
        return ans;
    }
}
