package com.android.szparag.github_graphql_doodle.utils;

/**
 * Created by ciemek on 04/11/2016.
 */


public class Constants {

    public static final String GITHUB_GRAPHQL_ENDPOINT = "graphql_endpoint";
    public static final String GITHUB_GRAPHQL_APIKEY = "graphql_apikey";

    public static final String RETROFIT_ADAPTER_GRAPHQL = "retrofit_adapter_graphql";
    public static final String RETROFIT_ADAPTER_REST = "retrofit_adapter_rest";

    public static final String LOG_TAG_MISC = "MISC";
    public static final String LOG_TAG_RETROFIT = "RETROFIT";
    public static final String LOG_TAG_EXCEPTION = "EXCEPTION";

    public class GraphqlConstants {
        public static final String ARGUMENT_FIRST = "first";
        public static final String ARGUMENT_AFTER = "after";
        public static final String ARGUMENT_LAST = "last";
        public static final String ARGUMENT_BEFORE = "before";
        public static final String ARGUMENT_PRIVACY = "privacy";
        public static final String ARGUMENT_ISFORK = "isFork";
        public static final String ARGUMENT_ORDERBY = "orderBy";
        public static final String ARGUMENT_NAME = "name";
        public static final String ARGUMENT_LOGIN = "login";

        public static final String NAME_REPOSITORY_OWNER = "repositoryOwner";
        public static final String NAME_REPOSITORIES = "repositories";
        public static final String NAME_REPOSITORY = "repository";
        public static final String NAME_VIEWER = "viewer";
        public static final String NAME_BLANK = "";
    }

}
