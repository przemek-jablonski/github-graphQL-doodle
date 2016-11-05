package com.android.szparag.github_graphql_doodle.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlService;
import com.android.szparag.github_graphql_doodle.backend.services.GraphqlServiceImpl;
import com.android.szparag.github_graphql_doodle.utils.Utils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {


    @BindView(R.id.textview)
    TextView textView;

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
