public class Project {

    private int id;
    private String title;
    private String summary;
    private String type;

    public Project(int id, String title, String summary, String type) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.type = type;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getSummary() { return summary; }
    public String getType() { return type; }



}