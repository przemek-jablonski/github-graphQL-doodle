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


    boolean firstStringUsed = false;
    private final Gson gson;
    private final TypeAdapter<G> adapter;

    public GraphQLRequestBodyConverter(Gson gson, TypeAdapter<G> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

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

        String costam;
//        String serializedValueStringFull = requestPayloadWrapper(serializedValueString);
        if (!firstStringUsed) {
//            serializedValueString =  "{\"query\": \"query { viewer { login }}\"}";
            serializedValueString =  "{\"query\": \"query { repositoryOwner(login:\"ReactiveX\") { login }}\"}";
            costam = "{ viewer { login }}";
            firstStringUsed = true;
        } else {
            serializedValueString = "{\"query\": \"query { repositoryOwner (login: \"ReactiveX\") { avatarURL login path url }}\"}";
            costam = "{ repositoryOwner (login: \"ReactiveX\") { avatarURL login path url }}";
        }


        JSONObject object = new JSONObject();
        try {
            object.put("query", costam);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MEDIA_TYPE, object.toString());
    }

    private String requestPayloadWrapper(String payload) {
        String string =  "{\"query\":";
        string += "\"query ";
        string += payload;
        string += "\"}";
        return string;
    }

    private String buildGraphqlFields(GraphqlBaseObject graphqlBaseObject) {
        Field[] fields = graphqlBaseObject.getClass().getDeclaredFields();

        StringBuilder queryFieldsBuilder = new StringBuilder();
        for (int i = 0; i < fields.length; ++i) {
            Field field = fields[i];
            Class clazz = field.getType();
            queryFieldsBuilder.append(field.getName());
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
        query.append(ARGUMENTS_START)
                .append(argumentKey)
                .append(ARGUMENT_KEY_VALUE_SEPARATOR)
                .append("\"")
                .append(argumentValue)
                .append("\"")
                .append(ARGUMENTS_END);
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
