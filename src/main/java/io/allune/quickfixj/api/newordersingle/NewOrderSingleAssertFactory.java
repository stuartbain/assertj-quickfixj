package io.allune.quickfixj.api.newordersingle;

import static io.allune.quickfixj.error.ShouldBeSupportedFixVersion.shouldBeSupportedFixVersion;
import static io.allune.quickfixj.error.ShouldBeValidMessage.shouldBeValidMessage;
import static io.allune.quickfixj.internal.Messages.createFromMessage;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.FIX50;
import static quickfix.FixVersions.FIX50SP1;
import static quickfix.FixVersions.FIX50SP2;
import static quickfix.field.MsgType.ORDER_SINGLE;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.Failures;

import io.allune.quickfixj.internal.InvalidMessageException;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingleAssertFactory {

	protected Failures failures = Failures.instance();

	final static Map<String, Function<String, AbstractNewOrderSingleAssert>> map = new HashMap<String, Function<String, AbstractNewOrderSingleAssert>>() {

		{
			put(BEGINSTRING_FIX40, messageData -> new NewOrderSingleAssert.NewOrderSingle40Assert(createFromMessage(BEGINSTRING_FIX40, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX41, messageData -> new NewOrderSingleAssert.NewOrderSingle41Assert(createFromMessage(BEGINSTRING_FIX41, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX42, messageData -> new NewOrderSingleAssert.NewOrderSingle42Assert(createFromMessage(BEGINSTRING_FIX42, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX43, messageData -> new NewOrderSingleAssert.NewOrderSingle42Assert(createFromMessage(BEGINSTRING_FIX43, ORDER_SINGLE, messageData)));
			put(BEGINSTRING_FIX44, messageData -> new NewOrderSingleAssert.NewOrderSingle42Assert(createFromMessage(BEGINSTRING_FIX44, ORDER_SINGLE, messageData)));
			put(FIX50, (messageData) -> new NewOrderSingleAssert.NewOrderSingle42Assert(createFromMessage(FIX50, ORDER_SINGLE, messageData)));
			put(FIX50SP1, (messageData) -> new NewOrderSingleAssert.NewOrderSingle42Assert(createFromMessage(FIX50SP1, ORDER_SINGLE, messageData)));
			put(FIX50SP2, (messageData) -> new NewOrderSingleAssert.NewOrderSingle42Assert(createFromMessage(FIX50SP2, ORDER_SINGLE, messageData)));
		}
	};

	public AbstractNewOrderSingleAssert newOrderSingleAssertFromFixVersion(AssertionInfo info, String beginString, String messageData) {
		try {
			Function<String, AbstractNewOrderSingleAssert> newOrderSingleAssertFunction = map.get(beginString);
			if (newOrderSingleAssertFunction == null) {
				throw failures.failure(info, shouldBeSupportedFixVersion(beginString));
			}

			return newOrderSingleAssertFunction.apply(messageData);
		} catch (InvalidMessageException e) {
			throw failures.failure(info, shouldBeValidMessage(messageData, e.getMessage()));
		}
	}
}
