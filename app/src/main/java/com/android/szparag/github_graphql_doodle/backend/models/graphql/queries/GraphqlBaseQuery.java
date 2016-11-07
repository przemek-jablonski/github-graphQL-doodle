package com.android.szparag.github_graphql_doodle.backend.models.graphql.queries;

import java.util.List;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

import static graphql.Scalars.GraphQLInt;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

/**
 * Created by ciemek on 07/11/2016.
 */

public abstract class GraphqlBaseQuery {

    public abstract GraphQLObjectType getQuery();

    protected GraphQLObjectType transformNestedObjectToNode(GraphQLObjectType nestedType) {
        List<GraphQLFieldDefinition> fieldDefinitions = nestedType.getFieldDefinitions();
        return newObject().name("node").fields(fieldDefinitions).build();
    }

    protected GraphQLObjectType createTotalCountObject(String fieldName) {
        return newObject()
                .name(fieldName)
                .field(newFieldDefinition()
                        .name("totalCount")
                        .type(GraphQLInt))
                .build();
    }

    //todo: graphql interface instead of ArgKey + ":" + ArgVal
    protected GraphQLFieldDefinition createEdgedObject(GraphQLObjectType nestedType, String queryName, String argumentKey, String argumentValue) {

        GraphQLObjectType graphConnection = newObject()
                .name(queryName)
                .description(argumentKey + ":" + argumentValue) //// TODO// : 07/11/2016
                .field(newFieldDefinition()
                        .name("totalCount")
                        .type(GraphQLInt)
                )
                .field(newFieldDefinition()
                        .name("edges")
                        .type(newObject()
                                .name("edges")
                                .field(newFieldDefinition()
                                        .name("node")
                                        .type(transformNestedObjectToNode(nestedType)))
                                .build()
                        )
                )
                .build();

        return newFieldDefinition().name("").type(graphConnection).build();
    }
}
