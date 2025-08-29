package DesignPatterns.StrategyPattern;

public enum DBConnection {
    DBConnection;

    // volatile insures data is directly written in the main memory before being read anywhere else
    // we use volatile cz instance creation occurs in 3 steps // allocate memory , initialize object, set instance to point to the memory
    // new object() is being created by jvm automatically, no thread safety needed, but it will be slow as fuck and memory overloaded hehe

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
