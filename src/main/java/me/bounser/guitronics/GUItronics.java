package me.bounser.guitronics;

import me.bounser.guitronics.advancedgui.AGUIExtension;
import me.bounser.guitronics.circuits.CircuitsManager;
import me.bounser.guitronics.listeners.BlockListener;
import me.bounser.guitronics.listeners.RedstoneListener;
import me.bounser.guitronics.tools.Data;
import me.bounser.guitronics.tools.DebugCommand;
import me.leoko.advancedgui.manager.LayoutManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class GUItronics extends JavaPlugin {

    /** Notes:
     *
     *  Directions are determined by integers starting with 0 and going clockwise.
     *  (North = 0, East = 1...) -1 means no direction.
     *
     *  Inputs/Outputs follow the same structure.
     *
     *
     *
     */

    private static GUItronics main;
    public static GUItronics getInstance(){ return main; }

    public BlockListener bl = new BlockListener();

    @Override
    public void onEnable() {
        main = this;
        boolean debug = Data.getInstance().getDebug();

        if(debug) getLogger().info("Loading resources...");

        if(debug) getCommand("debug").setExecutor(new DebugCommand(this));

        LayoutManager.getInstance().registerLayoutExtension(new AGUIExtension(), this);


        Bukkit.getPluginManager().registerEvents(bl, this);
        Bukkit.getPluginManager().registerEvents(new RedstoneListener(), this);

        if(debug) getLogger().info("Resources loaded.");

        if(debug && CircuitsManager.getInstance().loadCircuits()) getLogger().info("Circuits loaded.");

    }

}
