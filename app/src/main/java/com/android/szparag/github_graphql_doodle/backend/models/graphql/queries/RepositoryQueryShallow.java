package com.android.szparag.github_graphql_doodle.backend.models.graphql.queries;

import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by ciemek on 07/11/2016.
 */

public class RepositoryQueryShallow extends GraphqlBaseQuery {

    private GraphQLObjectType query;

    public RepositoryQueryShallow() {
        super();
        query = newObject()
                .name("repository")
                .field(newFieldDefinition()
                        .name("name")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("description")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("path")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("homepageURL")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("forks")
                        .type(createTotalCountObject("forks")))
                .field(newFieldDefinition()
                        .name("watchers")
                        .type(createTotalCountObject("watchers")))
                .field(newFieldDefinition()
                        .name("stargazers")
                        .type(createTotalCountObject("stargazers")))
                .build();
    }


    @Override
    public GraphQLObjectType getQuery() {
        return query;
    }
}
