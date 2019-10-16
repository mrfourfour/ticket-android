package kr.ac.jejunu.ticket.apollo;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloRequest {
    private static final String BASE_URL2 = "http://gql-server.ap-northeast-2.elasticbeanstalk.com/graphql";
    private static ApolloClient apollo;

    public static ApolloClient getApolloInstance() {
        if (apollo == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            apollo = ApolloClient.builder().serverUrl(BASE_URL2).okHttpClient(okHttpClient).build();
        }
        return apollo;
    }
}
