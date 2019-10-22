package kr.ac.jejunu.ticket.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import kr.ac.jejunu.ticket.CategoryListQuery;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;

public class ProductListDataRepository {
    private static final String TAG = ProductListDataRepository.class.getSimpleName();

    public LiveData<CategoryListQuery.Data> getList() {
        final MutableLiveData<CategoryListQuery.Data> data = new MutableLiveData<>();
        ApolloRequest.getApolloInstance().query(CategoryListQuery.builder().build()).enqueue(new ApolloCall.Callback<CategoryListQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<CategoryListQuery.Data> response) {
                data.postValue(response.data());
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
        return data;
    }
}
