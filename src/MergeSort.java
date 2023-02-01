

public class MergeSort {
	
	public static void merge(int arr[], int left, int middle, int right) 
	{
		int n1 = middle - left + 1;
		int n2 = right - middle;
		
		int leftArr[] = new int[n1];
		int rightArr[] = new int[n2];
		
		for(int i = 0; i < n1; ++i) 
		{
			leftArr[i] = arr[left + i];
		}
		
		for(int i = 0; i < n2; ++i) 
		{
			rightArr[i] = arr[middle + 1 + i];
		}
		
		int i = 0, j = 0;
		
		int k = left;
		
		while(i < n1 && j < n2) 
		{
			if(leftArr[i] <= rightArr[j]) 
			{
				arr[k] = leftArr[i];
				i++;
			}
			
			else 
			{
				arr[k] = rightArr[j];
				j++;
			}
			
			k++;
		}
		
		while(i < n1) 
		{
			arr[k] = leftArr[i];
			i++;
			k++;
		}
		
		while(j < n2) 
		{
			arr[k] = rightArr[j];
			j++;
			k++;
		}
	}
	
	public static void sort(int arr[], int left, int right) 
	{
		if (left < right) 
		{
			int middle = left + (right - left) / 2;
			
			sort(arr, left, middle);
			sort(arr, middle + 1, right);
			
			merge(arr, left, middle, right);
		}
	}
	

}
