package class16;

import common.Graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * 深度遍历图
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class C02_DFS {

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
        // a b c f d g e
        // 1 2 3 6 4 7 5
        dfs1(graphInfo.first);  // 递归的方式
        System.out.println();
        dfs2(graphInfo.first);  // 非递归方式
        System.out.println();
    }

    public static void dfs1(Graph.GNode start) {
        if (start == null) {
            return;
        }
        Set<Graph.GNode> cache = new HashSet<>();
        cache.add(start);
        process(start, cache);
    }

    public static void process(Graph.GNode start, Set<Graph.GNode> cache) {
        System.out.print(start);
        for (Graph.GNode node : start.nexts) {
            if (!cache.contains(node)) {
                cache.add(node);
                process(node, cache);
            }
        }
    }

    public static void dfs2(Graph.GNode start) {
        Stack<Graph.GNode> stack = new Stack<>();
        Set<Graph.GNode> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.print(start);
        while (!stack.isEmpty()) {
            Graph.GNode node = stack.pop();
            for (Graph.GNode gn : node.nexts) {
                if (!set.contains(gn)) {
                    System.out.print(gn);
                    set.add(gn);
                    stack.push(node);
                    stack.push(gn);
                    break;
                }
            }
        }
    }
}
