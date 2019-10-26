package kr.ac.jejunu.ticket.adapter.shoppingcateadpater;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kr.ac.jejunu.ticket.ProductByCategoryQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.CategoryShoppingInnerItemBinding;

public class ShoppingCategoryChildAdapter extends RecyclerView.Adapter<ShoppingCategoryChildAdapter.ChildViewHolder> {

    private static final String TAG = ShoppingCategoryChildAdapter.class.getSimpleName();
    private final List<ProductByCategoryQuery.ProductByCategory> productCategoryItems;
    private final NavController controller;
    private CategoryShoppingInnerItemBinding binding;

    public ShoppingCategoryChildAdapter(NavController controller) {
        this.productCategoryItems = new ArrayList<>();
        this.controller =controller;
    }


    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.category_shopping_inner_item, parent, false);
        return new ChildViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        if (productCategoryItems.size() !=0) {
            holder.bind.setProductModel(productCategoryItems.get(position));
            holder.bind.imageView.setOnClickListener(product -> click(productCategoryItems.get(position).id()));
        } else {
            holder.bind.imageView.setImageResource(R.drawable.preparing_product);
        }
    }

    private void click(String id) {
        Log.d(TAG,id);
        Bundle bundle = new Bundle();
        bundle.putString("id",id);
        controller.navigate(R.id.action_shoppingFragment_to_productDetailFragment2,bundle);
    }

    @Override
    public int getItemCount() {
        if (productCategoryItems.size() != 0) {
            return productCategoryItems.size();
        }else return 1;
    }

    public void addList(List<ProductByCategoryQuery.ProductByCategory> productByCategory) {
        productCategoryItems
                .addAll(Optional.ofNullable(productByCategory).orElse(productCategoryItems));
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        private final CategoryShoppingInnerItemBinding bind;

        ChildViewHolder(@NonNull CategoryShoppingInnerItemBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
            bind.textLayout.bringToFront();
        }
    }
}
