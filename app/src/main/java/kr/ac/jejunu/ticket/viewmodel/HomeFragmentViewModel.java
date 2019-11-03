package kr.ac.jejunu.ticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import kr.ac.jejunu.ticket.ProductByIdQuery;
import kr.ac.jejunu.ticket.TicketQuery;
import kr.ac.jejunu.ticket.application.AppApplication;
import kr.ac.jejunu.ticket.repository.ProductDataRepository;
import kr.ac.jejunu.ticket.repository.TicketDataRepository;

public class HomeFragmentViewModel extends AndroidViewModel {

    private final TicketDataRepository repository;
    private LiveData<TicketQuery.Data> ticketLiveData;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        repository = new TicketDataRepository((AppApplication)application);
        this.ticketLiveData = repository.getTicket();
    }

    public LiveData<TicketQuery.Data> getTicketLiveData() { return ticketLiveData; }
}
