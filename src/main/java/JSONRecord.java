public class JSONRecord {

    private String id;
    private long timestamp;
    private String status;

    public JSONRecord(String id, long timestamp) {
        this.id = id;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
