package load_from_json;

import java.util.ArrayList;
import java.util.List;

public class GraphDto {
    private List<EdgeDto> edges = new ArrayList<>();

    public GraphDto() {
    }

    public GraphDto(List<EdgeDto> edges) {
        this.edges = edges;
    }

    public List<EdgeDto> getEdges() {
        return edges;
    }

    public void setEdges(List<EdgeDto> edges) {
        this.edges = edges;
    }
}
