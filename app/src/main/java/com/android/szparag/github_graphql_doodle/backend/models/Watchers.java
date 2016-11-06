package com.android.szparag.github_graphql_doodle.backend.models;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;

/**
 * Created by ciemek on 05/11/2016.
 */

public class Watchers extends GraphqlBaseObject {

    public Watchers(boolean hasArguments, String argValue) {
        super("watchers", false, argValue);
    }

    //todo: too much public shit in models!
    public  Integer totalCount;
}
