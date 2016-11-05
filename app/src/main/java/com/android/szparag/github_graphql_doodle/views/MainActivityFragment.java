package com.android.szparag.github_graphql_doodle.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.android.szparag.github_graphql_doodle.backend.models.graphql.GraphqlResponseObject;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.utils.Utils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityFragment extends Fragment {


    //todo: remove graphql-java library (not used)

    @BindView(R.id.textview)
    TextView textView;

    @BindView(R.id.textview2)
    TextView textView2;

    @BindView(R.id.textview3)
    TextView textView3;

    private Unbinder unbinder;

    @Inject
    GraphqlService service;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.getDagger2(this).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
    }


    @Override
    public void onStart() {
        super.onStart();
        textView.setText("KA RA KAN");
    }

    @Override
    public void onResume() {
        super.onResume();

//        GraphQLObjectType queryType = newObject()
//                .name("helloWorldQuery")
//                .field(newFieldDefinition()
//                        .type(GraphQLString)
//                        .name("hello")
//                        .staticValue("world"))
//                .build();
//
//        GraphQLSchema schema = GraphQLSchema.newSchema()
//                .query(queryType)
//                .build();


//        Map<String, Object> result = (Map<String, Object>) new GraphQL(schema).execute("{hello}").getData();

//        GsonConverterFactory

//        textView.setText(result.toString());

//        GraphQLObjectType queryType2 = newObject()
//                .name("repositoryOwner")
//                .field(newFieldDefinition()
//                        .type(GraphQLString)
//                        .name("login"))
//                .field(newFieldDefinition()
//                        .type(GraphQLString)
//                        .name("avatarURL"))
//                .build();
//
//        GraphQLSchema graphQLSchema2 = GraphQLSchema.newSchema()
//                .query(queryType2)
//                .build();

////        textView.setText(queryType2.toString());
//        textView2.setText(graphQLSchema2.getAllTypesAsList().toString());
////        textView3.setText(graphQLSchema2.getDictionary().toString());
        RepositoryOwner repositoryOwner = new RepositoryOwner("repositoryOwner", true, "ReactiveX");

        service.getGraphData(repositoryOwner, new Callback<GraphqlResponseObject>() {
                    @Override
                    public void onResponse(Call<GraphqlResponseObject> call, Response<GraphqlResponseObject> response) {
                        GraphqlResponseObject obj = response.body();
                        Utils.logRetrofit("success");
                    }

                    @Override
                    public void onFailure(Call<GraphqlResponseObject> call, Throwable t) {
                        Utils.logRetrofit("failure");
                    }
                }
        );

//        service.getGraphData(repositoryOwner, new Callback<GraphqlBaseObject>() {
//            @Override
//            public void onResponse(Call<GraphqlBaseObject> call, Response<GraphqlBaseObject> response) {
////                Utils.logRetrofit("SUCCESS");
////                textView.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<GraphqlBaseObject> call, Throwable t) {
////                Utils.logRetrofit("FAILURE");
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
