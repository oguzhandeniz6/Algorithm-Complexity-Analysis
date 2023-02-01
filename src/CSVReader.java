

import java.io. * ;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {
	
	public ArrayList<String> reader(String fileName)
	{
		ArrayList<String> returnList = new ArrayList<>();
		
		Path filePath = Paths.get(fileName);
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filePath.toString()));
			String line;
			
			try {
				while ((line = br.readLine()) != null) 
				{
					String[] cols = line.split(",");
					returnList.add(cols[7]);
				}
			} 
			catch (IOException e) {e.printStackTrace();}

			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return returnList;
	}

}
