package com.sweegy.invasioncodered.util.animation;

import net.minecraft.client.model.geom.ModelPart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class EntityModelCache {
    public int lastAnimStep = -1;

    private TransformData[] transforms = new TransformData[0];
    private boolean valid;

    private static final class TransformData {
        float x, y, z;
        float xRot, yRot, zRot;
        float xScale, yScale, zScale;

        void read(ModelPart part) {
            x = part.x; y = part.y; z = part.z;
            xRot = part.xRot; yRot = part.yRot; zRot = part.zRot;
            xScale = part.xScale; yScale = part.yScale; zScale = part.zScale;
        }

        void write(ModelPart part) {
            part.x = x; part.y = y; part.z = z;
            part.xRot = xRot; part.yRot = yRot; part.zRot = zRot;
            part.xScale = xScale; part.yScale = yScale; part.zScale = zScale;
        }
    }

    public boolean canReuse(int step) {
        return valid && lastAnimStep == step;
    }

    public void markCaptured(int step) {
        lastAnimStep = step;
        valid = true;
    }

    private void resize(int count) {
        if (transforms.length == count) return;
        transforms = new TransformData[count];
        for (int i = 0; i < count; i++) transforms[i] = new TransformData();
        valid = false;
    }

    public void capture(ModelPart[] parts) {
        Objects.requireNonNull(parts, "parts");
        resize(parts.length);
        for (int i = 0; i < parts.length; i++) transforms[i].read(parts[i]);
    }

    public void apply(ModelPart[] parts) {
        Objects.requireNonNull(parts, "parts");
        if (!valid || transforms.length != parts.length) return;
        for (int i = 0; i < parts.length; i++) transforms[i].write(parts[i]);
    }

    public void capture(List<ModelPart> parts) {
        Objects.requireNonNull(parts, "parts");
        resize(parts.size());
        for (int i = 0; i < parts.size(); i++) transforms[i].read(parts.get(i));
        valid = true;
    }

    public void apply(List<ModelPart> parts) {
        Objects.requireNonNull(parts, "parts");
        if (!valid || transforms.length != parts.size()) return;
        for (int i = 0; i < parts.size(); i++) transforms[i].write(parts.get(i));
    }
}