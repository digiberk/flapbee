package com.berkd.flapbee;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


/** FLAPBEE
 * A game inspired by flappy bird
 */



public class Flapbee extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	Texture[] birds;
	int flapState = 0;
	float birdY = 0; // y - coordinate position
	float velocity = 0;
	float gravity = 1;

	int gameState = 0; // keep track of the game state



	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png"); // the background initialisation

        // Create an array for varying graphics
        birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight() /2 - (birds[0].getHeight() / 2); // centered bird Y
	}

	@Override
	public void render () {



		// User taps on the screen, change the game state to "1" and send a log
		if (Gdx.input.justTouched()) {
			Gdx.app.log("Touched", "Yep!");
			gameState = 1;
		}


		// When the user taps on the screen, proceed to run game!
		/** GAMESTATES

		 * gamestate = 0
		 	* home, default screen

		 * gamestate = 1
		 	* sprite moves, the game is now running

		 * gamestate = 2
			 * crashed or hit something
			 * display final score and high score
			 * give option to go to "home state 0" or "back to state 1"

		 */
		if (gameState != 0) {

			// BIRD MOVES UP
			if (Gdx.input.justTouched()) {
				velocity = -30;
			}

			// If bird falls to y = 0 OR if we give it a tap
			if ((birdY > 0) || (velocity < 0)) {
				velocity = velocity + gravity;
				birdY -= velocity;
			}



		} else {
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		}

		// Alternate between different flaps
		if (flapState == 0) {
			flapState = 1;
		} else {
			flapState = 0;
		}

		batch.begin();
		batch.draw(background, 0, 0,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch.draw(birds[flapState],Gdx.graphics.getWidth()/2 - (birds[flapState].getWidth() /2),
				birdY); // subtraction is for centering it
		batch.end();

	}
}
