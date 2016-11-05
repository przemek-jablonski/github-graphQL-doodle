package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.RequestBody;

import static org.junit.Assert.*;

/**
 * Created by ciemek on 05/11/2016.
 */
public class GraphQLRequestBodyConverterTest {

    GraphQLRequestBodyConverter conv;
    RepositoryOwner own;

    @Before
    public void setup() {
        conv = new GraphQLRequestBodyConverter<>();
        own = new RepositoryOwner("repositoryOwner", true, "ReactiveX");
    }

    @Test
    public void convertedStringProgressTest() {
        String str = conv.convertGraphqlObjectToString(own);
        System.out.println(str);
    }

    @Test
    public void convertedJsonProgressTest(){
        RequestBody body = null;
        try {
            body=conv.convert(own);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(body.toString());
    }

}