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

    @Test
    public void graphqlSerializationTesting() {
        GraphQLQueryConverter conv = new GraphQLQueryConverter();
        RepositoryOwnerQueryFull q = new RepositoryOwnerQueryFull("login", "\"ReactiveX\"", "first", "30"); //here should be like ARGS.TYPE.FIRST

        String querystring = conv.convertBaseObjectToquery(q.getQuery());
        System.out.println(querystring);
    }

//    GraphQLRequestBodyConverter conv;
//    RepositoryOwner own;
//
//    @Before
//    public void setup() {
//        conv = new GraphQLRequestBodyConverter<>();
//        own = new RepositoryOwner("repositoryOwner", true, "ReactiveX");
//    }
//
//    @Test
//    public void convertedStringProgressTest() {
//        String str = conv.convertGraphqlObjectToString(own);
//        System.out.println(str);
//    }
//
//    @Test
//    public void convertedJsonProgressTest(){
//        RequestBody body = null;
//        try {
//            body=conv.convert(own);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(body.toString());
//    }

}