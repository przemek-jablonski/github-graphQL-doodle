package com.android.szparag.github_graphql_doodle.backend.models.graphql.queries;

import com.android.szparag.github_graphql_doodle.utils.Constants;

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
    protected GraphQLFieldDefinition createEdgedObject(GraphQLObjectType nestedType, String queryName, String outerArgumentKey, String outerArgumentValue) {

        GraphQLObjectType graphConnection = newObject()
                .name(queryName)
                .description(outerArgumentKey + ":" + outerArgumentValue)
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

        return newFieldDefinition().name(Constants.GraphqlConstants.NAME_BLANK).type(graphConnection).build();
    }
}
