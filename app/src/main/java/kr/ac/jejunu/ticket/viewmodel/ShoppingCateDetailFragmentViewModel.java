package kr.ac.jejunu.ticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import kr.ac.jejunu.ticket.CategoryListQuery;
import kr.ac.jejunu.ticket.repository.CateListDataRepository;

public class ShoppingCateDetailFragmentViewModel extends AndroidViewModel {
    private final CateListDataRepository repository;
    private LiveData<CategoryListQuery.Data> list;
    public ShoppingCateDetailFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new CateListDataRepository();
        this.list =repository.getList();
    }
    public LiveData<CategoryListQuery.Data> getAllProduct()  {return list;}
}
