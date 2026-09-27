package com.sweegy.invasioncodered.util.animation;

import net.minecraft.client.model.geom.ModelPart;
import java.util.ArrayList;
import java.util.List;

public class EntityModelCache {
    public int lastAnimStep = -1;
    private final List<TransformData> transforms = new ArrayList<>();

    private static class TransformData {
        float x, y, z;
        float xRot, yRot, zRot;
        float xScale = 1f, yScale = 1f, zScale = 1f;
    }

    public void capture(List<ModelPart> parts) {
        if (transforms.size() != parts.size()) {
            transforms.clear();
            for (int i = 0; i < parts.size(); i++) {
                transforms.add(new TransformData());
            }
        }

        for (int i = 0; i < parts.size(); i++) {
            ModelPart part = parts.get(i);
            TransformData data = transforms.get(i);
            data.x = part.x; data.y = part.y; data.z = part.z;
            data.xRot = part.xRot; data.yRot = part.yRot; data.zRot = part.zRot;
            data.xScale = part.xScale; data.yScale = part.yScale; data.zScale = part.zScale;
        }
    }

    public void apply(List<ModelPart> parts) {
        if (transforms.size() != parts.size()) return;

        for (int i = 0; i < parts.size(); i++) {
            ModelPart part = parts.get(i);
            TransformData data = transforms.get(i);
            part.x = data.x; part.y = data.y; part.z = data.z;
            part.xRot = data.xRot; part.yRot = data.yRot; part.zRot = data.zRot;
            part.xScale = data.xScale; part.yScale = data.yScale; part.zScale = data.zScale;
        }
    }
}