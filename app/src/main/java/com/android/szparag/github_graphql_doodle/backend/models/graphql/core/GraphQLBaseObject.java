package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphQLType;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.queries.GraphqlBaseQuery;
import com.android.szparag.github_graphql_doodle.utils.Utils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ciemek on 05/11/2016.
 */

@GraphQLType
public abstract class GraphQLBaseObject {

    protected String serializableName;
    protected TreeMap<String, String> argumentMap;
    protected String[] availableArgs;
    protected GraphqlBaseQuery query;



    public GraphQLBaseObject(String serializableName) {
        this.serializableName = serializableName;
    }


    protected final void checkArguments(Map<String, String> arguments) {
        if (arguments == null) {
            return;
        }

        argumentMap = new TreeMap<>();
        for (int i = 0; i < arguments.size(); ++i) {
            if (arguments.containsKey(availableArgs[i])) {
                argumentMap.put(availableArgs[i], arguments.get(availableArgs[i]));
            }
        }

        if (argumentMap.size() == 0) {
            argumentMap.put(availableArgs[0], "1"); //?
        }
    }

    public String getSerializableName() {
        return serializableName;
    }

    public TreeMap<String, String> getArgumentMap() {
        return argumentMap;
    }
}
