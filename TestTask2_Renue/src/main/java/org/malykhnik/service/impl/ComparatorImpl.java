package org.malykhnik.service.impl;

import org.malykhnik.service.Comparator;

public class ComparatorImpl implements Comparator {

    @Override
    public int compareRequest(String request, String answer) {
        int[][] dp = new int[request.length() + 1][answer.length() + 1];

        for (int i = 0; i <= request.length(); i++) {
            for (int j = 0; j <= answer.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.
                            min(dp[i - 1][j - 1] + costOfSubstitution(request.charAt(i - 1),
                                answer.charAt(j - 1)),
                                Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1)
                    );
                }
            }
        }
        return dp[request.length()][answer.length()];
    }

    private int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }
}
