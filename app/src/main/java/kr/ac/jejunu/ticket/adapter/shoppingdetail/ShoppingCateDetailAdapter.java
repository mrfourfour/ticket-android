package kr.ac.jejunu.ticket.adapter.shoppingdetail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.ticket.ProductByCategoryQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.CategoryDetailItemBinding;

public class ShoppingCateDetailAdapter extends RecyclerView.Adapter<ShoppingCateDetailAdapter.DetailViewHolder> {

    private List<ProductByCategoryQuery.ProductByCategory> list;
    private CategoryDetailItemBinding binding;
    public ShoppingCateDetailAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.category_detail_item,parent,false);

        return new DetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailViewHolder holder, int position) {
        holder.bind.setProductByCate(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<ProductByCategoryQuery.ProductByCategory> data) {
        list.addAll(data);
    }

    public class DetailViewHolder extends RecyclerView.ViewHolder {
        private final CategoryDetailItemBinding bind;

        public DetailViewHolder(@NonNull CategoryDetailItemBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
        }
    }
}
