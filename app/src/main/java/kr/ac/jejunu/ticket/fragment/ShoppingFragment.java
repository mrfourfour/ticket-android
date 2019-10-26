package kr.ac.jejunu.ticket.fragment;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.activity.DetailActivity;
import kr.ac.jejunu.ticket.adapter.shoppingcateadpater.ShoppingBestCategoryAdapter;
import kr.ac.jejunu.ticket.adapter.shoppingcateadpater.ShoppingCategoryParentAdapter;
import kr.ac.jejunu.ticket.databinding.ShoppingMainFragmentBinding;
import kr.ac.jejunu.ticket.viewmodel.ShoppingFragmentViewModel;

public class ShoppingFragment extends Fragment {

    private static final String TAG = ShoppingFragment.class.getSimpleName();
    private ShoppingMainFragmentBinding binding;
    private ArrayList<String> categorys;
    private ArrayList<String> cateAreas;
    private ShoppingFragmentViewModel viewModel;
    private ShoppingCategoryParentAdapter adapter;
    private ShoppingBestCategoryAdapter bestAdapter;
    private NavController controller;
    private String areaName = "SEOUL";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(ShoppingFragmentViewModel.class);
        categorys = new ArrayList<>();
        cateAreas = new ArrayList<>();

        getCategoryList();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.shopping_main_fragment, container, false);

        bestCateView();
        setupViews();
        tab(cateAreas);
        shoppingCateView(areaName);
        binding.toolbar.setTitle("어벤져스");
        return binding.getRoot();
    }

    private void bestCateView() {
        RecyclerView bestRecycler = binding.recyclerView;
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        bestRecycler.setLayoutManager(manager1);
        bestAdapter = new ShoppingBestCategoryAdapter();
        bestRecycler.setHasFixedSize(true);
        bestRecycler.setAdapter(bestAdapter);
    }

    private void shoppingCateView(String areaName) {
        RecyclerView recyclerView = binding.mainRecyclerview;
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        adapter = new ShoppingCategoryParentAdapter(categorys, getActivity(), this::ClickDetail, areaName,controller);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void ClickDetail(String category) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("area",areaName);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }

    private void getCategoryList() {
        viewModel.getProductCategoryList().observe(this, list -> {
            List<String> category = list.categoryList();
            categorys.addAll(category);
            if (adapter != null) adapter.notifyDataSetChanged();
        });
        viewModel.getProductCategoryList().observe(this,area ->{
            List<String> cateArea = area.areaList();
            cateAreas.addAll(cateArea);
            tab(cateAreas);
        });

    }

    private void tab(ArrayList<String> cateAreas) {
        for (String title : cateAreas) {
            binding.tableLayout2.addTab(binding.tableLayout2.newTab().setText(title));
        }
    }

    private void setupViews() {
        try {
            NavHostFragment host = Optional.ofNullable((NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.main_controller)).orElseThrow(Exception::new);
            controller = host.getNavController();
        } catch (Throwable e) { e.printStackTrace(); }

        binding.tableLayout2.addOnTabSelectedListener(new BaseOnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                areaName = tab.getText().toString();
                shoppingCateView(areaName);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(getContext(), tab.getText() + " 이 이미 선택되어있습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
