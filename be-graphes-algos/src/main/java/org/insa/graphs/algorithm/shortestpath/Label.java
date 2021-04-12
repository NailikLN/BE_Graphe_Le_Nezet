package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;


public class Label{
	
	private boolean marque = false;
	
	private int cout = (Integer)null;
	
	private Node node = null;
	
	
	public Label(Node node, boolean marque,int cout)
	{
		this.node = node;
		this.marque = marque;
		this.cout = cout;
		
	}
	
	public int getCost()
	{
		return this.cout;
	}
	
	public boolean isMarked()
	{
		return this.marque;
	}
	
	public Node getNode()
	{
		return this.node;
	}
}
