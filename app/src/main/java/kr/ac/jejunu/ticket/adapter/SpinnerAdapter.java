package kr.ac.jejunu.ticket.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import java.util.List;

import kr.ac.jejunu.ticket.ProductByIdQuery;
import kr.ac.jejunu.ticket.R;
import kr.ac.jejunu.ticket.databinding.OptionSpinnerBinding;

public class SpinnerAdapter extends BaseAdapter implements android.widget.SpinnerAdapter{

     public interface Callback {
         void Callback(String optionId);
     }

    private static final String TAG = SpinnerAdapter.class.getSimpleName();
    private final Context context;
    private final List<ProductByIdQuery.Option> option;
    private OptionSpinnerBinding binding;
    private Callback callback;

    public SpinnerAdapter(Context context, List<ProductByIdQuery.Option> option,Callback callback) {
        this.context = context;
        this.option = option;
        this.callback = callback;
    }

    @Override
    public int getCount() {
        if (option.size() != 0) {
            return option.size();
        }else return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.option_spinner,parent,false);
        binding.setOptions(option.get(position));
//        String optionid = option.get(position).id();
        convertView = binding.getRoot();
        convertView.setOnClickListener(v -> callback.Callback(option.get(position).id()));
        return convertView;
    }
}
