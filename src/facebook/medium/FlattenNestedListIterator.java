package facebook.medium;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import utility.NestedInteger;

/*
Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:
Given the list [[1,1],2,[1,1]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
Given the list [1,[4,[6]]],

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * */

public class FlattenNestedListIterator implements Iterator<Integer> 
{
	private Stack<Iterator<NestedInteger>> nestedIter;	
	private NestedInteger peekedVal;
	
    public FlattenNestedListIterator( List<NestedInteger> nestedList ) 
    {
    	if ( nestedList == null )
    	{
    		throw new IllegalArgumentException();
    	}
    	
    	nestedIter = new Stack<>();
    	nestedIter.push( nestedList.iterator() );
    	peekedVal = null;
    }

    @Override
    public Integer next()
    {
    	NestedInteger lastPeekedVal = peekedVal;
    	peekedVal = null;    	
    	return lastPeekedVal.getInteger();
    }

    @Override
    public boolean hasNext() 
    {
    	while ( !nestedIter.isEmpty() )
    	{
	        Iterator<NestedInteger> currIter = nestedIter.pop();
	    	while ( currIter.hasNext() )
	    	{
	    		peekedVal = currIter.next();
	    		if ( !peekedVal.isInteger() )
	    		{
	    			nestedIter.push( currIter );
	    			currIter = peekedVal.getList().iterator();
	    		}
	    		else
	    		{
	    			nestedIter.push( currIter );
	    			return true;
	    		}
	    	}
    	}
    	return false;
    }
}