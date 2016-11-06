package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.annotations.GraphqlType;
import com.android.szparag.github_graphql_doodle.utils.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ciemek on 05/11/2016.
 */

@GraphqlType
public abstract class GraphqlBaseObject {

    protected String serializableName;
    protected TreeMap<String, String> argumentMap;
    protected String[] availableArgs;


    public GraphqlBaseObject(String serializableName) {
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

        if (argumentMap.size() != arguments.size()) {
            Utils.logMisc("Arguments for GraphQL item were not valid, deleted broken entries");
        }
        if (argumentMap.size() == 0) {
            Utils.logMisc("Arguments for GraphQL item were not valid or empty", "setting defaults");
            argumentMap.put(availableArgs[0], "50");
        }
    }

    public String getSerializableName() {
        return serializableName;
    }

    public TreeMap<String, String> getArgumentMap() {
        return argumentMap;
    }
}
