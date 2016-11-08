package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.queries.RepositoryOwnerQueryFull;
import com.android.szparag.github_graphql_doodle.utils.Constants;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.RequestBody;

/**
 * Created by ciemek on 05/11/2016.
 */
public class GraphQLRequestBodyConverterTest {

    GraphQLRequestBodyConverter converter;
    RepositoryOwner owner;


    @Before
    public void setup() {
        converter = new GraphQLRequestBodyConverter();

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put(Constants.GraphqlConstants.ARGUMENT_LOGIN, "sharaquss");
        owner = new RepositoryOwner(map);
    }

//    @Test
//    public void graphqlSerializationTesting() {
//        GraphQLQueryConverter conv = new GraphQLQueryConverter();
//        RepositoryOwnerQueryFull q = new RepositoryOwnerQueryFull(); //here should be like ARGS.TYPE.FIRST
//
//        String querystring = conv.convertBaseObjectToquery(q.getQuery());
//        System.out.println(querystring);
//    }

}