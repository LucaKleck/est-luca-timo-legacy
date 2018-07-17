package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import buildings.Building;
import buildings.Lumbercamp;
import info.Item;
import info.ResourcesController;
import info.SingleResourceType;
import mapTiles.MapTile;
import staticPackage.ObjectMap;

public class DrawMapTile extends JPanel {
	private static final long serialVersionUID = -8785925966340775096L;
	private MapTile[][] map;
	private ObjectMap objectMap;
	private ArrayList<ActionListener> listener = null;
	private MapTile mapTile;
	private MainJFrame mainJFrame;
	private DrawMap drawMap;
	private Color color = Color.BLACK;
	private boolean selected = false;
	private boolean hover = false;
	private ActionListener drawMapTileActionListener;
	// Constructor
	public DrawMapTile(ObjectMap objectMap,int xOfTile, int yOfTile, MainJFrame mainJFrame, DrawMap drawMap) {
		this.mainJFrame = mainJFrame;
		this.objectMap = objectMap;
		this.map = objectMap.getMap();
		this.drawMap = drawMap;
		this.mapTile = map[xOfTile][yOfTile];
		this.color = mapTile.getMapTileType().getColor();
		listener = new ArrayList<ActionListener>();
        drawMapTileActionListener = new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
        		if(!evt.getActionCommand().isEmpty() && evt.getActionCommand()!="select") {
        			boolean isBuilding = false;
            		Item item = new Item(removeTypeFromString(evt.getActionCommand()));
        			if(evt.getActionCommand().contains(",Building")) {
        				isBuilding = true;
        			}
        			buyItem(item, isBuilding, mapTile, mainJFrame.getResources());
        			mainJFrame.getBuyMenu().deselect();
        			toggleSelected();
        			drawMap.repaintMapTile(xOfTile, yOfTile);
        		}
        		if(evt.getActionCommand() == "select") {
					try {	
						mainJFrame.getInfoTextPane().setText(""+mapTile);
						toggleSelected();
						removeSelectedFromAllTiles(mainJFrame, (int)mapTile.getXPos(), (int)mapTile.getYPos());
						drawMap.repaintMapTile(xOfTile, yOfTile);
					} catch (Exception e) {
						System.out.println(e);
					}
        		}
        		if(evt.getActionCommand() == "toggleHover") {
        			try {
        				toggleHover();
        				drawMap.repaintMapTile(xOfTile, yOfTile);
        			} catch (Exception e) {
        				System.out.println(e);
        			}
        		}
			}
		};
		this.addActionListener(getDrawMapTileActionListener());
	}
	public void drawMapTile(Graphics g) {
		mapTile.setHeight((double)drawMap.getHeight()/objectMap.getHeight());
		mapTile.setWidth((double)drawMap.getWidth()/objectMap.getWidth());
		g.setColor(color);
		g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		g.setColor(new Color(0,0,0,40));
		g.drawRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		if(selected) {
			g.setColor(new Color(255,0,0,100));
			g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		}
		if(hover) {
			g.setColor(new Color(120,100,0,100));
			g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()), (int)(mapTile.getHeight()*mapTile.getYPos()), (int)mapTile.getWidth()+1, (int)mapTile.getHeight()+1);
		}
		if(mapTile.getBuilding() != null) {
			g.setColor(mapTile.getBuilding().getColor());
			g.fillRect((int)(mapTile.getWidth()*mapTile.getXPos()+mapTile.getWidth()/100*10), (int)(mapTile.getHeight()*mapTile.getYPos()+mapTile.getHeight()/100*10), (int)(mapTile.getWidth()-mapTile.getWidth()/100*40), (int)(mapTile.getHeight()-mapTile.getHeight()/100*40));
		}
	}
	public void buyItem(Item item, boolean isBuilding, MapTile mapTile, ResourcesController resources) {
		if(isBuilding) {
			switch(item.getItemName()) {
				case "ItemOne":
					if(mapTile.getBuilding() == null) {
						System.out.println("should only be null:"+mapTile.getBuilding());
						boolean hasResources = false;
						SingleResourceType[] cost = item.getCosts();
						hasResources = item.hasResources(resources, mapTile);
						if(hasResources) {
							Building lumbercamp = new Lumbercamp();
							for(int subtrackt = 0; subtrackt < resources.getResources().length; subtrackt++) {
								resources.getResources()[subtrackt].removeResourceAmount(cost[subtrackt].getResourceAmount());
							}
							mapTile.setBuilding(lumbercamp);
							mainJFrame.getLogTextPane().writeToLog("bought: " + item);
							mainJFrame.getInfoTextPane().setText(mapTile.getBuilding()+"\n"+resources);
						} else {
							mainJFrame.getLogTextPane().writeToLog("No Money/Invalid Field");		
							mainJFrame.getInfoTextPane().setText("No Money/Invalid Field"); return;
						}
					} else {
						mainJFrame.getLogTextPane().writeToLog("No Money/Building blocked");		
						mainJFrame.getInfoTextPane().setText("No Money/Building blocked"); return;
					}
					break;
				default: mainJFrame.getLogTextPane().writeToLog("No Money/Building blocked + DEFAULTCASE!!! (not good)");		
						 mainJFrame.getInfoTextPane().setText("No Money/Building blocked"); return;
			}
		}
	}
	public void removeSelectedFromAllTiles(MainJFrame mainJFrame, int xOfTile, int yOfTile) {
		for( int y = 0; y < map[0].length; y++) {
			for( int x = 0; x < map.length; x++) {
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				if( x == xOfTile && y == yOfTile) {
					drawMap.repaintMapTile(xOfTile, yOfTile);
				} else {
					if(drawMapTileArray[x][y].selected == true) {
						drawMapTileArray[x][y].turnOffSelected();
						drawMap.repaintMapTile(x, y);
					}
				}
			}
		}
	}
	private String removeTypeFromString(String fullString) {
		String result = "";
		for(int search = 3; search < fullString.length(); search++) {
			if(fullString.charAt(search) == ',') {
				return result;
			}
			result += fullString.charAt(search);
		}
		return result;
	}
	public void removeSelectedFromAllTiles(MainJFrame mainJFrame) {
		for( int y = 0; y < map[0].length; y++) {
			for( int x = 0; x < map.length; x++) {
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				if(drawMapTileArray[x][y].selected == true) {
					drawMapTileArray[x][y].turnOffSelected();
					drawMap.repaintMapTile(x, y);
				}
			}
		}
	}
	protected void toggleHover() {
		hover = !hover;
	}
	protected void toggleSelected() {
		selected = !selected;
	}
	public void turnOffSelected() {
		selected = false;
	}
	public MapTile getMapTile() {
		return mapTile;
	}
	public boolean getSelected() {
		return selected;
	}
	public ActionListener getDrawMapTileActionListener() {
		return drawMapTileActionListener;
	}
	protected void fireUpdate(ActionEvent evt) {   
        for (ActionListener al : listener) {
            al.actionPerformed(evt);
        }
    }
    public void addActionListener(ActionListener al) {
        listener.add(al);
    }
    public void removeActionListener(ActionListener al) {
        listener.remove(al);
    }
	public ArrayList<ActionListener> getListener() {
		return listener;
	}
}