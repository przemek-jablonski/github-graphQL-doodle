package com.android.szparag.github_graphql_doodle.backend.models;

import android.support.annotation.Nullable;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLDontFetch;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLRestrictDepth;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLConnectionObject;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLEdgeObject;

import java.util.LinkedHashMap;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.ARGUMENT_LOGIN;
import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_REPOSITORY_OWNER;

/**
 * Created by ciemek on 06/11/2016.
 */

public class RepositoryOwner extends GraphQLBaseObject {

    public RepositoryOwner(@Nullable LinkedHashMap<String, String> args) {
        super(NAME_REPOSITORY_OWNER);
        super.availableArgs = new String[] {
                ARGUMENT_LOGIN
        };
        checkArguments(args);
        repositories = new GraphQLConnectionObject<>();
    }


    //todo: http://stackoverflow.com/questions/2211002/why-not-abstract-fields

    private String avatarURL;
    private String login;
    private String path;

    @GraphQLRestrictDepth(maxLevel = 2)
    private GraphQLConnectionObject<GraphQLEdgeObject<Repository>> repositories;

    @GraphQLDontFetch
    private Repository repository;  //todo: figure out some solution for this


    public String getAvatarURL() {
        return avatarURL;
    }

}
