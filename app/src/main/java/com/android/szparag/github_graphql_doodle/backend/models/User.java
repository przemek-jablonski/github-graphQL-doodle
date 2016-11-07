package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.utils.Constants;

import static com.android.szparag.github_graphql_doodle.utils.Constants.GraphqlConstants.NAME_BLANK;

/**
 * Created by ciemek on 06/11/2016.
 */
public class User extends GraphQLBaseObject {

    public User() {
        super(NAME_BLANK);
    }

}
