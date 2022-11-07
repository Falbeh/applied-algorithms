package assignment3;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import assignment3.DancingLinks.DancingLinksMatrix;
import assignment3.DancingLinks.DancingLinksSudokuSolver;
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
        expectedResult.get(1).add(1);
        expectedResult.get(1).add(3);
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
        expectedResult.get(0).add(3);
        expectedResult.get(0).add(5);
        expectedResult.get(0).add(6);
        expectedResult.get(2).add(1);
        expectedResult.get(1).add(2);
        expectedResult.get(2).add(4);
        expectedResult.get(1).add(7);
        
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
    @Test
    public void testDancingLinksSudokuSolver() {  
        
        assertArrayEquals(new int[][] {{1, 2, 4, 3}, 
                                        {3, 4, 1, 2}, 
                                        {2, 1, 3, 4}, 
                                        {4, 3, 2, 1 }}, 
            DancingLinksSudokuSolver.DLXSolver(new int[][] {
            {0, 0, 0, 3},
            {0, 4, 0, 0},
            {0, 1, 0, 0},
            {0, 3, 2, 0}
        },2)); 
        
        assertArrayEquals(new int[][] {{3, 1, 6, 5, 7, 8, 4, 9, 2},
                                        {5, 2, 9, 1, 3, 4, 7, 6, 8},
                                        {4, 8, 7, 6, 2, 9, 5, 3, 1}, 
                                        {2, 6, 3, 4, 1, 5, 9, 8, 7}, 
                                        {9, 7, 4, 8, 6, 3, 1, 2, 5}, 
                                        {8, 5, 1, 7, 9, 2, 6, 4, 3}, 
                                        {1, 3, 8, 9, 4, 7, 2, 5, 6}, 
                                        {6, 9, 2, 3, 5, 1, 8, 7, 4}, 
                                        {7, 4, 5, 2, 8, 6, 3, 1, 9} }, 
            DancingLinksSudokuSolver.DLXSolver(new int[][] {
                {3, 0, 6, 5, 0, 8, 4, 0, 0},
                {5, 2, 0, 0, 0, 0, 0, 0, 0},
                {0, 8, 7, 0, 0, 0, 0, 3, 1},
                {0, 0, 3, 0, 1, 0, 0, 8, 0},
                {9, 0, 0, 8, 6, 3, 0, 0, 5},
                {0, 5, 0, 0, 9, 0, 6, 0, 0},
                {1, 3, 0, 0, 0, 0, 2, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 4},
                {0, 0, 5, 2, 0, 6, 3, 0, 0}
        },3));  

        assertArrayEquals(new int[][] {{5, 14, 9, 15, 1, 2, 3, 6, 4, 8, 7, 10, 11, 13, 12, 16}, 
                                        {16, 3, 2, 12, 4, 5, 8, 7, 1, 6, 11, 13, 10, 9, 14, 15}, 
                                        {4, 7, 13, 1, 9, 10, 11, 12, 2, 14, 15, 16, 3, 5, 6, 8}, 
                                        {11, 8, 10, 6, 13, 14, 15, 16, 3, 5, 9, 12, 1, 2, 7, 4}, 
                                        {1, 2, 3, 5, 8, 15, 7, 4, 9, 10, 6, 14, 12, 16, 11, 13}, 
                                        {7, 4, 8, 9, 6, 12, 13, 1, 5, 11, 16, 2, 15, 3, 10, 14}, 
                                        {6, 10, 12, 14, 2, 11, 16, 9, 8, 13, 3, 15, 4, 7, 5, 1}, 
                                        {13, 11, 15, 16, 14, 3, 5, 10, 7, 1, 12, 4, 8, 6, 2, 9}, 
                                        {3, 1, 4, 8, 5, 6, 12, 14, 13, 7, 2, 11, 16, 15, 9, 10}, 
                                        {2, 9, 6, 7, 11, 4, 10, 15, 14, 16, 1, 5, 13, 12, 8, 3}, 
                                        {14, 5, 16, 13, 3, 1, 2, 8, 12, 15, 10, 9, 7, 11, 4, 6}, 
                                        {12, 15, 11, 10, 16, 7, 9, 13, 6, 3, 4, 8, 5, 14, 1, 2}, 
                                        {10, 6, 1, 2, 12, 16, 14, 11, 15, 4, 5, 3, 9, 8, 13, 7}, 
                                        {9, 12, 7, 4, 15, 8, 6, 5, 16, 2, 13, 1, 14, 10, 3, 11}, 
                                        {8, 16, 5, 3, 10, 13, 1, 2, 11, 9, 14, 7, 6, 4, 15, 12}, 
                                        {15, 13, 14, 11, 7, 9, 4, 3, 10, 12, 8, 6, 2, 1, 16, 5} }, 
            DancingLinksSudokuSolver.DLXSolver(new int[][] {
                {5, 0, 9, 15, 0, 2, 3, 0, 4, 0, 0, 10, 0, 0, 12, 16}, 
                {0, 0, 0, 12, 0, 0, 0, 0, 1, 6, 11, 13, 10, 9, 14, 0}, 
                {4, 7, 13, 1, 9, 10, 11, 0, 2, 14, 0, 16, 0, 5, 0, 8}, 
                {11, 0, 10, 0, 13, 14, 0, 16, 0, 0, 9, 12, 1, 2, 0, 0}, 
                {0, 0, 3, 5, 8, 0, 0, 0, 9, 0, 0, 14, 0, 0, 0, 0}, 
                {0, 4, 8, 0, 6, 12, 13, 0, 5, 11, 16, 0, 15, 0, 0, 0}, 
                {6, 0, 12, 0, 2, 11, 16, 9, 8, 13, 0, 15, 4, 0, 5, 1}, 
                {13, 11, 15, 0, 0, 3, 5, 0, 0, 0, 12, 4, 0, 0, 2, 9}, 
                {3, 1, 4, 8, 5, 6, 12, 14, 13, 0, 2, 11, 0, 0, 9, 10}, 
                {2, 9, 0, 0, 11, 4, 0, 0, 14, 16, 1, 0, 13, 0, 0, 3}, 
                {0, 0, 0, 13, 0, 1, 2, 8, 0, 0, 0, 0, 7, 11, 4, 0}, 
                {0, 15, 11, 10, 16, 7, 0, 13, 0, 3, 0, 8, 0, 14, 0, 2}, 
                {0, 6, 0, 0, 12, 16, 0, 0, 15, 4, 5, 0, 9, 8, 13, 7}, 
                {0, 12, 0, 4, 15, 0, 6, 5, 16, 0, 0, 1, 0, 10, 3, 0}, 
                {8, 16, 0, 3, 10, 13, 1, 0, 11, 9, 14, 7, 6, 0, 15, 0}, 
                {15, 0, 14, 11, 7, 0, 4, 0, 10, 0, 8, 6, 0, 1, 16, 0} 
        },4)); 
    }
}

