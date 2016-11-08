package com.android.szparag.github_graphql_doodle.utils;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;

import java.util.Comparator;

/**
 * Created by ciemek on 07/11/2016.
 *
 * Compares and sorts Repository object based on either STARS, FORKS or WATCHES count
 */

public class RepoStatsComparator implements Comparator<Repository> {

    public enum SortItem {STARS, FORKS, WATCHES};
    public SortItem sortItem = SortItem.STARS;

    public RepoStatsComparator sortBy(SortItem sortItem) {
        if(sortItem != null) {
            this.sortItem = sortItem;
        }
        return this;
    }

    @Override
    public int compare(Repository repo1, Repository repo2) {
        switch (sortItem) {
            case FORKS:
                return Integer.valueOf(repo2.getForks().getTotalCount())
                        .compareTo(repo1.getForks().getTotalCount());
            case WATCHES:
                return Integer.valueOf(repo2.getWatchers().getTotalCount())
                        .compareTo(repo1.getWatchers().getTotalCount());
            default:
                return Integer.valueOf(repo2.getStargazers().getTotalCount())
                        .compareTo(repo1.getStargazers().getTotalCount());
        }
    }
}