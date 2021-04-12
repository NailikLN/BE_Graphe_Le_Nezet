package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        ArrayList<Label> Labels = null;
    	Graph graph = data.getGraph();
    	for(int i = 0; i < graph.size(); i++)
        {
        	 Labels.add(new Label(graph.getNodes().get(i), false, (Integer)null));
        }

    	
        return solution;
    }

}
