package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;

public class LabelStar extends Label {
	
	private float EstimCost;
	
	public LabelStar(Node node, float estimation)
	{
		super(node);
		this.EstimCost = estimation;
	}
	
	@Override
	public float getCostTot()
	{
		
		return (this.EstimCost + this.getCost()) ;
	}
	
	public float getEstimCost()
	{
		return this.EstimCost;
	}
	
	public void setEstimCost(float Cost)
	{
		this.EstimCost = Cost;
	}
	
	
}
