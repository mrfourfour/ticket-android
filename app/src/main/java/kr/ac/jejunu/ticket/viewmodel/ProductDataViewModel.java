package kr.ac.jejunu.ticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import kr.ac.jejunu.ticket.ProductByIdQuery;
import kr.ac.jejunu.ticket.repository.ProductDataRepository;

public class ProductDataViewModel extends AndroidViewModel {
    private final ProductDataRepository repository;
    private LiveData<ProductByIdQuery.Data> productList;
    public ProductDataViewModel(@NonNull Application application,String id) {
        super(application);
        repository = new ProductDataRepository();
        this.productList = repository.getProduct(id);
    }
    public LiveData<ProductByIdQuery.Data> getProductList(){return productList;}

}
