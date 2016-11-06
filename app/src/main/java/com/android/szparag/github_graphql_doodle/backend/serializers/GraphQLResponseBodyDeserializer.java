package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphQLResponseBodyDeserializer<T extends GraphqlBaseObject> implements JsonDeserializer<T> {

    Class clazz;

    public GraphQLResponseBodyDeserializer(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        //todo: instantiating class from typeOfT
        if (clazz.getName() == RepositoryOwner.class.getName()) {
            return (T) deserializeRepositoryOwner(json, context);
        }


        return null;
    }


    private RepositoryOwner deserializeRepositoryOwner(JsonElement json, JsonDeserializationContext context) {
        RepositoryOwner repositoryOwner = new RepositoryOwner(null);

        JsonObject jsonObject = json.getAsJsonObject();

        JsonObject jsonObject1 = jsonObject.get(repositoryOwner.getSerializableName()).getAsJsonObject();

        boolean asdasd = jsonObject1.has(repositoryOwner.getAvatarURL());

        String asd = "asd as";

        return null;
    }

}
