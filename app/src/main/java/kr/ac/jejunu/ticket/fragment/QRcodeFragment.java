package kr.ac.jejunu.ticket.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.QrcodeTransitionBinding;

public class QRcodeFragment extends Fragment {


    public QRcodeFragment() {
    }

    public static QRcodeFragment newInstance() {
        return new QRcodeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(R.transition.change_image_transform));
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        QrcodeTransitionBinding binding = DataBindingUtil.inflate(inflater, R.layout.qrcode_transition, container, false);
        View view = binding.getRoot();
        return view;
    }
}
