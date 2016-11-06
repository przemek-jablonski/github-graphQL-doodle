package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphqlBaseObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
    private final String QUERY = "query";


    //todo: delete and refactor, so that there will be only 1 StringBuilder instance for entire class

    @Override
    public RequestBody convert(G value) throws IOException, ClassCastException {

        JSONObject json = new JSONObject();

        if (value instanceof GraphqlBaseObject) {
            try {
//                skonczylem na tym: {repositoryOwner(login:"ReactiveX"){avatarURL login path url repositories}}
//                json.put(QUERY, convertGraphqlObjectToString(((GraphqlBaseObject) value)));
                json.put(QUERY, "{repositoryOwner(login:\"ReactiveX\"){avatarURL login path url repositories(first: 5) {edges{node{name path description forks {totalCount} watchers {totalCount} stargazers {totalCount} }}}}}");
            } catch (JSONException e) {
                e.printStackTrace(); //todo: something more elaborate here
            }
        }

        return RequestBody.create(MEDIA_TYPE, json.toString());
    }


//
//    public String convertGraphqlObjectToString(GraphqlBaseObject graphqlBaseObject) {
//
//        String serializedValueString = insertExpression(
//                buildGraphqlQuery(
//                        graphqlBaseObject.getSerializableName(),
//                        graphqlBaseObject.hasArguments(),
//                        graphqlBaseObject.getArgKey(),
//                        graphqlBaseObject.getArgValue()),
//                insertExpression(
//                        buildGraphqlFields(
//                                graphqlBaseObject
//                        )
//                )
//        );
//
//        return serializedValueString;
//    }
//
//    /**
//     * Builds 'fields' part of GraphQL query.
//     * Takes a graph object (GraphqlBaseObject), seeks all relevant fields to fill and puts them inside
//     * a query.
//     * @param graphqlBaseObject base class that all primary graph objects should extend from
//     * @return correctly formatted string with fields declaration
//     */
//    private String buildGraphqlFields(GraphqlBaseObject graphqlBaseObject) /*throws IllegalAccessException*/ {
//        Field[] fields = graphqlBaseObject.getClass().getDeclaredFields();
//
//        StringBuilder queryFieldsBuilder = new StringBuilder();
//        for (int i = 0; i < fields.length; ++i) {
//            if (fields[i].getClass().isAssignableFrom(GraphqlBaseObject.class)) {
//
//                GraphqlBaseObject childField = null;
//                try {
//                    childField = (GraphqlBaseObject) fields[i].get(graphqlBaseObject);
//                } catch (IllegalAccessException exc) {
//                    exc.printStackTrace();
//                    continue; //if we can't cast, field cannot be serialized for graph, skipping
//                }
//
//                buildGraphqlQuery(
//                        childField.getSerializableName(),
//                        childField.hasArguments(),
//                        childField.getArgKey(),
//                        childField.getArgValue()
//                );
//
//                if (childField.hasArguments() && Computation.isNumber(childField.getArgValue())) {
//                    //egdes - node - class (fields)
//                    queryFieldsBuilder.append(
//                            insertExpression("edges",
//                                    insertExpression("node",
//                                            insertExpression(buildGraphqlFields(childField))
//                                    )
//                            )
//                    );
//                } else {
//                    queryFieldsBuilder.append(insertExpression(buildGraphqlFields(childField)));
//                    //class (fields)
//                }
//            } else {
//                queryFieldsBuilder.append(fields[i].getName());
//            }
//            if (i != fields.length-1) {
//                queryFieldsBuilder.append(" ");
//            }
//        }
//        return queryFieldsBuilder.toString();
//    }
//
//    /**
//     * Building 'query' part of GraphQL Query (stuff like 'repositories(first:5)'.
//     * Hides syntax stuff from user.
//     * @param serializableName name of query / parameter
//     * @param arguments whether query should take some arguments on the input
//     * @param argumentKey argument key (login/first/last/after etc)
//     * @param argumentValue argument value
//     * @return correctly formatted string with query
//     */
//    //todo: map instead of single argument (key-val) pair
//    private String buildGraphqlQuery(String serializableName, boolean arguments, String argumentKey, String argumentValue) {
//        StringBuilder query = new StringBuilder();
//        query.append(serializableName);
//        if(arguments) {
//            query.append(ARGUMENTS_START)
//                    .append(argumentKey)
//                    .append(ARGUMENT_KEY_VALUE_SEPARATOR)
//                    .append("\"")
//                    .append(argumentValue)
//                    .append("\"")
//                    .append(ARGUMENTS_END);
//        }
//        return query.toString();
//    }
//
//    /**
//     * Surrounding expression(s) with correct curly braces on both sides
//     * @param expressions GraphQL expressions (queries, field declarations, enums etc)
//     * @return correcly formatted expression(s) as a String with braces, ready to POST.
//     */
//    private String insertExpression(String... expressions) {
//        StringBuilder expressionWithBraces = new StringBuilder();
//
//        expressionWithBraces.append(EXPRESSION_START);
////        expressionWithBraces.insert(0, EXPRESSION_START);
//        for (int e = 0; e < expressions.length; ++e) {
//            expressionWithBraces.append(expressions[e]);
//        }
//        expressionWithBraces.append(EXPRESSION_END);
//
//        return expressionWithBraces.toString();
//    }

}
