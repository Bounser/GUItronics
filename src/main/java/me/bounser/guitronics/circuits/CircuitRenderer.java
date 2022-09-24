package me.bounser.guitronics.circuits;

import me.bounser.guitronics.tools.Data;
import me.leoko.advancedgui.utils.components.ImageComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.material.Redstone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CircuitRenderer {

    // Utilities to render the proper circuit reactions depending on the corresponding design.

    private static CircuitRenderer  instance;
    public static CircuitRenderer  getInstance(){
        return instance == null ? instance = new CircuitRenderer() : instance;
    }

    public void VisualRender(Circuit cir){

        HashMap design = cir.getDesign();

        for(int input : cir.getInputs()){

            cir.addToRender(input);
            checkPixel(input, design, cir);

        }

    }

    public void checkPixel(int i, HashMap design, Circuit cir){

        for(int j : Arrays.asList(9, -9, 1, -1)){

            int x = j+i;

            if(!cir.getRender().contains(x)) {

                if (design.keySet().contains(x)) {

                    if (x == 5 || x == 37 || x == 45 || x == 77) {
                        cir.addOutput(x);
                    }

                    cir.addToRender(j + i);
                    checkPixel(j + i, design, cir);

                }
            }
        }
    }

    public void outputRedstone(List<Integer> outputs, Circuit cir){

        for(int output : outputs){

            Location base = cir.getLocation().add(0,-1,0);

            switch(output){
                case 5:  base.add(-1,0,0); break;
                case 37: base.add(0,0,1); break;
                case 45: base.add(0,0,-1); break;
                case 77: base.add(1,0,0); break;
            }

            if(Data.getInstance().getDebug()) Bukkit.broadcastMessage("Lighting: " + base);

            if(base.getBlock().getBlockPower() == 0){

                if(Data.getInstance().getDebug()) Bukkit.broadcastMessage("It can be lighted.");

                RedstoneWire rw = (RedstoneWire) base.getBlock().getBlockData();
                rw.setPower(15);
                base.getBlock().setBlockData(rw);
            }

        }
    }

}
