package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MergeD {

public static final String FILE = "testcases"+File.separator+"arraystosort.in";
	
	private String show;
	
	public static void main(String[] args) {
		MergeD m = new MergeD();
		try{
			m.sort();
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public MergeD() {
		show = "";
	}
	
	public void sort() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE));
		String a = null;
		int line = 1;
		a = br.readLine();
		while(a!=null) {
			String[] b = a.split(" ");
			int[] arr = new int[b.length];
			for(int i=0;i<b.length;i++) {
				arr[i] = Integer.parseInt(b[i]);
			}
			for(int t:arr) {
				show += t+" ";
			}
			arr = mergeSort(arr);
			File file = new File("testcases"+File.separator+"mergeA-"+line+".out");
			PrintWriter pw = new PrintWriter(file);
			pw.print(show);
			line++;
			a = br.readLine();
			pw.close();
		}
		br.close();
	}
	
	public int[] mergeSort(int[] arr) {
		int lgt = arr.length;
		if(lgt<2)return arr;
		int mid = arr.length/2;
		int[] l = new int[mid];
		int[] r = new int[lgt-mid];
		for(int i=0;i<mid;i++){
			l[i] = arr[i];
		}
		for(int j=mid;j<lgt;j++) {
			r[j-mid] = arr[j];
		}
		int[] lr = new int[lgt];
		l = mergeSort(l);
		r = mergeSort(r);
		lr = merge(arr,l,r,mid,lgt-mid);
		return lr;
	}
	
	public int[] merge(int[] lr, int[] l, int[] r, int lf, int rg) {
		int i=0, j=0, k=0;
		while(i<lf&&j<rg) {
			if(l[i]<=r[j]) {
				lr[k++] = l[i++];
			}else {
				lr[k++] = r[j++];
			}
		}
		while (i < lf) {
	        lr[k++] = l[i++];
	    }
	    while (j < rg) {
	        lr[k++] = r[j++];
	    }
	    show += "{ ";
		for(int t:lr) {
			show += t+" ";
		}
		show += "}\n";
	    return lr;
	}
}
