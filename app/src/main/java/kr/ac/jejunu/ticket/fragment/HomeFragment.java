package kr.ac.jejunu.ticket.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.transition.TransitionInflater;

import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.List;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.TicketQuery;
import kr.ac.jejunu.ticket.adapter.mainticketadapter.CurrentTicketRecyclerAdapter;
import kr.ac.jejunu.ticket.databinding.HomeFragmentBinding;
import kr.ac.jejunu.ticket.viewmodel.HomeFragmentViewModel;


public class HomeFragment extends Fragment {

    private float width;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private static final String TAG = HomeFragment.class.getSimpleName();
    private ArrayList<TicketQuery.AllTicket> tickets;
    private CurrentTicketRecyclerAdapter adapter;
    private HomeFragmentViewModel viewModel;
    private HomeFragmentBinding binding;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        tickets = new ArrayList<>();
        getTickets();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =
                DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false);
        setupViews();

        return binding.getRoot();
    }

    private void getTickets() {
        viewModel.getTicketLiveData().observe(this,ticket -> {
            List<TicketQuery.AllTicket> currentTicket = ticket.allTicket();
            tickets.addAll(currentTicket);
            if (adapter != null) adapter.notifyDataSetChanged();
        });
    }

    private void sharedFragment(Bitmap bitmap, View view) {
        QRcodeFragment qRcodeFragment = new QRcodeFragment(bitmap);
        setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));
        setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.fade));

        qRcodeFragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_transform));
        qRcodeFragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.fade));

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_controller, qRcodeFragment);
        transaction.addToBackStack(null);
        transaction.addSharedElement(view, "QRCode");
        transaction.commit();
    }

    private void setupViews() {
        SnapHelper snapHelper = new LinearSnapHelper();
        RecyclerView recyclerView = binding.transitionCurrentTicket;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        width = dm.widthPixels;
        int left = ((int) width) / 4;
        recyclerView.setPadding(left, 0, left, 0);

        adapter = new CurrentTicketRecyclerAdapter(tickets, this::sharedFragment, width,getActivity().getApplication(),getActivity());
        recyclerView.setAdapter(adapter);
        if (tickets.size() == 0) binding.container.setBackgroundResource(R.drawable.ring);
    }
}
