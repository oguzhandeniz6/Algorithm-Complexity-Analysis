

import java.util.Arrays;

public class PigeonholeSort {
	
	public static void pigeonholeSort(int[] arr) 
	{
		int min = Arrays.stream(arr).min().getAsInt();
		int max = Arrays.stream(arr).max().getAsInt();
		
		int range = max - min + 1;
		
		int[] hole = new int[range];
		Arrays.fill(hole, 0);
		
		for(int i = 0; i < arr.length; i++) 
		{
			hole[arr[i] - min]++;
		}
		
		int index = 0;
		
		for(int i = 0; i < range; i++) 
		{
			while(hole[i]-- > 0) 
			{
				arr[index++] = i + min;
			}
		}
	}

}
