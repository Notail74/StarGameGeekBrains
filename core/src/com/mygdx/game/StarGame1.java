package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class StarGame1 extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	//private TextureRegion region;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Background.jpg");
		//region = new TextureRegion(img, 20, 30, 50, 70);
		// создание веторов в методе create
		Vector2 v1 = new Vector2(3f , 5f );
		Vector2 v2 = new Vector2(6f , 8f );
		// метод для сложения векторов add
		v1.add(v2);
		System.out.println(" v1 + v2 = " + v1);
		//метод cpy копирование значения вектора + добавить вектор в2 не вызывать в методе render!
		Vector2 v3 = v1.cpy().add(v2);
		System.out.println(v3);
		//метод для того чтобы поменять значения векторов set
		v1.set(5, 4 );
		v2.set(2 , 2);
		//метод вычитания векторов sub , нужен для поиска расстояния
		v1.sub(v2);
		System.out.println(" v1 - v2 = " + v1);
		//длина вектора метод, есть len1 и len2
		System.out.println(" Длина v1 " + v1.len());
		// расчитать скаляр метод scl
		v1.set(100, 10 );
		v1.scl(0.9f);
		System.out.println("Скаляр " + v1.len());
		//метод для расчёта только направление вектора без учёта его длинны
		v1.set(100, 10 );
		v1.nor();
		System.out.println("Направление v1 без учёта длинны " + v1.len());
		//метод для расчета углов между 2 векторами
		v1.set(1, 1);
		v2.set(-1, 1);
		v1.nor();
		v2.nor();
		//сам метод Math.acos(v1.dot(v2))
		System.out.println("Угол межжду v1 и v2 " + Math.acos(v1.dot(v2)));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);//цвет фона
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();// метод передачи текстур
		batch.setColor(1f, 1f, 1f, 1);//метод отрисовки картинки
		batch.draw(img, 280, 200, 250, 250);// метод для отрисовки
		//batch.draw(region, 400, 400);
		//batch.setColor(1f, 1f, 1f, 0.2f);
		//batch.draw(img, 100, 50);
		batch.end(); // закрытие передачи текстур
	}
	
	@Override//когда игра закрылась выгрузить из памяти тяжелые обьекты
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
