package common;

import java.util.*;

/**
 * 图
 *
 * @author xujungang
 * @date 2021-01-25
 */
public class Graph {

    public Map<Integer, GNode> nodes;
    public Set<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }

    public static class GraphInfo {
        public Graph graph;
        public GNode first;

        public GraphInfo(GNode first) {
            this.first = first;
        }

        public GraphInfo(Graph graph) {
            this.graph = graph;
        }

        public GraphInfo(Graph graph, GNode first) {
            this.graph = graph;
            this.first = first;
        }
    }

    public static GraphInfo generatorGraph(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        GraphInfo graphInfo = new GraphInfo(new Graph());
        String[] nodeStr = str.split(",");
        for (int i = 0; i < nodeStr.length; i++) {
            String[] arr = nodeStr[i].split(":");
            if (arr == null || arr.length != 3) {
                throw new IllegalArgumentException("参数错误,Edge格式必须是weight:fromValue:toValue");
            }
            if (graphInfo.graph.nodes.get(Integer.valueOf(arr[1])) == null) {
                graphInfo.graph.nodes.put(Integer.valueOf(arr[1]), new GNode(Integer.valueOf(arr[1])));
            }
            if (graphInfo.graph.nodes.get(Integer.valueOf(arr[2])) == null) {
                graphInfo.graph.nodes.put(Integer.valueOf(arr[2]), new GNode(Integer.valueOf(arr[2])));
            }
            GNode from = graphInfo.graph.nodes.get(Integer.valueOf(arr[1]));
            if (i == 0) {   // 首个GNode
                graphInfo.first = from;
            }
            GNode to = graphInfo.graph.nodes.get(Integer.valueOf(arr[2]));
            Edge edge = new Edge(Integer.valueOf(arr[0]), from, to);
            graphInfo.graph.edges.add(edge);
            from.nexts.add(to);
            from.out++;
            from.edges.add(edge);
            to.in++;
        }
        return graphInfo;
    }

    public static class GNode {
        public int value;
        public int in;
        public int out;
        public List<GNode> nexts;
        public List<Edge> edges;

        public GNode(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "[" + value + "]";
        }
    }

    public static class Edge {
        public int weigh;
        public GNode from;
        public GNode to;

        public Edge(int weigh, GNode from, GNode to) {
            this.weigh = weigh;
            this.from = from;
            this.to = to;
        }
    }
}
