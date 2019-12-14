package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.BaseScreen;

import javax.sound.midi.Soundbank;

public class MenuScreen extends BaseScreen {

    private static final float V_LEN = 2.5f;
    private Texture img;
    private Vector2 pos;
    private Vector2 v;
    private Vector2 point;
    private Vector2 buff;

    @Override
    public void show() {
        super.show();
        img = new Texture("Background.jpg");
        pos = new Vector2();
        v = new Vector2();
        point = new Vector2();
        buff = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);//цвет фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();// метод передачи текстур
        batch.draw(img, pos.x,pos.y);
        //batch.setColor(1f, 1f, 1f, 1);//метод отрисовки картинки
        //batch.draw(img, 280, 200, 250, 250);// метод для отрисовки
        batch.end(); // закрытие передачи текстур
        buff.set(point);
        if(buff.sub(pos).len() > V_LEN){
            pos.add(v);
        } else {
            pos.set(point);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        //System.out.println(screenX + "; " + (Gdx.graphics.getHeight() - screenY));
        point.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(point.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }
}
