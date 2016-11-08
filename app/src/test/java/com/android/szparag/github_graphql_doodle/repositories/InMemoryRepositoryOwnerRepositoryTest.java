package com.android.szparag.github_graphql_doodle.repositories;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ciemek on 07/11/2016.
 */
public class InMemoryRepositoryOwnerRepositoryTest {

    @Mock
    RepositoryOwner mockRepositoryOwner;

    @Mock
    List<Repository> mockRepositories;

    @Mock
    RepoStatsComparator mockComparator;

    RepositoryOwnerRepository dataRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        dataRepository = new InMemoryRepositoryOwnerRepository(mockComparator);
        dataRepository.saveRepositoryOwner(new RepositoryOwner());
    }

    @Test
    public void saveRepositoryOwner_ifDataIsNullNoInteractions() throws Exception {
        dataRepository = new InMemoryRepositoryOwnerRepository(mockComparator);
        dataRepository.saveRepositoryOwner(null);
        verifyZeroInteractions(mockRepositories, mockRepositoryOwner);
    }

    @Test
    public void saveRepositoryOwner_ifDataIsValidOneSorting() throws Exception {
        verify(mockComparator, times(1)).sortBy(((RepoStatsComparator.SortItem) any()));
    }

    @Test
    public void getRepositoryOwner() throws Exception {
        assertNotNull(dataRepository.getRepositoryOwner());
    }

    @Test
    public void getRepositoryOwner_savingAgainWithNull() throws Exception {
        dataRepository.saveRepositoryOwner(null);
        assertNotNull(dataRepository.getRepositoryOwner());
    }

    @Test
    public void getRepositories() throws Exception {
        assertNotNull(dataRepository.getRepositoryOwner());
        assertNotNull(dataRepository.getRepositories());
    }

    @Test
    public void getRepositories_savingAgainWithNull() throws Exception {
        dataRepository.saveRepositoryOwner(null);
        assertNotNull(dataRepository.getRepositoryOwner());
        assertNotNull(dataRepository.getRepositories());
    }

    @Test
    public void sortData_noArguments() throws Exception {
        dataRepository.sortData();
        verify(mockComparator, atLeastOnce()).sortBy(RepoStatsComparator.SortItem.STARS);
    }

}