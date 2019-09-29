package kr.ac.jejunu.ticket.adapter;

import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiConsumer;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.CurrentTicketRecyclerItemBinding;
import kr.ac.jejunu.ticket.model.Ticket;

public class CurrentTicketRecyclerAdapter extends RecyclerView.Adapter<CurrentTicketRecyclerAdapter.ViewHolder> {

    private final float width;
    private ArrayList<Ticket> tickets;
    private CurrentTicketRecyclerItemBinding binding;
    private BiConsumer<Ticket,View> consumer;

    public CurrentTicketRecyclerAdapter(ArrayList<Ticket> tickets, BiConsumer<Ticket,View> consumer,float width) {
        this.tickets = tickets;
        this.consumer = consumer;
        this.width = width;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding =
                DataBindingUtil
                        .inflate(LayoutInflater.from(parent.getContext()),R.layout.current_ticket_recycler_item,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.binding.imageButton4.setTransitionName(ticket.getTicket_id());
        holder.binding.imageButton4.setOnClickListener(view -> onClick(position,holder.binding.imageButton4));
        holder.binding.setTicketModel(ticket);
        ViewGroup.LayoutParams params = binding.ticketLayout.getLayoutParams();
        params.width = ((int) width) / 2;
        binding.ticketLayout.setLayoutParams(params);
    }

    private void onClick(int position, ImageView view) {
        consumer.accept(tickets.get(position),view);
    }

    @Override
    public int getItemCount() {
        return Optional.ofNullable(tickets).map(ArrayList::size).orElse(0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final CurrentTicketRecyclerItemBinding binding;

        public ViewHolder(@NonNull CurrentTicketRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

