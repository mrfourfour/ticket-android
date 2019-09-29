package kr.ac.jejunu.ticket.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import kr.ac.jejunu.ticket.model.Ticket;
import kr.ac.jejunu.ticket.model.TicketResponse;
import kr.ac.jejunu.ticket.retrofit.ApiRequest;
import kr.ac.jejunu.ticket.retrofit.RetrofitRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketRepository {
    private static final String TAG = TicketRepository.class.getSimpleName();
    private final ApiRequest apiRequest;

    public TicketRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<TicketResponse> getTickets() {
        final MutableLiveData<TicketResponse> data = new MutableLiveData<>();
        apiRequest.getCurrentTickets()
                .enqueue(new Callback<TicketResponse>() {
                    @Override
                    public void onResponse(@NotNull Call<TicketResponse> call, @NotNull Response<TicketResponse> response) {
                        if (response != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<TicketResponse> call, @NotNull Throwable t) {
                            data.setValue(null);
                    }
                });
        return data;
    }
}
