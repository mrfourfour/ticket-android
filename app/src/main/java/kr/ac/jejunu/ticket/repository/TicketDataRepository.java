package kr.ac.jejunu.ticket.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import kr.ac.jejunu.ticket.TicketQuery;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;
import kr.ac.jejunu.ticket.application.GetTokenable;

public class TicketDataRepository extends DataRepository {

    private static final String TAG = TicketDataRepository.class.getSimpleName();

    public TicketDataRepository(GetTokenable getTokenable) {
        super(getTokenable);
    }

    public LiveData<TicketQuery.Data> getTicket() {
        final MutableLiveData<TicketQuery.Data> data = new MutableLiveData<>();
        ApolloRequest.getApolloInstance(accessToken).query(TicketQuery.builder().build()).enqueue(new ApolloCall.Callback<TicketQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<TicketQuery.Data> response) {
//                if (Objects.requireNonNull(response.data()).allTicket().size() >0) {
                if(Optional.ofNullable(response.data()).map(TicketQuery.Data::allTicket).map(List::size).orElse(0)>0){
                    data.postValue(response.data());
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.d(TAG, String.valueOf(e));
            }
        });
        return data;
    }
}
