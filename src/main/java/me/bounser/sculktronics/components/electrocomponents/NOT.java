package me.bounser.sculktronics.components.electrocomponents;

import me.bounser.sculktronics.circuits.Circuit;
import me.bounser.sculktronics.components.EComponent;
import me.bounser.sculktronics.components.ElectroComponent;
import me.bounser.sculktronics.tools.Data;
import me.leoko.advancedgui.utils.components.RectComponent;
import me.leoko.advancedgui.utils.interactions.Interaction;

import java.awt.*;
import java.util.List;

public class NOT implements ElectroComponent {

    /*
    TRUTH TABLE:

    A | Output
    1 | 0
    0 | 1

     */

    Circuit circuit;
    int[] pos;
    int direction;
    boolean negated;

    Color basicColor;
    Color poweredColor;

    boolean powered;

    List<RectComponent> icon;

    public NOT(Circuit circuit, int[] pos, int direction, boolean negated){
        this.direction = direction;
        this.negated = negated;

        basicColor = Data.getInstance().getDiodeBasicColor();
        poweredColor = Data.getInstance().getDiodePoweredColor();

        this.circuit = circuit;
        this.pos = new int[]{pos[0], pos[1]};
        placeIcon();
    }

    @Override
    public EComponent getEComponent() {
        return null;
    }

    @Override
    public int getLocations() {
        return 0;
    }

    @Override
    public boolean isPowered() {
        return false;
    }

    @Override
    public int getOutput() {
        return 0;
    }

    @Override
    public int getDirection() {
        return 0;
    }

    @Override
    public Color getBasicColor() {
        return null;
    }

    @Override
    public Color getPoweredColor() {
        return null;
    }

    @Override
    public boolean hasIcon() {
        return false;
    }

    @Override
    public void placeIcon() {
        Color black = new Color(0,0,0);

        for(Interaction interaction : circuit.getInteractions()){
            icon.add(new RectComponent("IconI", null, false, interaction, pos[0] + 2, pos[1] + 5, 2, 4, black));
            icon.add(new RectComponent("IconI", null, false, interaction, pos[0] + 2, pos[1] + 4, 5, 2, black));
            icon.add(new RectComponent("IconI", null, false, interaction, pos[0] + 6, pos[1] + 1, 2, 5, black));
        }

    }

    @Override
    public void removeIcon() {
        for(RectComponent rect : icon){
            rect.dispose();
        }
    }
}
