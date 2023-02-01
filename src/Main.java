

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Main {
    public static void main(String args[]) throws IOException {

        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};

        // Create sample data for linear runtime
        double[][] yAxis = new double[4][10];
        
        CSVReader reader = new CSVReader();
        
        ArrayList<String> data = reader.reader("TrafficFlowDataset.csv");
        int[] intData = new int[251281];
        
        for(int x = 0; x < 251281; x++) 
        {
        	intData[x] = Integer.parseInt(data.get(x + 1));
        }
        
        MergeSort.sort(intData, 0, intData.length - 1);
        intData = reverseArray(intData);

		System.out.println("Insertion Sort:");
        for(int t = 0; t < inputAxis.length; t++) 
        {
        	double time = avgTime(data, intData, "insertion", inputAxis[t]);
        	yAxis[0][t] = time;
        }

    	System.out.println("MergeSort:");
        for(int t = 0; t < inputAxis.length; t++) 
        {
        	double time = avgTime(data, intData, "merge", inputAxis[t]);
        	yAxis[1][t] = time;
        }

    	System.out.println("Pigeonhole Sort:");
        for(int t = 0; t < inputAxis.length; t++) 
        {
        	double time = avgTime(data, intData, "pigeonhole", inputAxis[t]);
        	yAxis[2][t] = time;
        }

    	System.out.println("Counting Sort:");
        for(int t = 0; t < inputAxis.length; t++) 
        {
        	double time = avgTime(data, intData, "counting", inputAxis[t]);
        	yAxis[3][t] = time;
        }
        

        
          

        // Save the char as .png and show it
        showAndSaveChart("Tests on Reversely Sorted Data", inputAxis, yAxis);
    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
        chart.addSeries("Merge Sort", doubleX, yAxis[1]);
        chart.addSeries("Pigeonhole Sort", doubleX, yAxis[2]);
        chart.addSeries("Counting Sort", doubleX, yAxis[3]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
    
	public static double avgTime(ArrayList<String> data, int[] intArray, String sortType, int sortSize) 
	{
		
		if(sortType.equals("insertion")) 
		{			
			double avgTime = 0;
			
			System.out.print("Sort Size is: " + sortSize);
			
			for(int i = 0; i < 10; i++) 
			{
//				int[] array = stringToInt(data, sortSize);
				int[] array = getArray(intArray, sortSize);
				
				double start = System.nanoTime();
				InsertionSort.insertionSort(array);
				double end = System.nanoTime();
				
				avgTime += (end - start);
			}
			System.out.println(" Average time is: " + avgTime / 10 + " ns");
			return avgTime / 10.0;
		}
		
		else if(sortType.equals("merge")) 
		{					
			double avgTime = 0;
			
			System.out.print("Sort Size is: " + sortSize);
			
			for(int i = 0; i < 10; i++) 
			{
//				int[] array = stringToInt(data, sortSize);
				int[] array = getArray(intArray, sortSize);
		
				double start = System.nanoTime();
				MergeSort.sort(array, 0, array.length - 1);
				double end = System.nanoTime();
				
				avgTime += (end - start);
						
			}
			System.out.println(" Average time is: " + avgTime / 10 + " ns");
			return avgTime / 10.0;
		}
		
		else if(sortType.equals("pigeonhole")) 
		{	
			double avgTime = 0;
			
			System.out.print("Sort Size is: " + sortSize);
			
			for(int i = 0; i < 10; i++) 
			{
//				int[] array = stringToInt(data, sortSize);
				int[] array = getArray(intArray, sortSize);
				
				
				double start = System.nanoTime();
				PigeonholeSort.pigeonholeSort(array);
				double end = System.nanoTime();
				
				avgTime += (end - start);
			}
			System.out.println(" Average time is: " + avgTime / 10 + " ns");
			return avgTime / 10.0;
		}
		
		else if(sortType.equals("counting")) 
		{	
			double avgTime = 0;
			
			System.out.print("Sort Size is: " + sortSize);
			
			for(int i = 0; i < 10; i++) 
			{
//				int[] array = stringToInt(data, sortSize);
				int[] array = getArray(intArray, sortSize);
				
				double start = System.nanoTime();
				CountingSort.countingSort(array);
				double end = System.nanoTime();
				
				avgTime += (end - start);
			}
			System.out.println(" Average time is: " + avgTime / 10 + " ns");
			return avgTime / 10.0;
		}
		
		else {return -1;}
	}
	
	public static int[] stringToInt(ArrayList<String> data, int size) 
	{
		int[] returnArray = new int[size];
        
        for(int i = 0; i < size; i++) 
        {
        	returnArray[i] = Integer.parseInt(data.get(i + 1));
        }
        
        return returnArray;
	}
	
	public static int[] getArray(int[] array, int size) 
	{
		int[] returnArray = new int[size];
		
		for(int i = 0; i < size; i++) 
		{
			returnArray[i] = array[i];
		}
		
		return returnArray;
	}
	
	public static int[] reverseArray(int a[])
    {
        int[] b = new int[a.length];
        int j = a.length;
        for (int i = 0; i < a.length; i++) 
        {
            b[j - 1] = a[i];
            j = j - 1;
        }
        
        return b;
    }
}


