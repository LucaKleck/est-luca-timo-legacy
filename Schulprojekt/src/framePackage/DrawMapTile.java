package framePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import buildings.Building;
import buildings.Lumbercamp;
import buildings.TownHall;
import info.Item;
import info.ObjectMap;
import info.ResourcesController;
import info.SingleResourceType;
import mapTiles.MapTile;
import mapTiles.MapTileWithResources;

public class DrawMapTile extends JPanel implements MouseListener {
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
		this.addMouseListener(this);
        drawMapTileActionListener = new ActionListener() {
        	@Override // Event from DrawMap, contains command from the special happenings
        	public void actionPerformed(ActionEvent evt) {
        		if(!evt.getActionCommand().isEmpty() && evt.getActionCommand()!="select") {
        			boolean isBuilding = false;
            		Item item = new Item(stripBuyString(evt.getActionCommand())); // This strips the string off of building information and buy
        			if(evt.getActionCommand().contains(",Building")) {
        				isBuilding = true;
        			}
        			// buys item(containing price) on MapTile mapTile, costs the global Resources
        			buyItem(item, isBuilding, mapTile, mainJFrame.getResources());
        			if(evt.getActionCommand()=="buyTownHall,Building") mainJFrame.getTownsHallPanel().toggleSelected();mainJFrame.testForTownHall();
        			mainJFrame.getBuyMenu().deselect();
        			toggleSelected();
        			drawMap.repaintMapTile(xOfTile, yOfTile);
        		}
        		if(evt.getActionCommand() == "select") {
					try {	
						mainJFrame.getInfoTextPane().setText(""+mapTile);
						toggleSelected();
						removeSelectedFromAllTiles(mainJFrame,mapTile.getXPos(),mapTile.getYPos());
						drawMap.repaintMapTile(xOfTile, yOfTile);
					} catch (Exception e) {
						System.out.println("selected "+e);
					}
					if(mapTile.getBuilding() == null && mainJFrame.hasTownHall()) {
						mainJFrame.enableBuyMenuBuildings();
					}
					try {
						if(mapTile.getBuilding().getName()=="Town Hall"&&getSelected()==true) {
							mainJFrame.enableSelectedMenuTownHall();
						}
						if(mapTile.getBuilding().getName()=="Lumbercamp"&&getSelected()==true) {
							mainJFrame.enableSelectedMenuLumbercamp();
						}
						if(getSelected()==false && mainJFrame.hasTownHall()) {
							mainJFrame.enableBuyMenuBuildings();
						}
					} catch(NullPointerException e) {
						// No building
					}
        		}
        		if(evt.getActionCommand() == "toggleHover") {
        			try {
        				toggleHover();
        				drawMap.repaintMapTile(xOfTile, yOfTile);
        			} catch (Exception e) {
        				System.out.println("selected "+e);
        			}
        		}
			}
		};
		this.addActionListener(getDrawMapTileActionListener());
	}
	private String stripBuyString(String fullString) {
		String result = "";
		for(int search = 3; search < fullString.length(); search++) {
			if(fullString.charAt(search) == ',') {
				return result;
			}
			result += fullString.charAt(search);
		}
		return result;
	}
	public void buyItem(Item item, boolean isBuilding, MapTile mapTile, ResourcesController resources) {
		if(isBuilding) {
			Building building;
			boolean hasResources = false;
			switch(item.getItemName()) {
				case "Town Hall":
						if(mapTile.getBuilding() == null && mapTile.getMapTileType().getType() != 20) {
							building = new TownHall(mainJFrame, mapTile);
							subtracktResources(item, resources);
							mapTile.setBuilding(building);
							mainJFrame.getLogTextPane().writeToLog("bought: " + item);
							mainJFrame.getInfoTextPane().setText(mapTile.getBuilding()+"\n"+resources);
						} else {
							mainJFrame.getLogTextPane().writeToLog("Missing Resources/Building blocked");		
							mainJFrame.getInfoTextPane().setText("Missing Resources/Building blocked"); return;
						}
						break;
				case "Lumbercamp":
					if(mapTile.getBuilding() == null) {
						hasResources = item.hasResources(resources, (MapTileWithResources) mapTile);
						if(hasResources) {
							building = new Lumbercamp(mapTile);
							subtracktResources(item, resources);
							mapTile.setBuilding(building);
							mainJFrame.getLogTextPane().writeToLog("bought: " + item);
							mainJFrame.getInfoTextPane().setText(mapTile.getBuilding()+"\n"+resources);
						} else {
							mainJFrame.getLogTextPane().writeToLog("Missing Resources/Invalid Field");		
							mainJFrame.getInfoTextPane().setText("Missing Resources/Invalid Field"); return;
						}
					} else {
						mainJFrame.getLogTextPane().writeToLog("Missing Resources/Building blocked");		
						mainJFrame.getInfoTextPane().setText("Missing Resources/Building blocked"); return;
					}
					break;
				default: mainJFrame.getLogTextPane().writeToLog("Missing Resources/Building blocked + DEFAULTCASE!!! (not good)");		
						 mainJFrame.getInfoTextPane().setText("Missing Resources/Building blocked"); return;
			}
		}
	}
	private void subtracktResources(Item item, ResourcesController resources) {
		SingleResourceType[] cost = item.getCosts();
		for(int subtrackt = 0; subtrackt < resources.getResources().length; subtrackt++) {
			resources.getResources()[subtrackt].removeResourceAmount(cost[subtrackt].getResourceAmount());
		}
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
	public void removeSelectedFromAllTiles(MainJFrame mainJFrame, int xPos, int yPos) {
		for( int y = 0; y < map[0].length; y++) {
			for( int x = 0; x < map.length; x++) {
				DrawMapTile[][] drawMapTileArray = mainJFrame.getDrawMapTileArray();
				if(yPos == y && xPos == x) {
					drawMap.repaintMapTile(xPos, yPos);
				} else {
					if(drawMapTileArray[x][y].selected == true) {
					drawMapTileArray[x][y].turnOffSelected();
					drawMap.repaintMapTile(x, y);
					}
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
	public void drawMapTile(Graphics g) {
		mapTile.setHeight((double)drawMap.getHeight()/objectMap.getHeight());
		mapTile.setWidth((double)drawMap.getWidth()/objectMap.getWidth());
		try {
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
		} catch (NullPointerException e) {
			
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("into");
	}
	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("outof");
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("not the one I want but nice too");
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
}