package kr.ac.jejunu.ticket.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import kr.ac.jejunu.ticket.ProductByIdQuery;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;
import kr.ac.jejunu.ticket.application.GetTokenable;

public class ProductDataRepository extends DataRepository {

    public ProductDataRepository(GetTokenable getTokenable) {
        super(getTokenable);
    }

    public LiveData<ProductByIdQuery.Data> getProduct(String id) {
        final MutableLiveData<ProductByIdQuery.Data> data = new MutableLiveData<>();
        ProductByIdQuery query = new ProductByIdQuery(Input.fromNullable(id));
        ApolloRequest.getApolloInstance(accessToken).query(query).enqueue(new ApolloCall.Callback<ProductByIdQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProductByIdQuery.Data> response) {
                data.postValue(response.data());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
        return data;
    }
}
