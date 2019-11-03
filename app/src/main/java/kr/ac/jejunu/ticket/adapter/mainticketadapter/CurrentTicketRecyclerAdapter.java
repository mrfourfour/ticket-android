package kr.ac.jejunu.ticket.adapter.mainticketadapter;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

import kr.ac.jejunu.ticket.ProductByIdQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.TicketQuery;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;
import kr.ac.jejunu.ticket.application.AppApplication;
import kr.ac.jejunu.ticket.data.TicketStatus;
import kr.ac.jejunu.ticket.databinding.CurrentTicketRecyclerItemBinding;

public class CurrentTicketRecyclerAdapter extends RecyclerView.Adapter<CurrentTicketRecyclerAdapter.ViewHolder> {

    private static final String TAG = CurrentTicketRecyclerAdapter.class.getSimpleName();
    private final float width;
    private final Application application;
    private final Activity activity;
    private List<TicketQuery.AllTicket> tickets;
    private CurrentTicketRecyclerItemBinding binding;
    private BiConsumer<Bitmap, View> consumer;

    public CurrentTicketRecyclerAdapter(ArrayList<TicketQuery.AllTicket> ticket, BiConsumer<Bitmap, View> consumer, float width, Application application,Activity activity) {
        this.tickets = ticket;
        this.consumer = consumer;
        this.width = width;
        this.application = application;
        this.activity = activity;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding =
                DataBindingUtil
                        .inflate(LayoutInflater.from(parent.getContext()), R.layout.current_ticket_recycler_item, parent, false);

        return new ViewHolder(binding);
    }

    private Bitmap generateQRcode(String contents) {
        Bitmap bitmap = null;
        QRCodeWriter qrcode = new QRCodeWriter();
        try {
            bitmap = toBitmap(qrcode.encode(contents, BarcodeFormat.QR_CODE,230,230));

        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    private Bitmap toBitmap(BitMatrix encode) {
        int heigth = encode.getHeight();
        int width = encode.getWidth();

        Bitmap bitmap = Bitmap.createBitmap(width,heigth,Bitmap.Config.RGB_565);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < heigth; j++) {
                bitmap.setPixel(i,j,encode.get(i,j) ? Color.BLACK : Color.WHITE) ;
            }

        }
        return bitmap;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (Objects.equals(TicketStatus.valueOf(tickets.get(position).status()), TicketStatus.USED)) {
            binding.layout.setBackgroundResource(R.color.black);
            binding.QRcode.setImageResource(R.drawable.cancel);
        }else {
            holder.binding.QRcode.setImageBitmap(generateQRcode(tickets.get(position).qrData()));
        }
        getProduct((AppApplication)application,tickets.get(position).productId(),holder);
        holder.binding.status.setText(TicketStatus.valueOf(tickets.get(position).status()).getValue());
        holder.binding.setTicketModel(tickets.get(position));

        holder.binding.QRcode.setTransitionName(tickets.get(position).id());
        holder.binding.QRcode.setOnClickListener(view -> onClick(generateQRcode(tickets.get(position).qrData()), holder.binding.QRcode));
        ViewGroup.LayoutParams params = binding.ticketLayout.getLayoutParams();
        params.width = ((int) width) / 2;
        binding.ticketLayout.setLayoutParams(params);
    }

    private void getProduct(AppApplication application, String productId, ViewHolder holder) {
        ProductByIdQuery query = new ProductByIdQuery(Input.fromNullable(productId));
        ApolloRequest.getApolloInstance(application.getToken()).query(query).enqueue(new ApolloCall.Callback<ProductByIdQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProductByIdQuery.Data> response) {
                activity.runOnUiThread(() -> {
                    holder.binding.contentDate.setText(response.data().productById().options().get(0).date());
                    holder.binding.place.setText(response.data().productById().area());
                    holder.binding.ticketName.setText(response.data().productById().name());
                    holder.binding.option.setText(response.data().productById().options().get(0).description());
                });

            }
            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }


    private void onClick(Bitmap bitmap, ImageView view) {
        consumer.accept(bitmap, view);
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CurrentTicketRecyclerItemBinding binding;

        public ViewHolder(@NonNull CurrentTicketRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

