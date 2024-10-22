package com.kingmang.lazurite.libraries.lzr.lang.io;

import com.kingmang.lazurite.core.Function;
import com.kingmang.lazurite.runtime.values.LzrNumber;
import com.kingmang.lazurite.runtime.values.LzrValue;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;

public class LzrByteArrayInputStream {

    protected static ByteArrayInputStream byteArrayInputStream;

    static class newByteArrayInputStream implements Function {
        @Override
        public @NotNull LzrValue execute(@NotNull LzrValue... args) {
            byte[] result = args[0].asString().getBytes();
            byteArrayInputStream = new ByteArrayInputStream(result);
            return LzrNumber.ZERO;
        }
    }

    static class readByteArrayInputStream implements Function {
        @Override
        public @NotNull LzrValue execute(@NotNull LzrValue... args) {
            int read = byteArrayInputStream.read();
            return new LzrNumber(read);
        }
    }
    static class availableByteArrayInputStream implements Function {
        @Override
        public @NotNull LzrValue execute(@NotNull LzrValue... args) {
            int resultOfAvailable = byteArrayInputStream.available();
            return new LzrNumber(resultOfAvailable);
        }
    }

    static class readNBytesByteArrayInputStream implements Function {
        @Override
        public @NotNull LzrValue execute(@NotNull LzrValue... args) {
            byte[] bytes = args[0].asString().getBytes();
            int resultOfReading = byteArrayInputStream.readNBytes(bytes, args[1].asInt(), args[2].asInt());
            return new LzrNumber(resultOfReading);
        }
    }
}
