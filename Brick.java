class Brick
{
 	int x;
	int y;
	int w;
	int h;
	Brick(int brick_x,int brick_y,int brick_w,int brick_h)
	{
		x = brick_x;
		y = brick_y;		
		w = brick_w;
		h = brick_h;
	} 
	
	Brick(Json ob)
	{
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
	}

	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("Type", "Brick");
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
         	return ob;
	}
}
