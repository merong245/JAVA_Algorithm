package backjoon;

import java.io.IOException;
import java.util.Scanner;

public class bj_4375 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int MAX_VALUE = 10001;
        while(sc.hasNext()){
            int n = sc.nextInt();

            int temp = 0;
            for(int i = 1; i<MAX_VALUE; i++){
                // 필요한 것은 나머지 값
                // 나머지 연산으로 값의 범위를 줄여 시간과 overflow를 조절
                temp = (temp*10 +1)%n;
                if(temp%n == 0){
                    System.out.println(i);
                    break;

                }

            }

        }

    }
}
