package Entity;

public class Survey {

    private String name;

    private Topic topic;

    Survey(){
    }

    public Survey(String name, Topic topic) {
        this.name = name;
        this.topic = topic;
    }

    public String getName() {
        return name;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
