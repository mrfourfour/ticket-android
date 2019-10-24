package kr.ac.jejunu.ticket.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import kr.ac.jejunu.ticket.CategoryListQuery;
import kr.ac.jejunu.ticket.repository.ProductListDataRepository;

public class ShoppingFragmentViewModel extends AndroidViewModel {
    private final ProductListDataRepository repository;
    private LiveData<CategoryListQuery.Data> productCategoryList;

    public ShoppingFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductListDataRepository();
        this.productCategoryList = repository.getList();

    }

    public LiveData<CategoryListQuery.Data> getProductCategoryList(){ return productCategoryList;}
}
