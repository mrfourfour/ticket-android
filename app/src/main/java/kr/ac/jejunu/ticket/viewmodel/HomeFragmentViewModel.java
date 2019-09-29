package kr.ac.jejunu.ticket.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import kr.ac.jejunu.ticket.model.TicketResponse;
import kr.ac.jejunu.ticket.repository.TicketRepository;

public class HomeFragmentViewModel extends AndroidViewModel {

    private final TicketRepository ticketRepository;
    private LiveData<TicketResponse> ticketLiveData;

    public HomeFragmentViewModel(@NonNull Application application) {
        super(application);

        ticketRepository = new TicketRepository();
        this.ticketLiveData = ticketRepository.getTickets();
    }

    public LiveData<TicketResponse> getTicketLiveData() {
        return ticketLiveData;
    }
}
