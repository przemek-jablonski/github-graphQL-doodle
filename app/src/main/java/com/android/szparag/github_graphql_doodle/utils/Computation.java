package com.android.szparag.github_graphql_doodle.utils;

import com.android.szparag.github_graphql_doodle.backend.models.Repository;

import java.util.Comparator;

/**
 * Created by ciemek on 05/11/2016.
 */

public class Computation {

    //todo: dagger instead of statics!

//    http://stackoverflow.com/a/237204/6942800
    public static boolean isNumber(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    public static class RepoStatsComparator implements Comparator<Repository> {
        public enum SortItem {STARS, FORKS, WATCHES};
        public SortItem sortItem = SortItem.STARS;

        public RepoStatsComparator sortBy(SortItem sortItem) {
            this.sortItem = sortItem;
            return this; //?
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
}
