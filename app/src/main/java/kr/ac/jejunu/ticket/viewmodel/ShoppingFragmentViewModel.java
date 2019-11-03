package kr.ac.jejunu.ticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import kr.ac.jejunu.ticket.CategoryListQuery;
import kr.ac.jejunu.ticket.application.AppApplication;
import kr.ac.jejunu.ticket.repository.CateListDataRepository;

public class ShoppingFragmentViewModel extends AndroidViewModel {
    private final CateListDataRepository repository;
    private LiveData<CategoryListQuery.Data> productCategoryList;
    public ShoppingFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new CateListDataRepository((AppApplication)application);
        this.productCategoryList = repository.getList();
    }

    public LiveData<CategoryListQuery.Data> getProductCategoryList() {return productCategoryList;}
}
