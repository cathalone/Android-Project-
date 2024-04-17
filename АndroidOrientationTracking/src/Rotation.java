public class Rotation {
    public static void pointRotationX(Point point, float phi) {
        float y = point.getY();
        float z = point.getZ();
        float new_y = (float) (Math.cos(phi) * y - Math.sin(phi) * z);
        float new_z = (float) (Math.sin(phi) * y + Math.cos(phi) * z);
        point.setY(new_y);
        point.setZ(new_z);
    }

    public static void pointRotationXWithMemory(Point point, Point defaultPoint, float phi) {
        float y = defaultPoint.getY();
        float z = defaultPoint.getZ();
        float new_y = (float) (Math.cos(phi) * y - Math.sin(phi) * z);
        float new_z = (float) (Math.sin(phi) * y + Math.cos(phi) * z);
        point.setY(new_y);
        point.setZ(new_z);
    }

    public static void pointRotationY(Point point, float phi) {
        float x = point.getX();
        float z = point.getZ();
        float new_x = (float) (Math.cos(phi) * x + Math.sin(phi) * z);
        float new_z = (float) (-Math.sin(phi) * x + Math.cos(phi) * z);
        point.setX(new_x);
        point.setZ(new_z);
    }

    public static void pointRotationYWithMemory(Point point, Point defaultPoint, float phi) {
        float x = defaultPoint.getX();
        float z = point.getZ();
        float new_x = (float) (Math.cos(phi) * x + Math.sin(phi) * z);
        float new_z = (float) (-Math.sin(phi) * x + Math.cos(phi) * z);
        point.setX(new_x);
        point.setZ(new_z);
    }

    public static void pointRotationZ(Point point, float phi) {
        float x = point.getX();
        float y = point.getY();
        float new_x = (float) (Math.cos(phi) * x - Math.sin(phi) * y);
        float new_y = (float) (Math.sin(phi) * x + Math.cos(phi) * y);
        point.setX(new_x);
        point.setY(new_y);
    }

    public static void pointRotationZWithMemory(Point point, Point defaultPoint, float phi) {
        float x = point.getX();
        float y = point.getY();
        float new_x = (float) (Math.cos(phi) * x - Math.sin(phi) * y);
        float new_y = (float) (Math.sin(phi) * x + Math.cos(phi) * y);
        point.setX(new_x);
        point.setY(new_y);
    }

    public static void pointRotation(Point point, float alpha, float beta, float gamma) {
        pointRotationX(point, alpha);
        pointRotationY(point, beta);
        pointRotationZ(point, gamma);
    }

    public static void pointRotationWithMemory(Point point, Point defaultPoint, float alpha, float beta, float gamma) {
        pointRotationXWithMemory(point, defaultPoint, alpha);
        pointRotationYWithMemory(point, defaultPoint, beta);
        pointRotationZWithMemory(point, defaultPoint, gamma);
    }

    public static void pointsRotation(Point[] points, float alpha, float beta, float gamma) {
        for (Point point : points) {
            pointRotation(point, alpha, beta, gamma);
        }
    }

    public static void pointsRotationWithMemory(Point[] points, Point[] defaultPoints, float alpha, float beta, float gamma) {
        for (int i = 0; i < points.length; i++) {
            pointRotationWithMemory(points[i], defaultPoints[i], alpha, beta, gamma);
        }
    }
}

