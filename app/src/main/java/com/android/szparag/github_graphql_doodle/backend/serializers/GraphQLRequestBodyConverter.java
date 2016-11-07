package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLDontFetch;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLType;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Map;

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
    private final String ARGUMENT_VALUE = "\"";
    private final String ARGUMENT_APPEND_NEXT = ",";
    private final String QUERY = "query";
    private final String FIELDS_SEPARATOR = " ";
    private StringBuilder stringBuilder;

    public GraphQLRequestBodyConverter() {
        stringBuilder = new StringBuilder(); //fixme: this should be deleted, dev/debug only
    }


    @Override
    public RequestBody convert(G value) throws IOException, ClassCastException {

        stringBuilder = new StringBuilder();

        JSONObject json = new JSONObject();

        if (value instanceof GraphQLBaseObject) {
            try {
//                skonczylem na tym: {repositoryOwner(login:"ReactiveX"){avatarURL login path url repositories}}
//                json.put(QUERY, convertGraphqlObjectToString(((GraphQLBaseObject) value)));
                json.put(QUERY, "{repositoryOwner(login:\"ReactiveX\"){avatarURL login path url repositories(first: 5) {edges{node{name path description forks {totalCount} watchers {totalCount} stargazers {totalCount} }}}}}");
            } catch (JSONException e) {
                e.printStackTrace(); //todo: something more elaborate here
            }
        }

        return RequestBody.create(MEDIA_TYPE, json.toString());

//        if (value instanceof GraphQLBaseObject) {
//            StringBuilder stringBuilder = new StringBuilder();
//            return RequestBody.create(
//                    MEDIA_TYPE,
//                    convertQueryToJson(
//                            convertBaseObjectToQuery(stringBuilder, ((GraphQLBaseObject) value)))
//                            .toString()
//            );
//        }
//
//        return null;
    }

