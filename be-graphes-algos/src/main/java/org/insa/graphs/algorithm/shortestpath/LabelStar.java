package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;

public class LabelStar extends Label {
	
	private float cost;
	
	public LabelStar(Node node, float estimation)
	{
		super(node);
		this.cost = estimation;
	}
	
	@Override
	public float getCostTot()
	{
		
		return (this.cost + this.getCost()) ;
	}
	
	public float getEstimCost()
	{
		return this.cost;
	}
	
	public void setEstimCost(float Cost)
	{
		this.cost = Cost;
	}
	
	
}
