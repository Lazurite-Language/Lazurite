package runtime

import com.kingmang.lazurite.core.Types
import com.kingmang.lazurite.exceptions.LzrException
import com.kingmang.lazurite.parser.ast.ArgumentsBuilder
import com.kingmang.lazurite.parser.ast.expressions.ValueExpression
import com.kingmang.lazurite.parser.ast.statements.ReturnStatement
import com.kingmang.lazurite.runtime.ClassInstanceValue
import com.kingmang.lazurite.runtime.ClassMethod
import com.kingmang.lazurite.runtime.UserDefinedFunction
import com.kingmang.lazurite.runtime.values.LzrMap
import com.kingmang.lazurite.runtime.values.LzrNumber
import com.kingmang.lazurite.runtime.values.LzrString
import com.kingmang.lazurite.runtime.values.LzrValue
import org.junit.Test
import testutils.assertLzrFails
import testutils.assertLzrTypeCastFails
import java.util.*
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ClassInstanceValueTest {

    @Test
    fun access() {
        val thisMap = LzrMap(mapOf(LzrString("field") to LzrString("field_value")))
        val instance = ClassInstanceValue("test", thisMap, null)
        assertEquals(LzrString("field_value"), instance.access(LzrString("field")))
        assertLzrFails(LzrException("RuntimeException", "Unknown member unknown in class test")) {
            instance.access(LzrString("unknown"))
        }
    }

    @Test
    fun set() {
        val thisMap = mapOf<LzrValue, LzrValue>(LzrString("field") to LzrString("field_value"))
        val instance = ClassInstanceValue("test", LzrMap(thisMap), null)
        instance.set(LzrString("field"), LzrString("new_field_value"))
        assertEquals(LzrString("new_field_value"), instance.access(LzrString("field")))
        assertLzrFails(LzrException("RuntimeException", "Unable to add new field field_undefined to class test")) {
            instance.set(LzrString("field_undefined"), LzrString("smh"))
        }
    }

    @Test
    fun raw() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertEquals(null, instance.raw())
    }

    @Test
    fun asInt() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertLzrTypeCastFails("class", "integer") {
            instance.asInt()
        }
    }

    @Test
    fun asNumber() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertLzrTypeCastFails("class", "number") {
            instance.asNumber()
        }
    }

    @Test
    fun asString() {
        val thisMap = LzrMap(mapOf(LzrString("field") to LzrString("field value")))
        val instance = ClassInstanceValue("test", thisMap, null)
        assertEquals("test@$thisMap", instance.asString())
    }

    @Test
    fun asStringWithOverride() {
        val toStringMethod = ClassMethod(
            ArgumentsBuilder().build(),
            ReturnStatement(ValueExpression(LzrString("overrided tostring"))),
            LzrMap.EMPTY
        )
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, toStringMethod)
        assertEquals("overrided tostring", instance.asString())
    }

    @Test
    fun asArray() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertContentEquals(intArrayOf(), instance.asArray())
    }

    @Test
    fun type() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertEquals(Types.CLASS, instance.type())
    }

    @Test
    fun testHashCode() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertEquals(37 * 5 + Objects.hash("test", LzrMap.EMPTY), instance.hashCode())
    }

    @Test
    fun testEquals() {
        val instance1 = ClassInstanceValue("test", LzrMap.EMPTY, null)
        val instance2 = ClassInstanceValue(
            "test",
            LzrMap.EMPTY,
            UserDefinedFunction(ArgumentsBuilder().build(), ReturnStatement(ValueExpression(LzrNumber.ZERO)))
        )
        val instance3 = ClassInstanceValue("test", LzrMap(mapOf(LzrString("key") to LzrString("val"))), null)
        val instance4 = ClassInstanceValue("prod", LzrMap.EMPTY, null)

        assertEquals(true, instance1 == instance1)
        assertEquals(true, instance1 == instance2)
        assertEquals(false, instance1 == instance3)
        assertEquals(false, instance1 == instance4)
    }

    @Test
    fun compareTo() {
        val instance1 = ClassInstanceValue("test", LzrMap.EMPTY, null)
        val instance2 = ClassInstanceValue("test", LzrMap(mapOf(LzrString("key") to LzrString("val"))), null)
        assertEquals(instance1.asString().compareTo(instance1.asString()), instance1.compareTo(instance1))
        assertEquals(instance1.asString().compareTo(instance2.asString()), instance1.compareTo(instance2))
        assertEquals(instance2.asString().compareTo(instance1.asString()), instance2.compareTo(instance1))
    }

    @Test
    fun testToString() {
        val instance = ClassInstanceValue("test", LzrMap.EMPTY, null)
        assertEquals(instance.asString(), instance.toString())
    }
}