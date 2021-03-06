package interius.entities.onfield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class PersonNPC extends Person {
    
    public PersonNPC(float x, float y) {
        pos = new Vector2(x, y);
        this.create();
    }
    
    @Override
    public void create() {
        Texture texture = new Texture(Gdx.files.internal("sprites/blacksquare.png"));
        sprite = new Sprite(texture, 32, 32);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setPosition(pos.x, pos.y);
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        
    }
}
