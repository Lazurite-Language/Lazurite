package com.kingmang.lazurite.libraries.lzr.utils.streamApi;

import com.kingmang.lazurite.core.Arguments;
import com.kingmang.lazurite.core.Function;
import com.kingmang.lazurite.core.Types;
import com.kingmang.lazurite.exceptions.LzrException;
import com.kingmang.lazurite.runtime.values.LzrArray;
import com.kingmang.lazurite.runtime.values.LzrMap;
import com.kingmang.lazurite.runtime.values.LzrNumber;
import com.kingmang.lazurite.runtime.values.LzrValue;
import com.kingmang.lazurite.utils.ValueUtils;

import java.util.Arrays;

public class LzrStream extends LzrMap {

    private final LzrArray container;

    public LzrStream(LzrArray container) {
        super(16);
        this.container = container;
        init();
    }

    private void init() {
        set("skip", this::skip);
        set("limit", this::limit);
        set("custom", this::custom);
        set("count", args -> LzrNumber.of(container.size()));
        set("toArray", args -> container);
    }

    private LzrValue skip(LzrValue[] args) {
        Arguments.check(1, args.length);

        final int skipCount = args[0].asInt();
        final int size = container.size();

        if (skipCount <= 0) return this;
        if (skipCount >= size) {
            return new LzrStream(new LzrArray(0));
        }

        final LzrValue[] result = new LzrValue[size - skipCount];
        System.arraycopy(container.getCopyElements(), skipCount, result, 0, result.length);
        return new LzrStream(new LzrArray(result));
    }

    private LzrValue limit(LzrValue[] args) {
        Arguments.check(1, args.length);

        final int limitCount = args[0].asInt();
        final int size = container.size();

        if (limitCount >= size) return this;
        if (limitCount <= 0) {
            return new LzrStream(new LzrArray(0));
        }

        final LzrValue[] result = new LzrValue[limitCount];
        System.arraycopy(container.getCopyElements(), 0, result, 0, limitCount);
        return new LzrStream(new LzrArray(result));
    }

    private LzrValue sorted(LzrValue[] args) {
        Arguments.checkOrOr(0, 1, args.length);
        final LzrValue[] elements = container.getCopyElements();

        switch (args.length) {
            case 0 -> Arrays.sort(elements);
            case 1 -> {
                final Function comparator = ValueUtils.consumeFunction(args[0], 0);
                Arrays.sort(elements, (o1, o2) -> comparator.execute(o1, o2).asInt());
            }
            default -> throw new LzrException("ArgumentsMismatchException", "Wrong number of arguments");
        }

        return new LzrStream(new LzrArray(elements));
    }

    private LzrValue custom(LzrValue[] args) {
        Arguments.check(1, args.length);
        final Function f = ValueUtils.consumeFunction(args[0], 0);
        final LzrValue result = f.execute(container);
        if (result.type() == Types.ARRAY) {
            return new LzrStream((LzrArray) result);
        }
        return result;
    }
}