//    private JSONObject convertQueryToJson(String graphqlQuery) {
//        JSONObject json = new JSONObject();
//        try {
//            json.put(QUERY, graphqlQuery);
//        } catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return json;
//    }

    public String convertBaseObjectToQuery(GraphQLBaseObject graphQLBaseObject, boolean recursivelyCalled) {
        if (!recursivelyCalled) stringBuilder.append(EXPRESSION_START);

        makeQuery(graphQLBaseObject);
        stringBuilder.append(EXPRESSION_START);

        Field[] fields = graphQLBaseObject.getClass().getDeclaredFields();

        for (int f = 0; f < fields.length; ++f) {
//            boolean isAssignable = fields[f].getClass().isAssignableFrom(GraphQLBaseObject.class);
//            boolean isAssignable = ;

//            String name = fields[f].getGenericType().getClass().getName();
//            String name1 = fields[f].getDeclaringClass().getName();
////            String name5 = fields[f].getDeclaringClass().getEnclosingClass().getName();
//            String name4 = fields[f].getDeclaringClass().getSuperclass().getName();
//            String name3 = fields[f].getDeclaringClass().getGenericSuperclass().getClass().getName();
////            String name2 = fields[f].getDeclaringClass().getDeclaringClass().getName();
//            String name6 = fields[f].getClass().getName();
////            String name7 = fields[f].getClass().getEnclosingClass().getName();
//            String name8 = fields[f].getClass().getSuperclass().getName();
//            String name9 = fields[f].getClass().getGenericSuperclass().getClass().getName();
////            String name10 = fields[f].getClass().getDeclaringClass().getName();

//            if (fields[f].getDeclaringClass().getSuperclass().isAssignableFrom(GraphQLBaseObject.class)) {
//            String name = fields[f].getType().getName();
//            String name1 = fields[f].getType().getSuperclass().getName();
//
//            boolean annot =
//            boolean assignable = fields[f].getType().isAssignableFrom(GraphQLBaseObject.class)
            Type actualType = fields[f].getType();
//            Type generic = fields[f].getType().g;
            Type param1;
            Type param2;
            Object superobject;
            Type parametrizedType = fields[f].getGenericType();
            if (parametrizedType instanceof ParameterizedType) {
                param1 = ((ParameterizedType) parametrizedType).getActualTypeArguments()[0];
                if (param1 instanceof ParameterizedType) {
                    param2 = ((ParameterizedType) param1).getActualTypeArguments()[0];
                    System.out.println(param2.toString() + " / " + param2.toString()); //wee, Repository here

                    try {
                        Class<?> clazz = Class.forName(param2.toString());
                        clazz.cast(param2);
//                        Constructor<?> constr = clazz.getConstructor;
                        superobject = clazz.newInstance();
                        String asd = "asd";
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }



            Type superType = fields[f].getType().getSuperclass();

            if (graphqlTypeCheck(fields[f])) {
//                if (fields[f].isAnnotationPresent(GraphQLDontFetch.class)) {
//                    continue;
//                }

                stringBuilder.append(FIELDS_SEPARATOR);
                try {
                    fields[f].setAccessible(true);
                    convertBaseObjectToQuery(((GraphQLBaseObject) fields[f].get(graphQLBaseObject)), true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {
                    fields[f].setAccessible(false);
                }
                stringBuilder.append(FIELDS_SEPARATOR);
            } else {
                stringBuilder.append(FIELDS_SEPARATOR).append(fields[f].getName()).append(FIELDS_SEPARATOR);
            }
        }
        if (!recursivelyCalled) stringBuilder.append(EXPRESSION_END);


        stringBuilder.append(EXPRESSION_END);
        return stringBuilder.toString();
    }

    private void makeQuery(GraphQLBaseObject graphQLBaseObject) {
        stringBuilder.append(graphQLBaseObject.getSerializableName());

        if (graphQLBaseObject.getArgumentMap() != null) {
            makeArguments(graphQLBaseObject.getArgumentMap());
        }
    }

    private void makeArguments(Map<String, String> argumentMap) {
        stringBuilder.append(ARGUMENTS_START);
        for (Map.Entry<String, String> entry : argumentMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(ARGUMENT_KEY_VALUE_SEPARATOR);
            stringBuilder.append(ARGUMENT_VALUE).append(entry.getValue()).append(ARGUMENT_VALUE);
            stringBuilder.append(ARGUMENT_APPEND_NEXT);
        }
        stringBuilder.deleteCharAt(stringBuilder.toString().length()-1); //todo: refactor, perf drop here
        stringBuilder.append(ARGUMENTS_END);
    }

    private boolean graphqlTypeCheck(Field field) {
        if (field.getType().isAnnotationPresent(GraphQLType.class)){
            return true;
        }
        if (field.getType().getSuperclass() != null) {
            if (field.getType().getSuperclass().isAnnotationPresent(GraphQLType.class)) {
                return true;
            }
        }

        if (field.isAnnotationPresent(GraphQLDontFetch.class)) { //todo: is this needed?
            return false;
        }

            if(field.getName() == "edges") {
            return true;
        }

        return false;
    }

//
//    public String convertGraphqlObjectToString(GraphQLBaseObject graphqlBaseObject) {
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

//    /**
//     * Builds 'fields' part of GraphQL query.
//     * Takes a graph object (GraphQLBaseObject), seeks all relevant fields to fill and puts them inside
//     * a query.
//     * @param graphqlBaseObject base class that all primary graph objects should extend from
//     * @return correctly formatted string with fields declaration
//     */
//    private String buildGraphqlFields(GraphQLBaseObject graphqlBaseObject) /*throws IllegalAccessException*/ {
//        Field[] fields = graphqlBaseObject.getClass().getDeclaredFields();
//
//        StringBuilder queryFieldsBuilder = new StringBuilder();
//        for (int i = 0; i < fields.length; ++i) {
//            if (fields[i].getClass().isAssignableFrom(GraphQLBaseObject.class)) {
//
//                GraphQLBaseObject childField = null;
//                try {
//                    childField = (GraphQLBaseObject) fields[i].get(graphqlBaseObject);
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
