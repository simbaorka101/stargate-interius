package interius.tiles;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import interius.entities.onfield.*;

public class Planet {
    private TileMapLoader map;
    
    private ArrayList<PersonCrew> crew;
    private ArrayList<PersonNPC> npcs;
    
    private OrthographicCamera camera;
    private SpriteBatch personsBatch;
    
    public Planet(String name) {
        map = new TileMapLoader(name);
        personsBatch = new SpriteBatch();
        
        crew = new ArrayList<PersonCrew>();
        npcs = new ArrayList<PersonNPC>();
        
        
        // @TODO - load crew which is on this planet and set their positions
        // also load npcs supposed to be on this planet
        npcs = map.getNPCs();
        crew.add(new CrewScientist(0, 20*16, this));
        crew.add(new CrewScientist(34, 20*16, this));
        crew.add(new CrewScientist(68, 20*16, this));
    }

    public void create(OrthographicCamera camera) {
        this.camera = camera;
        map.create(camera);
    }
    
    public void update() {
        for(Person p : npcs) p.update();
        for(Person p : crew) p.update();
    }
    
    public void renderTerrain() {
        map.render();
    }
    
    public void renderPeople() {
        personsBatch.setProjectionMatrix(camera.combined);
        personsBatch.begin();
        for(Person p : npcs) p.render(personsBatch);
        for(Person p : crew) p.render(personsBatch);
        personsBatch.end();
    }
    
    public ArrayList<PersonCrew> getCrew() {
        return crew;
    }
    
    public ArrayList<PersonNPC> getNPCs() {
        return npcs;
    }
    
    public boolean[][] getCollision() {
        return map.getCollideable();
    }
    
    public boolean isCollidable(int x, int y) {
        return map.isCollidable(x, y);
    }
    
    public int getWidth() {
        return map.getWidth();
    }
    
    public int getHeight() {
        return map.getHeight();
    }
    
    public ArrayList<PersonCrew> getSelectedUnits(Rectangle dragRegion) {
        ArrayList<PersonCrew> units = new ArrayList<PersonCrew>();
        
        // Rectangle.overlaps(Rectangle r) doesn't work with negative width/height
        // Creating new one to fix

        float minx = Math.min(dragRegion.x, dragRegion.x + dragRegion.width);
        float maxx = Math.max(dragRegion.x, dragRegion.x + dragRegion.width);
        float miny = Math.min(dragRegion.y, dragRegion.y + dragRegion.height);
        float maxy = Math.max(dragRegion.y, dragRegion.y + dragRegion.height);
        
        Rectangle region = new Rectangle(minx, miny, maxx - minx, maxy - miny);

        for(PersonCrew p : crew)
            if(region.overlaps(p.getBoundingBox())) {
                units.add(p);
                p.setSelected(true);
            }else{
                p.setSelected(false);
            }
        
        return units;
    }
    
    public void selectNPC(float x, float y) {
        for (PersonNPC p : npcs) {
            if (p.getBoundingBox().contains(x, y))
                System.out.println("Clicked npc");
        }
    }

    public void dispose()
    {
        map.dispose();
        npcs.clear();
        crew.clear();
    }


}
