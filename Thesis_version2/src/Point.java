/**
 * Created by Asus on 3/14/2018.
 */
public class Point {
    public double x;
    public double y;

    Point(){

    }

    Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object object){
        if (object != null && object instanceof Point)
        {
            if(this.x == ((Point) object).x && this.y == ((Point) object).y)
                return true;
        }
        return false;
    }
}
