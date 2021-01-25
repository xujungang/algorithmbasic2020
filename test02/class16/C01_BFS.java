package class16;

import common.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 宽度有限遍历图
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C01_BFS {

    public static void main(String[] args) {
        /**
         * 图:          形状           方向              值
         *              a        a -> b, a -> d     a = 1, b = 2
         *            / | \      b -> c, b -> e     c = 3, d = 4
         *          b - c  d     c -> a, c -> f     e = 5, f = 6
         *         /    | / \    d -> g, e -> f     g = 7
         *        e --- f    g   f -> d
         */
        Graph.GraphInfo graphInfo = common.Graph.generatorGraph("0:1:2,0:1:4,0:2:3,0:2:5,0:3:1,0:3:6,0:4:7,0:5:6,0:6:4");
        // a b d c e g f
        // 1 2 4 3 5 7 6
        bfs(graphInfo.first);
        System.out.println();
    }

    public static void bfs(common.Graph.GNode start) {
        if (start == null) {
            return;
        }
        Queue<Graph.GNode> queue = new LinkedList<>();
        Set<Graph.GNode> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Graph.GNode gn = queue.poll();
            for (Graph.GNode node : gn.nexts) {
                if (!set.contains(node)) {
                    set.add(node);
                    queue.add(node);
                }
            }
            System.out.print(gn + " ");
        }
    }
}
