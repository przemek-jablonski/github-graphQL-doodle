package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.core.GraphQLBaseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import graphql.schema.GraphQLObjectType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ciemek on 05/11/2016.
 *
 * A {@linkplain Converter.Factory converter} which handles Graphql's POJOs to JSON interactions.
 *
 * POJOs (data types of Graphql data/queries) are serialized into JSON with custom serializer
 * (see requestBodyConverter() method).
 *
 * JSON responses are deserialized with standard GSON implementation.
 */

public final class GraphQLConverterFactory extends Converter.Factory {

    public static GraphQLConverterFactory create() {
        return create(new Gson());
    }

    public static GraphQLConverterFactory create(Gson gson) {
        return new GraphQLConverterFactory(gson);
    }

    private final GsonConverterFactory gsonConverterFactory;

    private GraphQLConverterFactory(Gson gson) {
        if (gson == null)
            throw new NullPointerException("Gson passed to GraphQLConverterFactory is null!");

        gsonConverterFactory = GsonConverterFactory.create(gson);
    }


    /**
     * Returns a {@link Converter} for converting an HTTP response body to {@code type}, or null if
     * {@code type} cannot be handled by this factory. This is used to create converters for
     * response types such as {@code SimpleResponse} from a {@code Call<SimpleResponse>}
     * declaration.
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type,
            Annotation[] annotations,
            Retrofit retrofit) {

        return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit);
    }

    /**
     * Returns a {@link Converter} for converting {@code type} to an HTTP request body, or null if
     * {@code type} cannot be handled by this factory. This is used to create converters for types
     * specified by {@link Body @Body}, {@link Part @Part}, and {@link PartMap @PartMap}
     * values.
     */
    @Override
    public Converter<?, RequestBody> requestBodyConverter(
            Type type,
            Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations,
            Retrofit retrofit) {

        return new GraphQLQueryConverter<>();
    }

}
