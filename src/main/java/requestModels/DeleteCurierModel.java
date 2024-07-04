package requestModels;

public class DeleteCurierModel {
    private String id;

    public DeleteCurierModel(String id) {
        this.id = id;
    }

    public DeleteCurierModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
