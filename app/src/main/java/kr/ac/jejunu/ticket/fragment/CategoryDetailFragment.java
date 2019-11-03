package kr.ac.jejunu.ticket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Input;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import kr.ac.jejunu.ticket.ProductByCategoryQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.activity.DetailActivity;
import kr.ac.jejunu.ticket.adapter.shoppingdetail.ShoppingCateDetailAdapter;
import kr.ac.jejunu.ticket.apollo.ApolloRequest;
import kr.ac.jejunu.ticket.application.AppApplication;
import kr.ac.jejunu.ticket.databinding.CategoryDetailFragmentBinding;
import kr.ac.jejunu.ticket.viewmodel.ShoppingCateDetailFragmentViewModel;

public class CategoryDetailFragment extends Fragment {

    private static final String TAG = CategoryDetailFragment.class.getSimpleName();
    private CategoryDetailFragmentBinding binding;
    private ShoppingCateDetailFragmentViewModel viewModel;
    private ShoppingCateDetailAdapter adpater;
    private NavController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShoppingCateDetailFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =
                DataBindingUtil
                        .inflate(LayoutInflater.from(getContext()), R.layout.category_detail_fragment, container, false);

        DetailActivity activity = (DetailActivity) getActivity();
        String cate = activity.getCate();
        String area = activity.getArea();
        String value = activity.getValueCate();
        ((DetailActivity) getActivity()).getSupportActionBar().setTitle(value);
        setupViews();
        getCateDetailProduct(cate,area);
        return binding.getRoot();
    }

    private void getCateDetailProduct(String cate ,String area) {
        ProductByCategoryQuery query =
                new ProductByCategoryQuery(Input.fromNullable(cate),Input.fromNullable(area));
        ApolloRequest
                .getApolloInstance(((AppApplication)getActivity().getApplication()).getToken())
                .query(query)
                .enqueue(new ApolloCall.Callback<ProductByCategoryQuery.Data>() {
                    @Override
                    public void onResponse(@NotNull Response<ProductByCategoryQuery.Data> response) {
                        adpater.addList(response.data().productByCategory());
                        getActivity().runOnUiThread(adpater::notifyDataSetChanged);
                    }

                    @Override
                    public void onFailure(@NotNull ApolloException e) {

                    }
                });
    }


    private void setupViews() {
        adpater = new ShoppingCateDetailAdapter();
        binding.detailCateRecycler.setHasFixedSize(true);
        binding.detailCateRecycler.setAdapter(adpater);

        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.detail_fragment_controller)).orElseThrow(Exception::new);
            controller = host.getNavController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
