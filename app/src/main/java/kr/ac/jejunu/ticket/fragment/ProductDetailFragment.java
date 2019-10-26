package kr.ac.jejunu.ticket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.Optional;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.ProductDetailFragmentBinding;
import kr.ac.jejunu.ticket.viewmodel.ProductDataViewModel;

public class ProductDetailFragment extends Fragment {

    private static final String TAG = ProductDetailFragment.class.getSimpleName();

    private BottomSheetBehavior sheetBehavior;
    private ProductDetailFragmentBinding binding;
    private ProductDataViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_detail_fragment, container, false);
        viewModel = new ProductDataViewModel(getActivity().getApplication(), getArguments().getString("id"));
        getProduct();
        setupViews();
        return binding.getRoot();
    }

    private void getProduct() {
        viewModel.getProductList().observe(this, product -> binding.setProduct(product.productById()));
    }

    private void setupViews() {
        sheetBehavior = BottomSheetBehavior.from(binding.order);
        binding.order.setOnClickListener(v -> {
            if (sheetBehavior.getState() !=
                    BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            } else {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
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
}
