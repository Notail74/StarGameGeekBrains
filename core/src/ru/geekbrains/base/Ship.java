package ru.geekbrains.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.Explosion;

public abstract class Ship extends Sprite {

    protected final Vector2 v0 = new Vector2();
    protected final Vector2 v = new Vector2();

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV = new Vector2();
    protected Sound sound;
    protected float bulletHeight;
    protected int damage;

    protected int hp;

    protected float reloadInterval = 0f;
    protected float reloadTimer = 0f;

    protected float animateInterval = 0.05f;
    protected float animateTimer = animateInterval;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        reloadTimer += delta;
        if (reloadTimer > reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        animateTimer += delta;
        if (animateTimer > animateInterval) {
            frame = 0;
        }
        pos.mulAdd(v, delta);
    }

    @Override
    public void destroy() {
        boom();
        super.destroy();
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
        animateTimer = 0f;
        frame = 1;
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    protected void shoot() {
        sound.play(0.3f);
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
    }

    protected void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(pos, getHeight());
    }
}
