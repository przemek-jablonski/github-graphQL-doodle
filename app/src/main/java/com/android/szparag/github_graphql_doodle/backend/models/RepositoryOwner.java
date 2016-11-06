package com.android.szparag.github_graphql_doodle.backend.models;

import android.support.annotation.Nullable;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphqlDontFetch;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlConnectionObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlEdgeObject;

import java.util.LinkedHashMap;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.ARGUMENT_LOGIN;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORY_OWNER;

/**
 * Created by ciemek on 06/11/2016.
 */

public class RepositoryOwner extends GraphqlBaseObject {

    public RepositoryOwner(@Nullable LinkedHashMap<String, String> args) {
        super(NAME_REPOSITORY_OWNER);
        super.availableArgs = new String[] {
                ARGUMENT_LOGIN
        };
        checkArguments(args);
    }


    private String avatarURL;
    private String login;
    private String path;
//    private GraphqlConnectionObject<GraphqlEdgeObject<Repository>> repositories; //todo: we can query for repository OR/AND repositories!

//    @GraphqlFetchMaxLevels(value=)
//    private RepositoryConnection repositories;
    private GraphqlConnectionObject<GraphqlEdgeObject<Repository>> repositories;

    @GraphqlDontFetch
    private Repository repository;  //todo: figure out some solution for this


    public String getAvatarURL() {
        return avatarURL;
    }

    public String getLogin() {
        return login;
    }

    public String getPath() {
        return path;
    }

//    public RepositoryConnection getRepositories() {
//        return repositories;
//    }

    public Repository getRepository() {
        return repository;
    }

    //    public void set(String avatarURL, String login, String path,
//                    RepositoryConnection repositories, Repository repository) {
//        this.avatarURL = avatarURL;
//        this.login = login;
//        this.path = path;
//        this.repositories = repositories;
//        this.repository = repository;
//    }
}
