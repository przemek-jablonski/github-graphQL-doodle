package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.android.szparag.github_graphql_doodle.utils.Constants;

/**
 * Created by ciemek on 06/11/2016.
 */
public class PullRequest extends GraphQLBaseObject {

    public PullRequest() {
        super(Constants.GraphqlConstants.NAME_BLANK);
    }

}
