package net.kiberion.assets.readers;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.badlogic.gdx.Gdx;
import com.google.inject.Singleton;

import lombok.Getter;

@Singleton
public class ReaderHelper {

	private AbstractFileReader reader;

	@Getter
	private Path pathToAssets = Paths.get("src/main/resources/");

	public AbstractFileReader getReader() {
		if (reader == null) {
			if (Gdx.app != null) {
				reader = new GDXFileReader(pathToAssets);
			} else {
				reader = new SimpleFileReader(pathToAssets);
			}
		}
		return reader;
	}
	
	public void setPathToAssets (String path) {
		pathToAssets = Paths.get(path);
		reader = null;
	}

}