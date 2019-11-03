package kr.ac.jejunu.ticket.data;

public enum ProductByCategory {
    LEISURE("레져"),
    EXHIBITION("전시"),
    TOUR("여행"),
    CONCERT("콘서트"),
    THEATER("연극");

    private String value;
    ProductByCategory(String category) {
        this.value = category;
    }

    public String getKey() { return name(); }
    public String getValue() { return value; }
}
