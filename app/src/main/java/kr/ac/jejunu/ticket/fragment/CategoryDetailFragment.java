package kr.ac.jejunu.ticket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.activity.DetailActivity;
import kr.ac.jejunu.ticket.databinding.CategoryDetailFragmentBinding;

public class CategoryDetailFragment extends Fragment {

    private static final String TAG = CategoryDetailFragment.class.getSimpleName();
    private CategoryDetailFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.category_detail_fragment,container,false);
//        binding.textView.setText(getArguments().getString("category"));

//        DetailActivity activity = (DetailActivity) getActivity();
//        String cate = activity.getCate();
//        String area = activity.getArea();

//        Intent intent = getActivity().getIntent();
//        Bundle bundle = intent.getExtras();
//        String cate = bundle.getString("category");
//        String area = bundle.getString("area");
        Log.d(TAG,getArguments().getString("category"));
//        binding.textView.setText(
//                cate + "-" + area
//        );
        return binding.getRoot();
    }
}
