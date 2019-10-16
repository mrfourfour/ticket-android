package kr.ac.jejunu.ticket.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.adapter.shoppingcateadpater.ShoppingBestCategoryAdapter;
import kr.ac.jejunu.ticket.adapter.shoppingcateadpater.ShoppingCategoryParentAdapter;
import kr.ac.jejunu.ticket.databinding.FragmentShoppingMainBinding;
import kr.ac.jejunu.ticket.viewmodel.ShoppingFragmentViewModel;

public class ShoppingFragment extends Fragment {

    private static final String TAG = ShoppingFragment.class.getSimpleName();
    private FragmentShoppingMainBinding binding;
    private ArrayList<String> categorys;
    private ShoppingFragmentViewModel viewModel;
    private ShoppingCategoryParentAdapter adapter;
    private ShoppingBestCategoryAdapter bestAdapter;

    private final String[] list = {"서울","인천","대구","대전","부산","울산","광주","경기","강원","충청","전라","경상","제주"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShoppingFragmentViewModel.class);
        categorys = new ArrayList<>();
        getCategoryList();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_shopping_main,container,false);

        shoppingCateView();
        bestCateView();
        setupViews();
        return binding.getRoot();
    }

    private void bestCateView() {
        RecyclerView bestRecycler = binding.recyclerView;
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        bestRecycler.setLayoutManager(manager1);
        bestAdapter = new ShoppingBestCategoryAdapter();
        bestRecycler.setHasFixedSize(true);
        bestRecycler.setAdapter(bestAdapter);
    }

    private void shoppingCateView() {
        RecyclerView recyclerView = binding.mainRecyclerview;
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        adapter = new ShoppingCategoryParentAdapter(categorys,getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void getCategoryList() {
        viewModel.getProductCategoryList().observe(this,list -> {
            List<String> category = list.categoryList();
            categorys.addAll(category);
            if (adapter != null) adapter.notifyDataSetChanged();
        });
    }

    private void setupViews() {
        //Todo tablayout item click -> 해당 지역 카테고리 보여주기
        for (String title : list) binding.tableLayout2.addTab(binding.tableLayout2.newTab().setText(title));
        binding.tableLayout2.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            //Todo 탭이 선택된 상태로 들어갈때 호출
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG,tab.getText().toString());
            }
            //Todo 탭이 선택된 상태를 벗어날 때 호출
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //Todo 사용자가 이미 선택한 탭을 다시 선택하면 호출
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
