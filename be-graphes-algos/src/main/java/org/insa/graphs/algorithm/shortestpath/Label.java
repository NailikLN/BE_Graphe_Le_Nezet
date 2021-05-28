package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;


public class Label implements Comparable<Label>{
	
	private boolean marque;
	private float cout;
	private Node node;
	private Node father;
	private Arc fatherArc;
	
	
	public Label(Node node)
	{
		this.node = node;
		this.marque = false;
		this.cout = Float.POSITIVE_INFINITY;
		this.father = null;
		
	}
	
	public float getCost()
	{
		return this.cout;
	}
	
	public void setCost(float cost)
	{
		this.cout = cost;
	}
	
	public boolean isMarked()
	{
		return this.marque;
	}
	
	public void setMark(boolean marque)
	{
		this.marque = marque;
	}
	
	public Node getNode()
	{
		return this.node;
	}
	
	public Node getFather()
	{
		return this.father;
	}
	
	public Arc getFatherArc()
	{
		return this.fatherArc;
	}

	public float getCostTot()
	{
		return this.cout;
	}
	
	@Override
	public int compareTo(Label o) 
	{
		int result;
		if(this.getCostTot() < o.getCostTot())
		{
			result = -1;
		}
		else if(this.getCostTot() > o.getCostTot())
		{
			result = 1;
		}
		else
		{
			result = 0;
		}
		
		return result;
	}

	public void setFather(Node node2, Arc arc) {
		this.father = node2;
		this.fatherArc = arc;
	}


}
