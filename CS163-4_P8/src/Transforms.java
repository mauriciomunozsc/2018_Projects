
public class Transforms implements ImageInterface
{
	PictureLibrary pictureLib = null;
	int width = 0;
	int height = 0;
	int [][] data;
	
	public Transforms()
	{
		this.pictureLib = new PictureLibrary();
	}
	
	@Override
	public void readImage(String inFile) 
	{
		try 
		{
			pictureLib.readPGM(inFile);
			width = pictureLib.getWidth();
			height = pictureLib.getHeight();
			data = pictureLib.getData();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void writeImage(String outFile) 
	{
		try 
		{
			pictureLib.setData(data);
			pictureLib.writePGM(outFile);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public int[][] imageData()
	{
		return data;
	}

	@Override
	public void decode()
	{
		int upper = 0;
		int lower = 0;
		int newPixel = 0;
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				int current  = data[i][j];
				upper = 15 - (current/16);
				lower = current%16;
				newPixel = (upper*16)+lower;
				data[i][j] = newPixel; 
			}
		}
	}

	@Override
	public void swap() {
		int upper = 0;
		int middle = 0;
		int lower = 0;
		int newPixel = 0;
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				int current  = data[i][j];
				upper = current & 0b11000000;
				middle = current & 0b00111100;
				lower = current & 0b00000011;
				newPixel = (lower<<6) | middle | (upper>>6);
				data[i][j] = newPixel; 
			}
		}
	}

	@Override
	public void mirror() 
	{
		for(int i = 0; i < height/2; i++)
		{
			for(int j = 0; j < width; j++)
			{
				int current  = data[i][j];

				data[i][j] = data[height-1-i][j];
				data[height-1-i][j] = current;
			}
		}	
	}

	@Override
	public void exchange()
	{
		int recH = 310;
		int recW = 160;
		
		for(int i = 10; i < recH; i++)
		{
			for(int j = 10; j < recW; j++)
			{
				int current  = data[i][j];

				data[i][j] = data[i][300+j];
				data[i][300+j] = current;
			}
		}	
	}
	
}
