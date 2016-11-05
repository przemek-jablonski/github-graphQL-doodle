package com.android.szparag.github_graphql_doodle.backend.models.graphql;

/**
 * Created by ciemek on 05/11/2016.
 */

public class GraphqlBaseObject {

    protected String serializableName;
    protected boolean arguments;
    protected String argKey = "login";
    protected String argValue;
//  todo: argsMap! here

    public GraphqlBaseObject(String serializableName, boolean hasArguments, String argValue) {
        this.serializableName = serializableName;
        this.argValue = argValue;
        arguments = hasArguments;
    }

    public String getSerializableName() {
        return serializableName;
    }

    public boolean hasArguments() {
        return arguments;
    }

    public String getArgKey() {
        return argKey;
    }

    public String getArgValue() {
        return argValue;
    }

}
