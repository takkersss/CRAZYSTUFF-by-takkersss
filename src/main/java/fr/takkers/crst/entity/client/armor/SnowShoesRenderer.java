package fr.takkers.crst.entity.client.armor;

import fr.takkers.crst.item.custom.SnowShoes;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class SnowShoesRenderer extends GeoArmorRenderer<SnowShoes> {
    public SnowShoesRenderer() {
        super(new SnowShoesModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorLeftLeg";
        this.leftLegBone = "armorRightLeg";
        this.rightBootBone = "armorLeftBoot";
        this.leftBootBone = "armorRightBoot";
    }
}