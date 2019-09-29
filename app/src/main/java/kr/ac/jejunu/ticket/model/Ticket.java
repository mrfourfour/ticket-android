package kr.ac.jejunu.ticket.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket  implements Serializable {
    public String getTicket_id() { return ticket_id; }
    public void setTicket_id(String ticket_id) { this.ticket_id = ticket_id; }

    public String getTicket_status() { return ticket_status; }
    public void setTicket_status(String ticket_status) { this.ticket_status = ticket_status; }

    public int getTicket_amount() { return ticket_amount; }
    public void setTicket_amount(int ticket_amount) { this.ticket_amount = ticket_amount; }

    public int getTicket_total_price() { return ticket_total_price; }
    public void setTicket_total_price(int ticket_total_price) { this.ticket_total_price = ticket_total_price; }

    public String getTicket_product_id() { return ticket_product_id; }
    public void setTicket_product_id(String ticket_product_id) { this.ticket_product_id = ticket_product_id; }

    public String getTicket_date() { return ticket_date; }
    public void setTicket_date(String ticket_date) { this.ticket_date = ticket_date; }

    public String getTicket_qrdata() { return ticket_qrdata; }
    public void setTicket_qrdata(String ticket_qrdata) { this.ticket_qrdata = ticket_qrdata; }

    @SerializedName("id")
    private String ticket_id;
    @SerializedName("status")
    private String ticket_status;
    @SerializedName("amount")
    private int ticket_amount;
    @SerializedName("totalPrice")
    private int ticket_total_price;
    @SerializedName("productId")
    private String ticket_product_id;
    @SerializedName("date")
    private String ticket_date;
    @SerializedName("qrData")
    private String ticket_qrdata;
}

