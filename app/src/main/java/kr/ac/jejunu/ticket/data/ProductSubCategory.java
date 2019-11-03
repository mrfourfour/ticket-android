package kr.ac.jejunu.ticket.data;


public enum ProductSubCategory {
    SEA("해양", ProductByCategory.LEISURE),
    LAND("육상", ProductByCategory.LEISURE),
    SPA("스파", ProductByCategory.LEISURE),

    EXPO("박람회", ProductByCategory.EXHIBITION),
    DISPLAY("전시회", ProductByCategory.EXHIBITION),
    MUSEUM("박물관", ProductByCategory.EXHIBITION),

    FESTIVAL("축제", ProductByCategory.TOUR),
    ATTRACTION("명소", ProductByCategory.TOUR),

    TROT("트로트", ProductByCategory.CONCERT),
    ROCK("락", ProductByCategory.CONCERT),
    HIPHOPNRAP("힙합/랩", ProductByCategory.CONCERT),
    BALLAD("발라드", ProductByCategory.CONCERT),
    IDOL("아이돌", ProductByCategory.CONCERT),
    INDE("인디", ProductByCategory.CONCERT),
    OVERSEA("내한", ProductByCategory.CONCERT),

    SMALLHALL("소극장", ProductByCategory.THEATER),
    MUSICAL("뮤지컬", ProductByCategory.THEATER),
    THEATERETC("기타", ProductByCategory.THEATER);

    private String value;
    private ProductByCategory parent;

    ProductSubCategory(String subCategory, ProductByCategory parent) {
        this.value = subCategory;
        this.parent = parent;
    }
    public String getKey() {
        return name();
    }
    public ProductByCategory getParentProductCategory() { return parent; }
    public String getValue() {
        return value;
    }
}