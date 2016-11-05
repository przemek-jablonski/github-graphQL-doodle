package com.android.szparag.github_graphql_doodle;

import com.android.szparag.github_graphql_doodle.backend.models.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlObjectTest {


    private final String EXPRESSION_START = "{";
    private final String EXPRESSION_END = "}";
    private final String ARGUMENTS_START = "(";
    private final String ARGUMENTS_END = ")";
    private final String ARGUMENT_KEY_VALUE_SEPARATOR = ":";
    private final String ARGUMENT_APPEND = ",";


    @Test
    public void fieldSerializationTest() {
        RepositoryOwner repositoryOwner = new RepositoryOwner("repositoryOwner", true, "ReactiveX");
        assertEquals("avatarURL login path url", buildQueryFieldsString(repositoryOwner));
    }

    @Test
    public void serializingRepositoryOwner() {

        StringBuilder serialized = new StringBuilder();

        RepositoryOwner repositoryOwner = new RepositoryOwner("repositoryOwner", true, "ReactiveX");


        serialized.append(
                insertExpression(
                        buildGraphqlQuery(
                                repositoryOwner.getSerializableName(),
                                repositoryOwner.hasArguments(),
                                repositoryOwner.getArgKey(),
                                repositoryOwner.getArgValue()
                        ), insertExpression(
                                buildQueryFieldsString(
                                        repositoryOwner
                                )
                        )
                )
        );


        assertEquals(
                "{repositoryOwner(login:\"ReactiveX\"){avatarURL login path url}}",
                serialized.toString()
        );
    }

    public String buildQueryFieldsString(GraphqlBaseObject graphqlBaseObject) {
        Field[] fields = graphqlBaseObject.getClass().getDeclaredFields();

        StringBuilder queryFieldsBuilder = new StringBuilder();
        for (int i = 0; i < fields.length; ++i) {
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
