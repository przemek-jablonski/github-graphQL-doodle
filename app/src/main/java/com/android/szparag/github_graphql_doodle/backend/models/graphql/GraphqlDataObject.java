package com.android.szparag.github_graphql_doodle.backend.models.graphql;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlDataObject {

    Repository repository;
    RepositoryOwner repositoryOwner;

    public Repository getRepository() {
        return repository;
    }

    public RepositoryOwner getRepositoryOwner() {
        return repositoryOwner;
    }
}
