package kr.ac.jejunu.ticket.adapter.shoppingcateadpater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.ticket.ProductByCategoryQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.CategoryShoppingInnerItemBinding;

public class ShoppingCategoryChildAdapter extends RecyclerView.Adapter<ShoppingCategoryChildAdapter.ChildViewHolder> {

    private static final String TAG = ShoppingCategoryChildAdapter.class.getSimpleName();
    private final List<ProductByCategoryQuery.ProductByCategory> productCategoryItmes;
    private CategoryShoppingInnerItemBinding binding;

    public ShoppingCategoryChildAdapter() {
        this.productCategoryItmes = new ArrayList<>();
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
        if (productCategoryItmes.size() !=0) {
            holder.bind.setProductModel(productCategoryItmes.get(position));
        } else {
            holder.bind.imageView.setImageResource(R.drawable.preparing_product);
        }
    }

    @Override
    public int getItemCount() {
        if (productCategoryItmes.size() != 0) {
            return productCategoryItmes.size();
        }else return 1;
    }

    public void addList(List<ProductByCategoryQuery.ProductByCategory> productByCategory) {
        productCategoryItmes.addAll(productByCategory);
    }

    class ChildViewHolder extends RecyclerView.ViewHolder {
        private final CategoryShoppingInnerItemBinding bind;

        ChildViewHolder(@NonNull CategoryShoppingInnerItemBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
        }
    }
}
