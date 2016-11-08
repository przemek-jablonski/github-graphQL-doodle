package com.android.szparag.github_graphql_doodle.repositories;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator;
import com.android.szparag.github_graphql_doodle.utils.RepoStatsComparator.SortItem;

import java.util.List;

/**
 * Created by ciemek on 07/11/2016.
 */

public interface RepositoryOwnerRepository {

    void saveRepositoryOwner(RepositoryOwner data);

    void sortData();
    void sortData(SortItem sortItem);

    RepositoryOwner getRepositoryOwner();
    List<Repository> getRepositories();
}
