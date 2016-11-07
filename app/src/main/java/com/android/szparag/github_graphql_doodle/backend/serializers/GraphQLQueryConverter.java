package com.android.szparag.github_graphql_doodle.backend.serializers;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by ciemek on 07/11/2016.
 */

public class GraphQLQueryConverter implements Converter<GraphQLObjectType, RequestBody>{

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final String EXPRESSION_START = "{";
    private final String EXPRESSION_END = "}";
    private final String ARGUMENTS_START = "(";
    private final String ARGUMENTS_END = ")";
    private final String ARGUMENT_KEY_VALUE_SEPARATOR = ":";
    private final String ARGUMENT_VALUE = "\"";
    private final String ARGUMENT_APPEND_NEXT = ",";
    private final String QUERY = "query";
    private final String FIELDS_SEPARATOR = " ";
    private StringBuilder stringBuilder;

    public GraphQLQueryConverter() {
        stringBuilder = new StringBuilder(); //fixme: this should be deleted, dev/debug only
    }

    @Override
    public RequestBody convert(GraphQLObjectType value) throws IOException {
        stringBuilder = new StringBuilder();
        JSONObject json = new JSONObject();

        try {
            json.put(QUERY, convertBaseObjectToquery(value));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return RequestBody.create(MEDIA_TYPE, json.toString());
    }

    public String convertBaseObjectToquery(GraphQLObjectType graphqlType, boolean isRecursivelyCalled) {
        if(!isRecursivelyCalled) stringBuilder.append(EXPRESSION_START);

        makeQuery(graphqlType);
        makeArguments(graphqlType);
        stringBuilder.append(EXPRESSION_START);

        List<GraphQLFieldDefinition> fieldDefinitions = graphqlType.getFieldDefinitions();
        for (int f = 0; f < fieldDefinitions.size(); ++f) {
            if (fieldDefinitions.get(f).getType().getClass() == GraphQLObjectType.class) {
                convertBaseObjectToquery(((GraphQLObjectType) fieldDefinitions.get(f).getType()), true);
            } else {
                stringBuilder.append(FIELDS_SEPARATOR).append(fieldDefinitions.get(f).getName()).append(FIELDS_SEPARATOR);
            }
        }

        if(!isRecursivelyCalled) stringBuilder.append(EXPRESSION_END);
        stringBuilder.append(EXPRESSION_END);
        return stringBuilder.toString();
    }

    public String convertBaseObjectToquery(GraphQLObjectType graphqlType) {
        return convertBaseObjectToquery(graphqlType, false);
    }

    //todo: arguments stuff!

    private void makeQuery(GraphQLObjectType graphQLObjectType) {
        stringBuilder
                .append(graphQLObjectType.getName());
    }

    private void makeArguments(GraphQLObjectType graphQLObjectType) {
        if (graphQLObjectType.getDescription() != null) {
            stringBuilder
                    .append(ARGUMENTS_START)
                    .append(graphQLObjectType.getDescription())
                    .append(ARGUMENTS_END);
        }
    }

}
