package kr.ac.jejunu.ticket.adapter.shoppingcateadpater;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import kr.ac.jejunu.ticket.ProductByCategoryQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.CategoryShoppingMainItemBinding;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;

public class ShoppingCategoryParentAdapter extends RecyclerView.Adapter<ShoppingCategoryParentAdapter.ParentViewHolder> {

    private static final String TAG = ShoppingCategoryParentAdapter.class.getSimpleName();
    private final ArrayList<String> productCategory;
    private final Activity context;
    private CategoryShoppingMainItemBinding binding;
    private final RecyclerView.RecycledViewPool recycledViewPool;

    public ShoppingCategoryParentAdapter(ArrayList<String> productCategory, Activity context) {
        this.productCategory = productCategory;
        this.context =context;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public ParentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.category_shopping_main_item,parent,false);

        return new ParentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewHolder holder, int position) {
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.bind.category.setText(productCategory.get(position));
        holder.adapter = new ShoppingCategoryChildAdapter();
        getProducts(position, holder.adapter);
        holder.bind.recyclerHorizontal.setAdapter(holder.adapter);
        holder.bind.recyclerHorizontal.setRecycledViewPool(recycledViewPool);
        holder.bind.recyclerHorizontal.setLayoutManager(manager);
        holder.bind.recyclerHorizontal.setHasFixedSize(true);
        holder.bind.recyclerHorizontal.setNestedScrollingEnabled(false);
        holder.bind.recyclerHorizontal.setItemAnimator(new DefaultItemAnimator());
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(holder.bind.recyclerHorizontal);
    }

    private void getProducts(int position, final ShoppingCategoryChildAdapter adapter) {
        ProductByCategoryQuery query = new ProductByCategoryQuery(Input.fromNullable(productCategory.get(position)));
        ApolloRequest.getApolloInstance().query(query).enqueue(new ApolloCall.Callback<ProductByCategoryQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<ProductByCategoryQuery.Data> response) {
                adapter.addList(response.data().productByCategory());
                context.runOnUiThread(adapter::notifyDataSetChanged);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                Log.d(TAG,"문제있어요.");
            }
        });
    }

    @Override
    public int getItemCount() {
        return productCategory.size();
    }

    class ParentViewHolder extends RecyclerView.ViewHolder {
        private final CategoryShoppingMainItemBinding bind;
        private ShoppingCategoryChildAdapter adapter;
        public ParentViewHolder(@NonNull CategoryShoppingMainItemBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
        }
    }
}
