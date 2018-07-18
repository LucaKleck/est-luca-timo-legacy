package buildings;

import java.awt.Point;
import java.util.Random;

import framePackage.MainJFrame;
import info.MapTileType;
import mapTiles.MapTile;
import mapTiles.MapTileRoad;

public class TownHall extends Building {
	private static String buildingName = "Town Hall";
	private static int[] buildableOn = {0,1,2,3,4,5};

	public TownHall(MainJFrame mainJFrame,MapTile townHall) { 
		super(buildingName, buildableOn, townHall);
		MapTile[][] map = mainJFrame.getObjectMap().getMap();
		if( townHall.getXPos() >= (map.length/100.0*50) && townHall.getYPos() <= (map[0].length/100.0*50) ) { // up right
			System.out.println("up right\nx>=: "+(map.length/100.0*50)+" y<=:"+(map[0].length/100.0*50));
			//now start down left
			createPathToTownHall(map,townHall,new Point(0,map[0].length));
		}
		if(townHall.getXPos() < (map.length/100.0*50) && townHall.getYPos() < (map[0].length/100.0*50)) { // up left
			System.out.println("up left\nx<: "+(map.length/100.0*50)+" y<:"+(map[0].length/100.0*50));
			// now start down right
			createPathToTownHall(map,townHall,new Point(map.length,map[0].length));
		}
		if(townHall.getXPos() > (map.length/100.0*50) && townHall.getYPos() > (map[0].length/100.0*50)) { // down right
			System.out.println("down right\nx>: "+(map.length/100.0*50)+" y<:"+(map[0].length/100.0*50));
			// now start up left
			createPathToTownHall(map,townHall,new Point(0,0));
		}
		if(townHall.getXPos() < (map.length/100.0*50) && townHall.getYPos() > (map[0].length/100.0*50)) { // down left
			System.out.println("down left\nx<: "+(map.length/100.0*50)+" y>:"+(map[0].length/100.0*50));
			// now start up right
			createPathToTownHall(map,townHall,new Point(map.length,0));
		}
		mainJFrame.getContentPane().remove(mainJFrame.getDrawMap());
		mainJFrame.redoDrawMapTile();
	}
	private void createPathToTownHall(MapTile[][] map, MapTile townHall, Point referencePoint) {
		Random r = new Random();
		int xPath = (int)referencePoint.getX();
		int yPath = (int)referencePoint.getY();
		try {
			int xStart =xPath+r.nextInt(10);
			map[xStart][yPath].getWidth();
			xPath = xStart;
		} catch(IndexOutOfBoundsException e) {
			xPath-=r.nextInt(10);
		}
		try {
			int yStart = yPath+r.nextInt(10);
			map[xPath][yStart].getWidth();
			yPath = yStart;
		} catch(IndexOutOfBoundsException e) {
			yPath-=r.nextInt(10);
		}
		while(isNotConnected(map, townHall, xPath, yPath)) {
//			int xRepeatCount = 0;
//			int yRepeatCount = 0;
			boolean goX= r.nextBoolean();
			if(goX) {
				// Move x method
				xPath = moveX(townHall, xPath);
				changeToPath(map,xPath,yPath);
//				if(townHall.getXPos() == xPath) {
//					for(int moveYToTownHall=0; moveYToTownHall < map[0].length; moveYToTownHall++) {
//						yPath = moveY(townHall, yPath);
//						
//						changeToPath(map,xPath,yPath);
//						//create path method
//						if((yPath+1) == townHall.getYPos() || (yPath-1) == townHall.getYPos()) {
//							continue;
//						}
//					}
//				}
			} else {
				yPath = moveY(townHall, yPath);
				changeToPath(map,xPath,yPath);
//				if(townHall.getYPos() == yPath) {
//					for(int moveXToTownHall=0; moveXToTownHall < map[0].length; moveXToTownHall++) {
//						if(isNotConnected(townHall, xPath, yPath)) { //create path method
//							xPath = moveX(townHall, xPath);
//							if((xPath+1) == townHall.getXPos() || (xPath-1) == townHall.getXPos()) {
//								continue;
//							}
//							changeToPath(map,xPath,yPath);
//						}
//					}
//				}
			}
		}
		System.out.println("yStart: "+yPath+"  xStart: "+xPath);
	}
	private void changeToPath(MapTile[][] map, int xPath, int yPath) {
		try {
			if(map[xPath][yPath].getMapTileType().getType() == 20) {
				map[xPath][yPath] = new MapTileRoad(MapTileType.Bridge, xPath, yPath,true);
			} else {
				map[xPath][yPath] = new MapTileRoad(MapTileType.DirtRoad, xPath, yPath,true);				
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
	@SuppressWarnings("unused")
	private boolean isInBounds() {
		boolean isInBounds = true;
		return isInBounds;
	}
	private boolean isNotConnected(MapTile[][] map ,MapTile townHall,int xPath, int yPath) {
		boolean isNotConnected = true;
//		if(townHall.getXPos()==(xPath+1) || townHall.getXPos()==(xPath-1) && townHall.getYPos()==(yPath+1) || townHall.getYPos()==(yPath-1)) {
		try {
			if(map[xPath-1][yPath] != townHall && map[xPath+1][yPath] != townHall  && map[xPath][yPath+1] != townHall && map[xPath][yPath-1] != townHall ) {
			} else {
				isNotConnected = false;
			}			
		} catch (Exception e) {
			System.out.println("isNotConnected "+e);
		}
		return isNotConnected;
	}
}
