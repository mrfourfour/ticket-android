package kr.ac.jejunu.ticket.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloMutationCall;
import com.apollographql.apollo.api.Mutation;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.ticket.OrderQueryMutation;
import kr.ac.jejunu.ticket.ProductByIdQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.adapter.SpinnerAdapter;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;
import kr.ac.jejunu.ticket.application.AppApplication;
import kr.ac.jejunu.ticket.databinding.ProductDetailFragmentBinding;
import kr.ac.jejunu.ticket.viewmodel.ProductDataViewModel;

public class ProductDetailFragment extends Fragment {

    private static final String TAG = ProductDetailFragment.class.getSimpleName();

    private BottomSheetBehavior sheetBehavior;
    private ProductDetailFragmentBinding binding;
    private ProductDataViewModel viewModel;
    private List<ProductByIdQuery.Option> options;
    private SpinnerAdapter adapter;
    private String optionId;
    private int countNum =0;
    private int price;
    private Context context;
    public ProductDetailFragment(){}

    public static ProductDetailFragment newInstance() {
        return new ProductDetailFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_detail_fragment, container, false);
        viewModel = new ProductDataViewModel(getActivity().getApplication(), getArguments().getString("id"));
        context = container.getContext();
        options = new ArrayList<>();
        getProduct();
        setupViews();
        return binding.getRoot();
    }

    private void getProduct() {
        viewModel.getProductList().observe(this, product -> {
            binding.setProduct(product.productById());
            price = product.productById().price();
            options.addAll(product.productById().options());
        });
    }

    private void setupViews() {
        binding.order.count.setText(String.valueOf(countNum));
        binding.order.button.setOnClickListener(this::Click);
        adapter = new SpinnerAdapter(getContext(),options,this::getOptionId);
        binding.order.spinner.setAdapter(adapter);
        binding.order.minus.setOnClickListener(this::minusClick);
        binding.order.plus.setOnClickListener(this::plusClick);
        sheetBehavior = BottomSheetBehavior.from(binding.order.bottomSheet);
        binding.order.orderLayout.setOnClickListener(v -> {
            if (sheetBehavior.getState() !=
                    BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                binding.order.statusImage.setImageResource(R.drawable.down);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                binding.order.statusImage.setImageResource(R.drawable.up);
            }
        });

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
            }
        });
    }

    private void getOptionId(String optionId) {
        this.optionId = optionId;
    }

    private void plusClick(View view) {
        countNum++;
        binding.order.count.setText(String.valueOf(countNum));
        binding.order.totalprice.setText(String.valueOf(countNum *price));
    }

    private void minusClick(View view) {
        countNum--;
        binding.order.count.setText(String.valueOf(countNum));
        binding.order.totalprice.setText(String.valueOf(countNum*price));
    }

    private void itemClick(AdapterView<?> adapterView, View view, int i, long l) {
        optionId = options.get(i).id();
    }

    private void Click(View view) {
        String productId = getArguments().getString("id");
        OrderQueryMutation query = new OrderQueryMutation(countNum,countNum*price,optionId,productId);
        ApolloRequest.getApolloInstance(((AppApplication)getActivity().getApplication()).getToken()).mutate(query).enqueue(new ApolloCall.Callback<OrderQueryMutation.Data>() {
            @Override
            public void onResponse(@NotNull Response<OrderQueryMutation.Data> response) {
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
            }
        });
    }
}
