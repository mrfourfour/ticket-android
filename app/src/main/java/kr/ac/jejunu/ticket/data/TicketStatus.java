package kr.ac.jejunu.ticket.data;

public enum TicketStatus  {
    ON_PROGRESS("사용중"),
    NOT_USED("미사용"),
    USED("사용됨"),
    CANCELED("취소");

    private String value;
    TicketStatus(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}