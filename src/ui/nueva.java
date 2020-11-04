package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class nueva {

	public static void main() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n;
		n = br.read();

		int[] a = new int[10001];

		for (int i = 1; i <= n; i++) {
			a[i] = br.read();
		}

		int flag = 0;
		for (int i = 1; i <= n; i++) {

			if (i == a[a[a[i]]]) {
				System.out.println("YES");
				flag = 1;
				break;
			}
		}
		if (flag == 0)
			System.out.println("NO");
	}
}
