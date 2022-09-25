package me.bounser.guitronics.circuits;

import me.bounser.guitronics.advancedgui.AGUIInstances;
import me.bounser.guitronics.listeners.RedstoneListener;
import me.bounser.guitronics.tools.Data;
import me.leoko.advancedgui.utils.Direction;
import me.leoko.advancedgui.utils.GuiInstance;
import me.leoko.advancedgui.utils.Layout;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.awt.*;
import java.util.Map.Entry;
import java.util.HashMap;

public class CircuitsManager {

    // Keeps information about circuits, and it's the responsable of creating/deleting them.

    Data data = Data.getInstance();

    HashMap<Circuit, GuiInstance> circuits = new HashMap<>();
    Layout layout = Data.getInstance().getLayout();

    private static CircuitsManager instance;
    public static CircuitsManager getInstance(){
        return instance == null ? instance = new CircuitsManager() : instance;
    }

    public void createCircuit(HashMap loc, String owneruuid){

        circuits.put(new Circuit(loc, owneruuid, null, null, null), AGUIInstances.getInstance().placeGUI(loc, Direction.FLOOR_EAST, layout));
        Data.getInstance().registerCircuit(loc, owneruuid);

    }

    public Circuit getCircuitFromGUIInstance(GuiInstance guiinstance){
        for(Entry<Circuit, GuiInstance> e: circuits.entrySet()){
            if(e.getValue() == guiinstance) return e.getKey();
        }
        return null;
    }

    public Circuit getCircuitFromBaseLocation(Location circuitBase){
        for(Circuit circuit : circuits.keySet()){
            Location base = circuit.getLocation().add(0,-1,0);
            if(data.getDebug()) Bukkit.broadcastMessage("distance: " + base.distance(circuitBase) + base + circuitBase);
            if(base.distance(circuitBase) == 0) return circuit;
        }
        return null;
    }

    public boolean loadCircuits(){

        Data data = Data.getInstance();

        if(!(circuits.size() == 0)){
            for(String uuid : data.getUsersUUID()){

                HashMap Location loc = data.getLocation(uuid);
                circuits.put(new Circuit(loc, uuid, data.getDesign(uuid)),
                        AGUIInstances.getInstance().placeGUI(loc, Direction.FLOOR_EAST, layout));
                RedstoneListener.getInstance().addBase(loc.add(0,-1,0));

            }
            return true;
        }
        return false;
    }
}