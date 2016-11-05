package com.android.szparag.github_graphql_doodle.backend.models;

/**
 * Created by ciemek on 05/11/2016.
 */

public class RepositoryOwner extends GraphqlBaseObject {


    public RepositoryOwner(String serializableName, boolean hasArguments, String argValue) {
        this.serializableName = serializableName;
        arguments = hasArguments;
        this.argValue = argValue;
    }

    String avatarURL;
    String login;
    String path;
    String url;

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

    public String getAvatarURL() {
        return avatarURL;
    }

    public String getLogin() {
        return login;
    }

    public String getPath() {
        return path;
    }

    public String getUrl() {
        return url;
    }
}
