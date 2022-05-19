package utils;

import java.util.Random;
import java.util.Scanner;

/**
 * 根据传入的范围生成随机数
 */
public class RandomNumberGenerator {

    private static Random random;

    private RandomNumberGenerator(){
    }

    /**
     * 生成 【l, r】之间的随机数
     * @param l 区间最小值
     * @param r 区间最大值
     * @return 【l, r】中的随机数
     */
    public static int generate(int l, int r){

        int ret = 0;
        random = new Random();

        ret += l + random.nextInt(r - l + 1);

        return ret;
    }
}
