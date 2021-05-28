package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

import java.util.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    public Label createLabel(Node node,ShortestPathData data) {
        return(new Label(node));
    }
    
    

    @Override
    protected ShortestPathSolution doRun() {
        
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        /* initialisation des tableaux et variable dont on auras besoin*/
        
    	Graph graph = data.getGraph();
    	Node Origin = data.getOrigin();
    	Node Destination = data.getDestination();
    	int SizeGraph = graph.size();
    	
    	Label Labels[] = new Label[SizeGraph];
    	
    	//first event
    	
    	List<Node> ToTest = graph.getNodes();
    	
    	for(Node node: ToTest)
    	{
    		Labels[node.getId()] = createLabel(node,data);
    	}

    	BinaryHeap<Label> LabelHeap = new BinaryHeap<Label>();
    	
    	/* Initialisation avec le point d'origine */
    	
    	
    	Labels[Origin.getId()].setCost(0);
    	LabelHeap.insert(Labels[Origin.getId()]);
    	 notifyOriginProcessed(Origin);
    	
    	Boolean IsFinished = false;

    	
    	while(!LabelHeap.isEmpty() && !IsFinished)
    	{
    		
    		Label CurrentNode = LabelHeap.findMin();
    		notifyNodeReached(CurrentNode.getNode());
    		notifyNodeMarked(CurrentNode.getNode());
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
    				if(SuccNode.getCostTot() > (CurrentNode.getCostTot() + arc.getLength()))
    				{
    					SuccNode.setCost((CurrentNode.getCostTot()+arc.getLength()));
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
			notifyDestinationReached(Destination);
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
