package com.heyu.tree;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.panel.TreeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * description TODO .
 *
 * @author chengxuewen
 * @createTime 2021年08月23日 17:17:00
 */
public class TreeDemo {
    List<Node> nodes = new ArrayList<>();

    public TreeDemo() {

    }

    public TreeDemo(List<Node> nodes) {
        super();
        this.nodes = nodes;
    }

    public String buildTree(List<Node> nodes) {
        TreeDemo treeDemo = new TreeDemo(nodes);
        return treeDemo.buildJSONTree();
    }

    // 构建JSON树形结构
    public String buildJSONTree() {
        List<Node> nodeTree = buildTree();
        String jsonStr = JSON.toJSONString(nodeTree);
        return jsonStr;
    }

    // 构建树形结构
    public List<Node> buildTree() {
        List<Node> treeNodes = new ArrayList<>();
        List<Node> rootNodes = getRootNodes();
        for (Node rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(Node node) {
        List<Node> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (Node child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children);
        }
    }

    // 获取父节点下所有的子节点
    public List<Node> getChildNodes(Node pnode) {
        List<Node> childNodes = new ArrayList<>();
        for (Node n : nodes) {
            if (pnode.getId().equals(n.getParentId())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(Node node) {
        boolean isRootNode = true;
        for (Node n : nodes) {
            if (node.getParentId().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<Node> getRootNodes() {
        List<Node> rootNodes = new ArrayList<>();
        for (Node n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }


    public static List<Node> buildNode() {
        List<Node> nodeList = new ArrayList<>();
        Node node = new Node(0, -1, "地球", "WORD", 1);
        Node node1 = new Node(1, 0, "中国", "CHN", 1);

        Node node2 = new Node(2, 1, "华北区域", "A", 2);
        Node node3 = new Node(3, 1, "华南区域", "B", 2);
        Node node4 = new Node(4, 1, "华东区域", "C", 2);
        Node node5 = new Node(5, 1, "华西区域", "D", 2);
        Node node6 = new Node(6, 1, "华中区域", "E", 2);

        Node node7 = new Node(7, 6, "河南", "豫", 3);
        Node node8 = new Node(8, 6, "湖北", "鄂", 3);
        Node node9 = new Node(9, 6, "湖南", "湘", 3);
        Node node10 = new Node(10, 6, "江西", "赣", 3);

        Node node11 = new Node(11, 7, "郑州", "豫A", 4);
        Node node12 = new Node(12, 7, "开封", "豫B", 4);
        Node node13 = new Node(13, 7, "洛阳", "豫C", 4);
        Node node14 = new Node(14, 7, "南阳", "豫R", 4);

        Node node15 = new Node(15, 11, "金水区", "豫A-1", 5);

        nodeList.add(node);
        nodeList.add(node1);
        nodeList.add(node2);
        nodeList.add(node3);
        nodeList.add(node4);
        nodeList.add(node5);
        nodeList.add(node6);
        nodeList.add(node7);
        nodeList.add(node8);
        nodeList.add(node9);
        nodeList.add(node10);
        nodeList.add(node11);
        nodeList.add(node12);
        nodeList.add(node13);
        nodeList.add(node14);
        nodeList.add(node15);

        return nodeList;
    }

    public static void main(String[] args) {
        List<Node> nodeList = buildNode();
        TreeDemo treeDemo = new TreeDemo();
        String result = treeDemo.buildTree(nodeList);
        System.out.println(result);

    }

}
