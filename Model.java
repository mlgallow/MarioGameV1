import java.util.*;


class Model
{
	int scrollPos;
	ArrayList<Brick> bricks;
	Mario mario;
	
	Model()
	{
		bricks = new ArrayList<Brick>();
		mario = new Mario(this);
	}

	void prev_location()
	{
		mario.prev_location();
	}

	public void update() {
		mario.update();
	}

	void addBrick(int x, int y, int w, int h)
	{
		// Array of bricks and adding new bricks to the array
		Brick b = new Brick(x, y, w, h);
		bricks.add(b);
	}
	
	void unmarshall(Json ob)
	{
		bricks.clear();
		Json json_bricks = ob.get("bricks");
		for(int i = 0; i < json_bricks.size(); i++)
		{
			Json j = json_bricks.get(i);
			Brick b = new Brick(j);
			bricks.add(b);
		}
	}

	Json marshall()
	{
		Json ob = Json.newObject();
		Json json_bricks = Json.newList();
		ob.add("bricks", json_bricks);
		for(int i = 0; i< bricks.size(); i++)
		{
			Brick b = bricks.get(i);
			Json j = b.marshall();
			json_bricks.add(j);
		}
         	return ob;
	}
	void save(String filename)
	{
		//Allows us to save to a file
		Json ob = marshall();
		ob.save(filename);
	}
	
	void load(String filename)
	{
		//Allows us to load our saved file
		Json ob = Json.load(filename);
		unmarshall(ob);
		
	}

}

