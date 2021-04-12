package org.insa.graphs.algorithm.shortestpath;


import java.util.*;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.*;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        /* initialisation des tableaux et varaiable dont on auras besoin*/
        
    	Graph graph = data.getGraph();
    	Node Origin = data.getOrigin();
    	Node Destination = data.getDestination();
    	int SizeGraph = graph.size();
    	
    	Label Labels[] = new Label[SizeGraph];

    	
    	List<Node> ToTest = graph.getNodes();
    	
    	for(Node node: ToTest)
    	{
    		Labels[node.getId()] = new Label(node);
    	}

    	BinaryHeap<Label> LabelHeap = new BinaryHeap<Label>();
    	
    	/* Initialisation avec le point d'origine */
    	
    	
    	Labels[Origin.getId()].setCost(0);
    	LabelHeap.insert(Labels[Origin.getId()]);
    	
    	Boolean IsFinished = false;

    	
    	while(!LabelHeap.isEmpty() && !IsFinished)
    	{
    		
    		Label CurrentNode = LabelHeap.findMin();
    		LabelHeap.remove(CurrentNode);
    		CurrentNode.setMark(true);
    		
    		// on vérifie que on ne se trouve pas a la fin.
    		
    		if(CurrentNode.getNode() == Destination)
    		{
    			IsFinished = true;
    			continue;
    			
    		}
    		
    		
    		for(Arc arc : CurrentNode.getNode().getSuccessors())
    		{
    			
    			if (!data.isAllowed(arc)) 
    			{
                    continue;
                }
    			
    			Label SuccNode = Labels[arc.getDestination().getId()];
    			
    			if(!SuccNode.isMarked())
    			{
    				if(SuccNode.getCost() > (CurrentNode.getCost() + arc.getLength()))
    				{
    					SuccNode.setCost((CurrentNode.getCost()+arc.getLength()));
    					SuccNode.setFather(CurrentNode.getNode(), arc);
    					LabelHeap.insert(SuccNode);
    					
    				}
    			}
    			
    		}
    		
    		// pour savoir si on arrive a la fin du programme on test si la destination a un predecesseur (father)
    		
    		
    		
    	}
    	if(Labels[Destination.getId()].getFather() == null)
		{
			solution = new ShortestPathSolution(data, Status.INFEASIBLE);
		} 
		else 
		{
			ArrayList<Arc> arcs = new ArrayList<Arc>();
			Arc ArcFromfather = Labels[Destination.getId()].getFatherArc();
			while(ArcFromfather != null)
			{
				arcs.add(ArcFromfather);
				ArcFromfather = Labels[ArcFromfather.getOrigin().getId()].getFatherArc();
			}
			
			Collections.reverse(arcs);
			
			solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
		}
    	

    	
        return solution;
    }
    
    
}
