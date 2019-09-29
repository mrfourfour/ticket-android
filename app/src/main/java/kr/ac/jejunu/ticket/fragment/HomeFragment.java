package kr.ac.jejunu.ticket.fragment;

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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.transition.TransitionInflater;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.adapter.CurrentTicketRecyclerAdapter;
import kr.ac.jejunu.ticket.databinding.HomeFragmentBinding;
import kr.ac.jejunu.ticket.model.Ticket;
import kr.ac.jejunu.ticket.viewmodel.HomeFragmentViewModel;


public class HomeFragment extends Fragment {

    private float width;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private static final String TAG = HomeFragment.class.getSimpleName();
    private SnapHelper snapHelper;
    private RecyclerView recyclerView;
    private ArrayList<Ticket> tickets;
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
        viewModel.getTicketLiveData().observe(this, ticket -> {
            if (ticket != null) {
                List<Ticket> currentTicket = ticket.getData();
                tickets.addAll(currentTicket);

                if (adapter != null) adapter.notifyDataSetChanged();
            }
        });
    }

    private void sharedFragment(String ticket_qrcode, View view) {
        QRcodeFragment qRcodeFragment = QRcodeFragment.newInstance();
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
        snapHelper = new LinearSnapHelper();
        recyclerView = binding.transitionCurrentTicket;
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        snapHelper.attachToRecyclerView(recyclerView);

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getRealMetrics(dm);
        width = dm.widthPixels;
        int left = ((int) width) / 4;
        recyclerView.setPadding(left, 0, left, 0);

        adapter = new CurrentTicketRecyclerAdapter(tickets, (item, view) -> {
            Log.d(TAG, item.getTicket_qrdata());
            sharedFragment(item.getTicket_qrdata(), view);
        }, width);
        recyclerView.setAdapter(adapter);
    }
}
