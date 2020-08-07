package com.algdat.utils;

import java.util.List;
import java.util.Random;

import com.algdat.interfaces.GraphNodeBase;
import com.algdat.interfaces.GraphNodeUnweighted;
import com.algdat.interfaces.GraphNodeWeighted;

public class GraphUtils {

    public static <T extends GraphNodeWeighted<T>> String weightedGraphToStringDetailed(List<T> nodes) {
        String ret = "Graph: \n";


        for (T node : nodes) {
            ret += "Node " + node.getId() + ":\nEdges (id, weight): [ ";
            for (T edge : node.getEdges().keySet()) {
                ret += "(" + edge.getId() + ", " + node.getEdges().get(edge) + ") ";
            }
            ret += "]\n\n";
        }

        return ret;
    }

    public static <T extends GraphNodeUnweighted<T>> String unweightedGraphToStringDetailed(List<T> nodes) {
        String ret = "Graph: \n";


        for (T node : nodes) {
            ret += "Node " + node.getId() + ":\nEdges: [ ";
            for (T edge : node.getEdges()) {
                ret += edge.getId() + " ";
            }
            ret += "]\n\n";
        }

        return ret;
    }

    public static <T extends GraphNodeBase<T>> String nodesToStringSimple(List<T> nodes) {
        String ret = "[ ";

        for (T node : nodes) {
            ret += node.getId() + " ";
        }

        return ret + "]";
    }

    public static <T extends GraphNodeBase<T>> String nodesToStringSimpleReversed(List<T> nodes) {
        String ret = " ]";

        for (T node : nodes) {
            ret = " " + node.getId() + ret;
        }

        return "[" + ret;
    }

    // Use this to visualize graph on https://graphonline.ru/en/
    public static <T extends GraphNodeWeighted<T>> String weightedGraphToGraphML(List<T> nodes, boolean directed) {
        String xml = "<?xml version='1.0' encoding='UTF-8'?><graphml><graph id='Graph' uidGraph='0' uidEdge='0'>";

        for (GraphNodeWeighted<T> node : nodes) {
            int x = randomNumber(0, 500);
            int y = randomNumber(0, 500);
            xml += "<node positionX='" + x + "' positionY='" + y + "' id='" + node.getId() + "' mainText='" + node.getId() + "' upText=''/>";
        }

        int edgeId = 10002;
        for (GraphNodeWeighted<T> node : nodes) {
            for (GraphNodeWeighted<T> edge : node.getEdges().keySet()) {
                if (directed || Integer.parseInt(node.getId()) < Integer.parseInt(edge.getId())) {
                    xml += "<edge source='" + node.getId() + "' target='" + edge.getId() + "' isDirect='" + Boolean.toString(directed) + "' weight='" + node.getEdges().get(edge) + "' useWeight='true' id='" + edgeId + "' text='' upText='' arrayStyleStart='' arrayStyleFinish='' model_width='4' model_type='0' model_curvedValue='0.1'/>";
                    edgeId++;
                }
            }
        }

        xml += "</graph></graphml>";

        return xml;
    }

    // Use this to visualize graph on https://graphonline.ru/en/
    public static <T extends GraphNodeUnweighted<T>> String unweightedGraphToGraphML(List<T> nodes, boolean directed) {
        String xml = "<?xml version='1.0' encoding='UTF-8'?><graphml><graph id='Graph' uidGraph='0' uidEdge='0'>";

        for (GraphNodeUnweighted<T> node : nodes) {
            int x = randomNumber(0, 500);
            int y = randomNumber(0, 500);
            xml += "<node positionX='" + x + "' positionY='" + y + "' id='" + node.getId() + "' mainText='" + node.getId() + "' upText=''/>";
        }

        int edgeId = 10002;
        for (GraphNodeUnweighted<T> node : nodes) {
            for (GraphNodeUnweighted<T> edge : node.getEdges()) {
                if (directed || Integer.parseInt(node.getId()) < Integer.parseInt(edge.getId())) {
                    xml += "<edge source='" + node.getId() + "' target='" + edge.getId() + "' isDirect='" + Boolean.toString(directed) + "' weight='0' useWeight='false' id='" + edgeId + "' text='' upText='' arrayStyleStart='' arrayStyleFinish='' model_width='4' model_type='0' model_curvedValue='0.1'/>";
                    edgeId++;
                }
            }
        }

        xml += "</graph></graphml>";

        return xml;
    }

	private static int randomNumber(int min, int max) {
        Random numGen = new Random();
		return numGen.nextInt(max - min) + min;
	}
}
