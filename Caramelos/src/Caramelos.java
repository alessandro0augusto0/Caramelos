import java.util.*;

public class Caramelos {
    static final int MAXN = 110;
    static final int MAXSOMA = 10000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int soma = 0;
        int[] v = new int[MAXN];

        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            soma += v[i];
        }

        if (soma % 2 == 1) {
            System.out.println("-1");
            sc.close();
            return;
        }

        soma /= 2;

        boolean[][] pd = new boolean[MAXN][MAXSOMA];
        for (int j = 0; j <= soma; j++) {
            pd[0][j] = false;
        }
        pd[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= soma; j++) {
                pd[i][j] = pd[i - 1][j];
                if (v[i] <= j) {
                    pd[i][j] = pd[i][j] || pd[i - 1][j - v[i]];
                }
            }
        }

        if (!pd[n][soma]) {
            System.out.println("-1");
            sc.close();
            return;
        }

        List<Integer> alice = new ArrayList<>();
        List<Integer> bob = new ArrayList<>();
        int s = soma;

        for (int i = n; i > 0; i--) {
            if (s >= v[i] && pd[i - 1][s - v[i]]) {
                alice.add(v[i]);
                s -= v[i];
            } else {
                bob.add(v[i]);
            }
        }

        int s1 = 0, s2 = 0, prox = 0;

        for (int i = 0; i < n; i++) {
            if (s1 <= s2 && !alice.isEmpty()) {
                prox = alice.remove(alice.size() - 1);
                s1 += prox;
            } else if (!bob.isEmpty()) {
                prox = bob.remove(bob.size() - 1);
                s2 += prox;
            }
            System.out.print(prox + (i == n - 1 ? "\n" : " "));
        }

        sc.close();
    }
}