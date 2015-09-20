package treelayout;

import java.util.Arrays;

import treelayout.GenerateTrees;
import treelayout.TreeNode;

import treelayout.algorithm.Marshall;

public class Measure {

    public static int MAX_SIZE = 10000;
    public static int INCREMENT = 100;
    public static int NR_TESTS = 200;
    public static int NR_WARMUP = 100;
    public static long SEED = 42;
    
    static long timeLayout(TreeNode tree){
        System.gc();
        Marshall m = new Marshall();
        Object converted = m.convert(tree);
        long start = System.nanoTime();
        m.runOnConverted(converted);
        long now = System.nanoTime();
        return now - start;
    }

    static long runTests(GenerateTrees gen,  int nrTests) {
        for (int i = 0 ; i < nrTests * gen.nr ; i++){
            TreeNode tree = gen.rand();
            long res = timeLayout(tree);
            System.out.printf("%d %d\n",gen.nr,res);
        }
        return 0;
    }
    
    public static void main(String[] argv){
        for (int i = 1; i < MAX_SIZE; i+= INCREMENT) {
            GenerateTrees gen = new GenerateTrees(i, 1, 10, 1, 10, 5000);
            runTests(gen, NR_TESTS);
        }
    }
    
}
