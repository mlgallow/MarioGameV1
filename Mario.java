import java.util.Iterator;

class Mario {
    //Where mario was
    int prev_x;
    int prev_y;
    int x;
    int y;
    int w =60;
    int h = 95;
    double vert_vel;
    Model model;
    int frame_count;

    Mario(Model m)
    {
        model = m;
        frame_count = 0;
    }

    void prev_location()
    {
        prev_x = x;
        prev_y = y;
    }

    //Collision detection
    boolean doesCollide(int collisionx, int collisiony, int collisionw, int collisionh)
    {
        if(x + w <= collisionx)
            return false;
        if(x >= collisionx + collisionw)
            return false;
        if(y + h <= collisiony)
            return false;
        if(y >= collisiony + collisionh)
            return false;
        else
        {
/*            if(y < 300)
            {
               System.out.println("break here");
            }*/
            return true;
        }

    }

    void get_out(Brick b)
    {
        if(x + w > b.x && prev_x + w <= b.x) // If mario enters from the left
        {
                x = b.x - w;
        }
        else if(x < b.x + b.w && prev_x >= b.x + b.w) // If mario enters form the right
        {
                x = b.x + b.w;
        }
        if(y + h > b.y && prev_y + h <= b.y) // If mario enters form the top
        {
                y = b.y - h;
                vert_vel = 0;
                frame_count = 0;
        }
        else if(y < b.y + b.h && prev_y >= b.y + b.h) // If mario enters form the bottom
        {
                y = b.y + b.h;
                vert_vel = 0;
        }
    }

    void update() {
        // Locks the scrolling to mario's x position
        model.scrollPos = x- 200;
        // Setting mario's velocity to a specific number
        vert_vel += 3.14159;
        y += vert_vel;

        // Stop when mario hits the ground
        if (y > 515)
        {
            y = 515;
            vert_vel = 0;
            frame_count = 0;
        }
        //Am I colliding
       Iterator<Brick> it =model.bricks.iterator();
        while(it.hasNext())
         {
            Brick b = it.next();
            if(doesCollide(b.x,b.y,b.w,b.h))
            {
                get_out(b);
            }
         }
        frame_count++;
    }
}