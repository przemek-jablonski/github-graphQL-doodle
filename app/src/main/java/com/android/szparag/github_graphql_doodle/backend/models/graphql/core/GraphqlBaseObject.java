package com.android.szparag.github_graphql_doodle.backend.models.graphql.core;

import android.support.annotation.Nullable;

import com.android.szparag.github_graphql_doodle.utils.Utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ciemek on 05/11/2016.
 */

public abstract class GraphqlBaseObject {

    protected String serializableName;
    protected Map<String, String> argumentMap;
    protected String[] availableArgs;


    public GraphqlBaseObject(String serializableName) {
        this.serializableName = serializableName;
    }


    protected final void checkArguments(LinkedHashMap<String, String> arguments) {
        if (arguments == null) {
            return;
        }

        argumentMap = new HashMap<>();
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

    public Map<String, String> getArgumentMap() {
        return argumentMap;
    }
}
