package com.kingmang.lazurite.libraries.lzrx.awt.lgl.effects;

import com.kingmang.lazurite.core.Function;
import com.kingmang.lazurite.libraries.lzrx.awt.lgl.value.EffectValue;
import com.kingmang.lazurite.runtime.values.LzrValue;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class LShadow implements Function {
        @Override
        public @NotNull LzrValue execute(@NotNull LzrValue... args) {
            Shadow effect = switch (args.length) {
                case 2 -> new Shadow(args[0].asNumber(), (Color) args[1].raw());
                case 3 -> new Shadow(BlurType.values()[args[0].asInt()], (Color) args[1].raw(),
                        args[2].asNumber());
                default -> new Shadow();
            };
            return new EffectValue(effect);
        }
    }