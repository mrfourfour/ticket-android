package kr.ac.jejunu.ticket.retrofit;

import kr.ac.jejunu.ticket.model.Ticket;
import kr.ac.jejunu.ticket.model.TicketResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRequest {
    @GET("/api/ticket/")
    Call<TicketResponse> getCurrentTickets();
}
