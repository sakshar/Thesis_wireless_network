/**
 * Created by Asus on 3/14/2018.
 */
public class Vector {
    public Point point;

    Vector(Point p){
        point = new Point();
        point.x = p.x;
        point.y = p.y;
    }

    public double dot(Vector that){
        return this.point.x*that.point.x + this.point.y*that.point.y;
    }

    public double magnitude(){
        return Math.sqrt(this.dot(this));
    }

    public Vector plus(Vector that){
        Point p = new Point();
        p.x = this.point.x+that.point.x;
        p.y = this.point.y+that.point.y;
        return new Vector(p);
    }

    public Vector minus(Vector that){
        Point p = new Point();
        p.x = this.point.x-that.point.x;
        p.y = this.point.y-that.point.y;
        return new Vector(p);
    }

    public double distanceTo(Vector that){
        return this.minus(that).magnitude();
    }

    public Vector times(double factor){
        Point p = new Point();
        p.x = this.point.x*factor;
        p.y = this.point.y*factor;
        return new Vector(p);
    }

    public Vector direction(){
        return this.times(1.0/this.magnitude());
    }

    public String toString(){
        return "< "+this.point.x + ", " + this.point.y+ " >";
    }
}
