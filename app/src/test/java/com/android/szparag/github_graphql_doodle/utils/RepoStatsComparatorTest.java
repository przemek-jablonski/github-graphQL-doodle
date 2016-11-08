package com.android.szparag.github_graphql_doodle.utils;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator.SortItem.FORKS;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by ciemek on 07/11/2016.
 */
public class RepoStatsComparatorTest {

    RepoStatsComparator comparator;

    @Mock
    Repository data1;

    @Mock
    Repository data2;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        data1 = mock(Repository.class);
        comparator = new RepoStatsComparator();
    }

    @Test
    public void sortBy_passedItemValid() {
        comparator.sortBy(FORKS);

        assertEquals(FORKS, comparator.sortItem);
        assertEquals(
                comparator.getClass(),
                comparator.sortBy((RepoStatsComparator.SortItem) any()).getClass()
        );
    }

    @Test
    public void sortBy_passedItemNull() {
        RepoStatsComparator.SortItem oldVal = comparator.sortItem;
        comparator.sortBy(null);

        assertEquals(oldVal, comparator.sortItem);
        assertSame(RepoStatsComparator.class, comparator.sortBy(null).getClass());
    }

}