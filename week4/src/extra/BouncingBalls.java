package extra;

import edu.princeton.cs.algs4.CollisionSystem;
import edu.princeton.cs.algs4.Particle;
import edu.princeton.cs.algs4.StdDraw;

public class BouncingBalls {
    public static void main(String[] args) {
//        ballTest();
        CollisionTest();
    }
    private static void ballTest(){
        int N=1000;
        Ball[] balls=new Ball[N];
        for(int i=0;i<N;i++){
            balls[i]=new Ball();
        }
        StdDraw.enableDoubleBuffering();
        while(true){
            StdDraw.clear();
            for (Ball b : balls) {
                StdDraw.setPenColor(b.R, b.G, b.B);
                b.move(0.5);
                b.draw();
            }
            StdDraw.show();
        }
    }

    private static void CollisionTest(){
        int size=100;
        Particle[] particles=new Particle[size];
        for(int i=0;i<100;i++){
            particles[i]=new Particle();
        }
        CollisionSystem collisionSystem=new CollisionSystem(particles);
        collisionSystem.simulate(50);
    }
}
