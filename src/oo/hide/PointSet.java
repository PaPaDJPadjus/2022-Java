package oo.hide;

import java.util.Arrays;
import java.util.Objects;



public class PointSet {
    public static void main(String[] args) {
        PointSet a = new PointSet();
        PointSet b = new PointSet();

        a.add(new Point(1, 1));

        System.out.println(a.equals(b));
    }
    private Point[] points;
    private int index = 0;

    public PointSet(int capacity) {
        points = new Point[capacity];
    }

    public PointSet() {
        this(10);
    }

    public void add(Point point) {
        if (!contains(point)) {
            if (points.length == index) {
                points = Arrays.copyOf(points, points.length * 2);
            }
            points[index] = point;
            index += 1;
        }
    }

    public int size() {
        int lengthOfArray = 0;
        for (Point el : points) {
            if (el != null) {
                lengthOfArray += 1;
            }
        }
        return lengthOfArray;
    }

    public boolean contains(Point point) {
        for (Point el : points) {
            if (el != null && Objects.equals(point.x, el.x) && Objects.equals(point.y, el.y)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {
        String setToString = "";
        int indexCounter = 0;

        for (Point el : points) {
            if (el != null && indexCounter <= index) {
                setToString = setToString + "(%s, %s)".formatted(el.x, el.y);
                indexCounter += 1;
                if (indexCounter < index) {
                    setToString += ", ";
                }
            }
        }
        return setToString;
    }

    public PointSet subtract(PointSet other) {
        PointSet subtractedSet = new PointSet(10);


        for (Point pointFromThis : points) {
            if (pointFromThis != null) {
                int checkForEqualsElement = 0;
                for (Point pointFromOther : other.points) {
                    if (pointFromOther != null && pointFromOther.equals(pointFromThis)) {
                            checkForEqualsElement++;
                    }
                }
                if (checkForEqualsElement < 1) {
                    subtractedSet.add(pointFromThis);
                }
            }
        }
        return subtractedSet;
    }


    public PointSet intersect(PointSet other) {
        PointSet intersectedArray = new PointSet(10);

        int setIndex = 0;
        for (Point pointFromOther : other.points) {
            if (pointFromOther != null && contains(pointFromOther)) {
                    intersectedArray.points[setIndex] = pointFromOther;
                    setIndex++;
            }

        }
        return intersectedArray;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PointSet)) {
            return false;
        }
        int cycleCounter = 0;
        for (Point el : ((PointSet) obj).points) {
            cycleCounter++;
            if (el != null && !contains(el)) {
                return false;
            }
            if (el == null && this.size() != 0) {
                if (cycleCounter > this.size()) {
                    break;
                }
                return false;
            }
        }
        return true;
    }

    public void remove(Point point) {
    }
}
