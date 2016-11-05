package com.android.szparag.github_graphql_doodle.backend.serializers;

import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlBaseObject;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

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
 */

public final class GraphQLConverterFactory extends Converter.Factory {

    private final Gson gson;
    private final GsonConverterFactory gsonConverterFactory;

    public GraphQLConverterFactory() {
        gson = new Gson();
        gsonConverterFactory = GsonConverterFactory.create();
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
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new GsonResponseBodyConverter<>(gson, adapter);
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

//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new GraphQLRequestBodyConverter<>();
    }

    /**
     * Returns a {@link Converter} for converting {@code type} to a {@link String}, or null if
     * {@code type} cannot be handled by this factory. This is used to create converters for types
     * specified by {@link Field @Field}, {@link FieldMap @FieldMap} values,
     * {@link Header @Header}, {@link HeaderMap @HeaderMap}, {@link Path @Path},
     * {@link Query @Query}, and {@link QueryMap @QueryMap} values.
     */
    @Override
    public Converter<?, String> stringConverter(
            Type type,
            Annotation[] annotations,
            Retrofit retrofit) {

        return null;
    }

}
