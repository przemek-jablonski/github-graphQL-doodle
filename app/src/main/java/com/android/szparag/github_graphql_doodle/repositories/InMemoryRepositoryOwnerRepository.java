package com.android.szparag.github_graphql_doodle.repositories;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import static com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator.SortItem.STARS;

/**
 * Created by ciemek on 07/11/2016.
 */
//this name is ridiculous
public class InMemoryRepositoryOwnerRepository implements RepositoryOwnerRepository {

    RepositoryOwner repositoryOwner;
    List<Repository> repositories;

    RepoStatsComparator comparator;


    public InMemoryRepositoryOwnerRepository(RepoStatsComparator comparator) {
        if (comparator == null) {
            comparator = new RepoStatsComparator();
        }
        this.comparator = comparator;
    }


    @Override
    public void saveRepositoryOwner(RepositoryOwner data) {
        if (data == null) {
            return;
        }
        repositoryOwner = data;
        repositories = createRepositoriesList(data);
        sortData();
    }

    @Override
    public RepositoryOwner getRepositoryOwner() {
        return repositoryOwner;
    }

    @Override
    public List<Repository> getRepositories() {
        return repositories;
    }

    @Override
    public void sortData(RepoStatsComparator.SortItem sortItem) {
        Collections.sort(repositories, comparator.sortBy(sortItem));
    }

    @Override
    public void sortData() {
        sortData(STARS);
    }

    private LinkedList<Repository> createRepositoriesList(RepositoryOwner data) {
        LinkedList<Repository> repositoriesList = new LinkedList<>();
        for (int i = 0; i < data.getRepositories().getEdges().size(); ++i) {
            data.getRepository();
            repositoriesList.add(data.getRepositories().getEdges().get(i).getNode());
        }
        return repositoriesList;
    }
}
