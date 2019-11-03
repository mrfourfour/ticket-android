package kr.ac.jejunu.ticket.data;

public enum ProductArea {
    SEOUL("서울"),
    INCHEON("인천"),
    DAEGU("대구"),
    DAEJEON("대전"),
    BUSAN("부산"),
    ULSAN("울산"),
    GWANGJU("광주"),
    GYEONGGI("경기"),
    GANGWON("강원"),
    CHUNGCHEONG("충청"),
    JEOLLA("전라"),
    GYEONGSANG("경상"),
    JEJU("제주");

    private String value;
    ProductArea(String value) {
        this.value = value;
    }

    public String getKey() { return name(); }
    public String getValue() { return value; }
}