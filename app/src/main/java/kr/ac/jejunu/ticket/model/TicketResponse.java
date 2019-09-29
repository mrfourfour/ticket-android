package kr.ac.jejunu.ticket.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketResponse {
    public List<Ticket> getData() {
        return data;
    }

    public void setData(List<Ticket> data) {
        this.data = data;
    }

    @SerializedName("data")
    @Expose
    private List<Ticket> data = null;


}
