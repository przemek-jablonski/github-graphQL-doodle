package com.android.szparag.github_graphql_doodle.backend.models.graphql.queries;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLConnectionObject;
import com.android.szparag.github_graphql_doodle.utils.Constants;

import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by ciemek on 07/11/2016.
 */

public class RepositoryOwnerQueryFull extends GraphqlBaseQuery{

    private GraphQLObjectType query;

    private final String GRAPH_0BJECT_NAME = "";

    //todo: builder method in superclass? like (Array[](fieldName, fieldType))
    public RepositoryOwnerQueryFull(String argKey, String argValue, String repositoryArgKey, String repositoryArgVal) {
        query = newObject()
                .name("repositoryOwner")
                .description(argKey + ":" + argValue) //todo: FUCK THIS SHIT
                .field(newFieldDefinition()
                        .name("avatarURL")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("login")
                        .type(GraphQLString))
                .field(newFieldDefinition()
                        .name("path")
                        .type(GraphQLString))
                .field(createEdgedObject(new RepositoryQueryShallow().getQuery(), "repositories", repositoryArgKey, repositoryArgVal)) //todo: argument validation
                .build();
    }

    @Override
    public GraphQLObjectType getQuery() {
        return query;
    }

    @Override
    public void setQuery(GraphQLObjectType query) {
        this.query = query;
    }
}
