package graph;

/**
 * Generally Edge will be containing the Source and Destination vertex and Weight.
 * This may valid to contain isDirected when we are working with both Directed/UnDirected Edges. 
 * 
 * @author ksugumar
 *
 * @param <T>
 */
public class GEdge<T>{
    private boolean isDirected = false;
    private GVertex<T> vertex1;
    private GVertex<T> vertex2;
    private int weight;
    
    GEdge(GVertex<T> vertex1, GVertex<T> vertex2){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    GEdge(GVertex<T> vertex1, GVertex<T> vertex2,boolean isDirected,int weight){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
        this.isDirected = isDirected;
    }
    
    GEdge(GVertex<T> vertex1, GVertex<T> vertex2,boolean isDirected){
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.isDirected = isDirected;
    }
    
    GVertex<T> getVertex1(){
        return vertex1;
    }
    
    GVertex<T> getVertex2(){
        return vertex2;
    }
    
    int getWeight(){
        return weight;
    }
    
    public boolean isDirected(){
        return isDirected;
    }

    @Override
    public int hashCode() {
        
        return Objects.hash(vertex1, vertex2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        GEdge other = (GEdge) obj;
        if (vertex1 == null) {
            if (other.vertex1 != null)
                return false;
        } else if (!vertex1.equals(other.vertex1))
            return false;
        if (vertex2 == null) {
            if (other.vertex2 != null)
                return false;
        } else if (!vertex2.equals(other.vertex2))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Edge [isDirected=" + isDirected + ", vertex1=" + vertex1
                + ", vertex2=" + vertex2 + ", weight=" + weight + "]";
    }
}
