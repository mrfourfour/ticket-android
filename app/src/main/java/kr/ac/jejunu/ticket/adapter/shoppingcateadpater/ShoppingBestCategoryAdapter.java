package kr.ac.jejunu.ticket.adapter.shoppingcateadpater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.BestCategoryShoppingItemBinding;

public class ShoppingBestCategoryAdapter extends RecyclerView.Adapter<ShoppingBestCategoryAdapter.BestViewHolder> {

    private BestCategoryShoppingItemBinding binding;

    public ShoppingBestCategoryAdapter() {

    }
    @NonNull
    @Override
    public BestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.best_category_shopping_item,parent,false);

        return new BestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BestViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class BestViewHolder extends RecyclerView.ViewHolder {
        private final BestCategoryShoppingItemBinding bind;

        public BestViewHolder(@NonNull BestCategoryShoppingItemBinding bind) {
            super(bind.getRoot());
            this.bind =bind;
        }
    }
}
