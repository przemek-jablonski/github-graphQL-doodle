package com.android.szparag.github_graphql_doodle.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.szparag.github_graphql_doodle.R;
import com.android.szparag.github_graphql_doodle.backend.models.RepositoryOwner;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ciemek on 07/11/2016.
 */

public class RepositoryOwnerAdapter extends BaseHeaderAdapter<RepositoryOwner> {

    public RepositoryOwnerViewHolder holder;

    public RepositoryOwnerAdapter(View headerParentLayout) {
        holder = new RepositoryOwnerViewHolder(headerParentLayout);
    }

    @Override
    public void updateItem(RepositoryOwner item) {
        super.updateItem(item);
        bindView();
    }

    @Override
    public void bindView() {
        Picasso.with(holder.avatar.getContext()).load(getItem().getAvatarURL()).into(holder.avatar);
        holder.login.setText(getItem().getLogin());
        holder.path.setText(getItem().getPath());
        holder.stats.setText(
                holder.stats.getContext()
                        .getString(
                                R.string.repositories_count,
                                getItem().getRepositories().getTotalCount(),
                                getItem().getRepositories().getEdges().size()
                        )
        );

    }

    public class RepositoryOwnerViewHolder {

        @BindView(R.id.header_repositoryowner_avatar)
        ImageView avatar;

        @BindView(R.id.header_repositoryowner_login)
        TextView login;

        @BindView(R.id.header_repositoryowner_path)
        TextView path;

        @BindView(R.id.header_repositoryowner_stats)
        TextView stats;

        public RepositoryOwnerViewHolder(View headerParentLayout) {
            ButterKnife.bind(this, headerParentLayout);
        }
    }
}
