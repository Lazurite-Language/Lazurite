package com.kingmang.lazurite.parser.pars;

import com.kingmang.lazurite.lib._TExeprion;
import com.kingmang.lazurite.lib.Arguments;
import com.kingmang.lazurite.runtime.ArrayValue;
import com.kingmang.lazurite.lib.Function;
import com.kingmang.lazurite.runtime.MapValue;
import com.kingmang.lazurite.lib.Types;
import com.kingmang.lazurite.runtime.Value;
import com.kingmang.lazurite.lib.ValueUtils;
import java.util.Map;

public final class REDUCE implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.check(3, args.length);
        
        final Value container = args[0];
        final Value identity = args[1];
        final Function accumulator = ValueUtils.consumeFunction(args[2], 2);
        if (container.type() == Types.ARRAY) {
            Value result = identity;
            final ArrayValue array = (ArrayValue) container;
            for (Value element : array) {
                result = accumulator.execute(result, element);
            }
            return result;
        }
        if (container.type() == Types.MAP) {
            Value result = identity;
            final MapValue map = (MapValue) container;
            for (Map.Entry<Value, Value> element : map) {
                result = accumulator.execute(result, element.getKey(), element.getValue());
            }
            return result;
        }
        throw new _TExeprion("Invalid first argument. Array or map expected");
    }
}