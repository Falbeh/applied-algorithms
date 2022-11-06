package assignment3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import assignment3.DancingLinks.DancingLinksMatrix;
import assignment3.DancingLinks.DLXSearch;
import assignment3.DancingLinks.DLXSearchCount;

public class DancingLinksTest {
    @Test
    public void testDLXSearchCount() { 
        // 2 solution test
        assertEquals(2, new DLXSearchCount().search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {1,0,1},
            {1,1,0},
            {0,1,0},
            {0,0,1} 
        }),new ArrayList<>(),0));

        // 1 solution test
        assertEquals(1, new DLXSearchCount().search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1} 
        }),new ArrayList<>(),0));

        // 0 solution test
        assertEquals(0, new DLXSearchCount().search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1} 
        }),new ArrayList<>(),0));
    }


    @Test
    public void testDLXSearch() { 
        List<List<Integer>> expectedResult = new ArrayList<>();

        expectedResult.add(new ArrayList<>());
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add(2);
        expectedResult.get(1).add(1);
        expectedResult.get(1).add(3);

        // This call is expected to return the list [[2],[1,3]]
        assertEquals(expectedResult, DLXSearch.search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {1,0,1},
            {1,1,0},
            {0,1,0},
            {0,0,1} 
        }),new ArrayList<>()));

        expectedResult.clear();
        expectedResult.add(new ArrayList<>());
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add(2);
        expectedResult.get(1).add(3);
        expectedResult.get(1).add(1);
        // This call is expected to return the list [[2],[1,3]]
        assertEquals(expectedResult, DLXSearch.search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {1,0,1},
            {1,1,0},
            {0,1,0} 
        }),new ArrayList<>()));


        expectedResult.clear();
        expectedResult.add(new ArrayList<>());
        expectedResult.add(new ArrayList<>());
        expectedResult.add(new ArrayList<>());
        expectedResult.get(0).add(6);
        expectedResult.get(0).add(3);
        expectedResult.get(0).add(5);
        expectedResult.get(1).add(7);
        expectedResult.get(1).add(2);
        expectedResult.get(2).add(1);
        expectedResult.get(2).add(4);
        // This call is expected to return the list [[6, 3, 5], [7, 2], [1, 4]]
        assertEquals(expectedResult, DLXSearch.search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {0, 0, 1, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1} 
        }),new ArrayList<>()));


        expectedResult.clear();
        // This should return empty list as there are no solutions
        assertEquals(expectedResult, DLXSearch.search(DancingLinksMatrix.createDancingLinks(new int[][] {
            {0, 0, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 1} 
        }),new ArrayList<>()));
    }
}
