package bfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 */

public class GraphValidTree
{
    public boolean validTree(int n, int[][] edges) 
    {
    	if ( n <= 0 )
    	{
    		throw new IllegalArgumentException("");
    	}
    	if ( n == 1 )
    	{
    		return true;
    	}
    	if ( n != edges.length + 1 )
    	{
    		return false;
    	}
    	
    	//  build graph
    	Map<Integer, Set<Integer>> graph = new HashMap<>();
    	for ( int[] edge : edges )
    	{
    		graph.putIfAbsent( edge[0], new HashSet<>() );
    		graph.putIfAbsent( edge[1],  new HashSet<>() );
    		
    		graph.get( edge[0] ).add( edge[1] );
    		graph.get( edge[1] ).add( edge[0] );
    	}
    	
    	// dfs from a node
    	Set<Integer> visited = new HashSet<>();
    	Set<Integer> discovered = new HashSet<>();
    	int startNode = edges[0][0];
    	boolean isCycleExist = depthFirstSearch( graph, startNode, discovered, visited );
    	
    	if ( isCycleExist 
    			|| visited.size() < n )
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }
    
    /** 
     * @return does cycle exists
     */
    private boolean depthFirstSearch( Map<Integer, Set<Integer>> graph, int startNode, Set<Integer> discovered, Set<Integer> visited )
    {
    	discovered.add( startNode );
    	
    	for ( Integer neighbor : graph.get( startNode ) )
    	{
    		if ( neighbor == startNode )
    		{
    			continue;
    		}    		
    		if ( discovered.contains( neighbor ) 
    				&& !visited.contains( neighbor ) )
    		{
    			return true;
    		}
    		
    		if ( depthFirstSearch( graph, neighbor, discovered, visited ) )
    		{
    			return true;
    		}
    	}
    	
    	visited.add( startNode );
    	return false;
    }
}
