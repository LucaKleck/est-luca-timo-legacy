package buildings;

import java.awt.Point;
import java.util.Random;

import enemyBuildings.Portal;
import framePackage.DrawMapTile;
import framePackage.MainJFrame;
import info.MapTileType;
import info.ResourceType;
import mapTiles.MapTile;
import mapTiles.MapTileRoad;

public class TownHall extends Building {
	private static String buildingName = "Town Hall";
	private static ResourceType[] buildableOn = {ResourceType.Money,ResourceType.Food,ResourceType.Wood,ResourceType.Stone,ResourceType.Metal,ResourceType.ManaStones};
	private MainJFrame mainJFrame;

	public TownHall(MainJFrame mainJFrame,MapTile townHall) { 
		super(buildingName, buildableOn, townHall);
		MainJFrame.getLogger().info("start town hall");
		this.mainJFrame = mainJFrame;
		MapTile[][] map = mainJFrame.getObjectMap().getMap();
		if( townHall.getXPos() >= (map.length/100.0*50) && townHall.getYPos() <= (map[0].length/100.0*50) ) { // up right
			MainJFrame.getLogger().finest("up right\nx>=: "+(map.length/100.0*50)+" y<=:"+(map[0].length/100.0*50));
			//now start down left
			createPathToTownHall(map,townHall,new Point(0,map[0].length-1));
		} else 
		if(townHall.getXPos() < (map.length/100.0*50) && townHall.getYPos() < (map[0].length/100.0*50)) { // up left
			MainJFrame.getLogger().finest("up left\nx<: "+(map.length/100.0*50)+" y<:"+(map[0].length/100.0*50));
			// now start down right
			createPathToTownHall(map,townHall,new Point(map.length-1,map[0].length-1));
		} else
		if(townHall.getXPos() > (map.length/100.0*50) && townHall.getYPos() > (map[0].length/100.0*50)) { // down right
			MainJFrame.getLogger().finest("down right\nx>: "+(map.length/100.0*50)+" y<:"+(map[0].length/100.0*50));
			// now start up left
			createPathToTownHall(map,townHall,new Point(0,0));
		} else
		if(townHall.getXPos() < (map.length/100.0*50) && townHall.getYPos() > (map[0].length/100.0*50)) { // down left
			MainJFrame.getLogger().finest("down left\nx<: "+(map.length/100.0*50)+" y>:"+(map[0].length/100.0*50));
			// now start up right
			createPathToTownHall(map,townHall,new Point(map.length-1,0));
		}
		mainJFrame.redoDrawMapTile();
		MainJFrame.getLogger().info("end town hall");
	}
	
	private void createPathToTownHall(MapTile[][] map, MapTile townHall, Point referencePoint) {
		Random r = new Random();
		int xPath = (int)referencePoint.getX();
		int yPath = (int)referencePoint.getY();
		boolean leftOrRight = r.nextBoolean();
		if(leftOrRight) {
			try {
				int xStart =xPath+r.nextInt(10);
				map[xStart][yPath].getWidth();//just do anything, if this doesn't work, its out of bounds, so it wont run
				xPath = xStart;
			} catch(IndexOutOfBoundsException e) {
				xPath-=r.nextInt(10);
			}
		} else {
			try {
				int yStart = yPath+r.nextInt(10);
				map[xPath][yStart].getWidth(); // again just with y
				yPath = yStart;
			} catch(IndexOutOfBoundsException e) {
				yPath-=r.nextInt(10);
			}
		}
		changeToPath(map,xPath,yPath);
		createPortalOnPath(map,xPath,yPath);
		while(isNotConnected(map, townHall, xPath, yPath)) {
//			int xRepeatCount = 0;
//			int yRepeatCount = 0; IDEA implement this (for more varied paths)
			boolean goX= r.nextBoolean();
			if(goX) {
				xPath = moveX(townHall, xPath);
				changeToPath(map,xPath,yPath);
			} else {
				yPath = moveY(townHall, yPath);
				changeToPath(map,xPath,yPath);
			}
		}
	}
	private void changeToPath(MapTile[][] map, int xPath, int yPath) {
		try {
			if(map[xPath][yPath].getMapTileType().getType() == 20) {
				map[xPath][yPath] = new MapTileRoad(MapTileType.Bridge, xPath, yPath,true);
				mainJFrame.getDrawMapTileArray()[xPath][yPath] = new DrawMapTile(mainJFrame.getObjectMap(), xPath, yPath, mainJFrame, mainJFrame.getDrawMap());
			} else {
				map[xPath][yPath] = new MapTileRoad(MapTileType.DirtRoad, xPath, yPath,true);
				mainJFrame.getDrawMapTileArray()[xPath][yPath] = new DrawMapTile(mainJFrame.getObjectMap(), xPath, yPath, mainJFrame, mainJFrame.getDrawMap());
			}
		} catch(Exception e) {
		}
	}
	private int moveY(MapTile townHall, int yPath) {
		if(townHall.getYPos() > yPath) {
			yPath++;
		}
		if(townHall.getYPos() < yPath) {
			yPath--;
		}
		return yPath;
	}
	private int moveX(MapTile townHall, int xPath) {
		if(townHall.getXPos() > xPath) {
			xPath++;
		}
		if(townHall.getXPos() < xPath) {
			xPath--;
		}
		return xPath;
	}
	private boolean isNotConnected(MapTile[][] map ,MapTile townHall,int xPath, int yPath) {
		boolean isNotConnected = true;
		try {
			if(map[xPath-1][yPath] == townHall || map[xPath+1][yPath] == townHall || map[xPath][yPath+1] == townHall || map[xPath][yPath-1] == townHall ) {
				isNotConnected = false;
			}
//			if(map[xPath-1][yPath] != townHall && map[xPath+1][yPath] != townHall  && map[xPath][yPath+1] != townHall && map[xPath][yPath-1] != townHall ) {
//			} else {
//				isNotConnected = false;
//			}			
		} catch (IndexOutOfBoundsException e) {

		}
		return isNotConnected;
	}
	private void createPortalOnPath(MapTile[][] map,int xPath, int yPath) {
		map[xPath][yPath].setBuilding(new Portal(map[xPath][yPath],map));
	}
	
}
