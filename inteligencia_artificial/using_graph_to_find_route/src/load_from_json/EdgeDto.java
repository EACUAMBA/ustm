package load_from_json;

public class EdgeDto {
    public String id;
    public String name;
    public double weight;
    public NodeDto leftNode;
    public NodeDto rightNode;

    public EdgeDto() {
    }

    public EdgeDto(String id, String name, double weight, NodeDto leftNode, NodeDto rightNode) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public NodeDto getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(NodeDto leftNode) {
        this.leftNode = leftNode;
    }

    public NodeDto getRightNode() {
        return rightNode;
    }

    public void setRightNode(NodeDto rightNode) {
        this.rightNode = rightNode;
    }
}