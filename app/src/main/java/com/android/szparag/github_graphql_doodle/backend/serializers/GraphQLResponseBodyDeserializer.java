package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.android.szparag.github_graphql_doodle.utils.Utils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphQLResponseBodyDeserializer implements JsonDeserializer<GraphqlBaseObject> {
    @Override
    public GraphqlBaseObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Set<Map.Entry<String, JsonElement>> entries = json.getAsJsonObject().entrySet();
        Utils.logRetrofit(json.toString());
        return null;
    }
}
