package kr.ac.jejunu.ticket.apollo;

import com.apollographql.apollo.ApolloClient;

import okhttp3.OkHttpClient;

public class ApolloRequest {
    private static final String TAG = ApolloRequest.class.getSimpleName();
    private static final String BASE_URL2 = "http://gql-server.ap-northeast-2.elasticbeanstalk.com/graphql";
//    private static final String BASE_URL2 = "http://172.19.1.246:4000/graphql";
    private static ApolloClient apollo;


    public static ApolloClient getApolloInstance(String token) {
        if (apollo == null) {
            OkHttpClient okHttpClient =
                    new OkHttpClient
                            .Builder()
                            .addNetworkInterceptor(
                                    chain -> chain.proceed(chain.request().newBuilder().header("Authorization",token).build())
                            )
                            .build();
            apollo = ApolloClient
                    .builder()
                    .serverUrl(BASE_URL2)
                    .okHttpClient(okHttpClient)
                    .build();
        }
        return apollo;
    }
}
