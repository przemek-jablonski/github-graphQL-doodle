package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphQLRequestBodyConverter<G> implements Converter<G, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final String EXPRESSION_START = "{";
    private final String EXPRESSION_END = "}";
    private final String ARGUMENTS_START = "(";
    private final String ARGUMENTS_END = ")";
    private final String ARGUMENT_KEY_VALUE_SEPARATOR = ":";
    private final String ARGUMENT_APPEND_NEXT = ",";


    @Override
    public RequestBody convert(G value) throws IOException, ClassCastException {

        String serializedValueString = "";

        if (value instanceof GraphqlBaseObject) {
            GraphqlBaseObject graphqlBaseObject = ((GraphqlBaseObject) value);
            serializedValueString = insertExpression(
                    buildGraphqlQuery(
                            graphqlBaseObject.getSerializableName(),
                            graphqlBaseObject.hasArguments(),
                            graphqlBaseObject.getArgKey(),
                            graphqlBaseObject.getArgValue()
                    ), insertExpression(
                            buildGraphqlFields(
                                    graphqlBaseObject
                            )
                    )
            );

        }

        JSONObject object = new JSONObject();
        try {
            object.put("query", serializedValueString);
        } catch (JSONException e) {
            e.printStackTrace(); //todo: something more elaborate here
        }
        return RequestBody.create(MEDIA_TYPE, object.toString());
    }

    private String buildGraphqlFields(GraphqlBaseObject graphqlBaseObject) /*throws IllegalAccessException*/ {
        Field[] fields = graphqlBaseObject.getClass().getDeclaredFields();

        StringBuilder queryFieldsBuilder = new StringBuilder();
        for (int i = 0; i < fields.length; ++i) {
//            if (fields[i].getClass().isAssignableFrom(GraphqlBaseObject.class)) {
//                GraphqlBaseObject childField = (GraphqlBaseObject) fields[i].get(graphqlBaseObject);
//                buildGraphqlQuery(
//                        childField.getSerializableName(),
//                        childField.hasArguments(),
//                        childField.getArgKey(),
//                        childField.getArgValue()
//                );
//            }
            queryFieldsBuilder.append(fields[i].getName());
            if (i != fields.length-1) {
                queryFieldsBuilder.append(" ");
            }
        }
        return queryFieldsBuilder.toString();
    }

    //todo: map instead of single argument (key-val) pair
    private String buildGraphqlQuery(String serializableName, boolean arguments, String argumentKey, String argumentValue) {
        StringBuilder query = new StringBuilder();
        query.append(serializableName);
        if(arguments) {
            query.append(ARGUMENTS_START)
                    .append(argumentKey)
                    .append(ARGUMENT_KEY_VALUE_SEPARATOR)
                    .append("\"")
                    .append(argumentValue)
                    .append("\"")
                    .append(ARGUMENTS_END);
        }
        return query.toString();
    }

    private String insertExpression(String... expressions) {
        StringBuilder expressionWithBraces = new StringBuilder();
        expressionWithBraces.append(EXPRESSION_START);
        for (int e = 0; e < expressions.length; ++e) {
            expressionWithBraces.append(expressions[e]);
        }
        expressionWithBraces.append(EXPRESSION_END);
        return expressionWithBraces.toString();
    }

}
