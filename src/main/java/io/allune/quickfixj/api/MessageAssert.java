/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2020-2020 the original author or authors.
 */
package io.allune.quickfixj.api;

import io.allune.quickfixj.internal.Dictionaries;
import io.allune.quickfixj.internal.Messages;
import io.allune.quickfixj.internal.Versions;
import org.assertj.core.internal.Failures;
import org.assertj.core.internal.Objects;
import quickfix.DataDictionary;
import quickfix.FixVersions;
import quickfix.Message;
import quickfix.field.BodyLength;

import static io.allune.quickfixj.error.ShouldBeAdminMessage.shouldBeAdminMessage;
import static io.allune.quickfixj.error.ShouldBeAppMessage.shouldBeAppMessage;
import static io.allune.quickfixj.error.ShouldBeEmptyMessage.shouldBeEmptyMessage;
import static io.allune.quickfixj.error.ShouldBeEqual.shouldBeEqual;
import static io.allune.quickfixj.error.ShouldHaveHeader.shouldHaveHeader;
import static io.allune.quickfixj.error.ShouldHaveTrailer.shouldHaveTrailer;
import static org.assertj.core.util.Preconditions.checkArgument;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.BEGINSTRING_FIXT11;
import static quickfix.field.ApplVerID.FIX50;
import static quickfix.field.ApplVerID.FIX50SP1;
import static quickfix.field.ApplVerID.FIX50SP2;
import static quickfix.field.MsgType.ADVERTISEMENT;
import static quickfix.field.MsgType.ALLOCATION_INSTRUCTION;
import static quickfix.field.MsgType.ALLOCATION_INSTRUCTION_ACK;
import static quickfix.field.MsgType.BID_REQUEST;
import static quickfix.field.MsgType.BID_RESPONSE;
import static quickfix.field.MsgType.BUSINESS_MESSAGE_REJECT;
import static quickfix.field.MsgType.CROSS_ORDER_CANCEL_REPLACE_REQUEST;
import static quickfix.field.MsgType.CROSS_ORDER_CANCEL_REQUEST;
import static quickfix.field.MsgType.DERIVATIVE_SECURITY_LIST;
import static quickfix.field.MsgType.DERIVATIVE_SECURITY_LIST_REQUEST;
import static quickfix.field.MsgType.DONT_KNOW_TRADE;
import static quickfix.field.MsgType.EMAIL;
import static quickfix.field.MsgType.EXECUTION_REPORT;
import static quickfix.field.MsgType.HEARTBEAT;
import static quickfix.field.MsgType.INDICATION_OF_INTEREST;
import static quickfix.field.MsgType.LIST_CANCEL_REQUEST;
import static quickfix.field.MsgType.LIST_EXECUTE;
import static quickfix.field.MsgType.LIST_STATUS;
import static quickfix.field.MsgType.LIST_STATUS_REQUEST;
import static quickfix.field.MsgType.LIST_STRIKE_PRICE;
import static quickfix.field.MsgType.LOGON;
import static quickfix.field.MsgType.LOGOUT;
import static quickfix.field.MsgType.MARKET_DATA_INCREMENTAL_REFRESH;
import static quickfix.field.MsgType.MARKET_DATA_REQUEST;
import static quickfix.field.MsgType.MARKET_DATA_REQUEST_REJECT;
import static quickfix.field.MsgType.MARKET_DATA_SNAPSHOT_FULL_REFRESH;
import static quickfix.field.MsgType.MASS_QUOTE;
import static quickfix.field.MsgType.MASS_QUOTE_ACKNOWLEDGEMENT;
import static quickfix.field.MsgType.MULTILEG_ORDER_CANCEL_REPLACE;
import static quickfix.field.MsgType.NEWS;
import static quickfix.field.MsgType.NEW_ORDER_CROSS;
import static quickfix.field.MsgType.NEW_ORDER_MULTILEG;
import static quickfix.field.MsgType.ORDER_CANCEL_REJECT;
import static quickfix.field.MsgType.ORDER_CANCEL_REPLACE_REQUEST;
import static quickfix.field.MsgType.ORDER_CANCEL_REQUEST;
import static quickfix.field.MsgType.ORDER_LIST;
import static quickfix.field.MsgType.ORDER_MASS_CANCEL_REPORT;
import static quickfix.field.MsgType.ORDER_MASS_CANCEL_REQUEST;
import static quickfix.field.MsgType.ORDER_SINGLE;
import static quickfix.field.MsgType.ORDER_STATUS_REQUEST;
import static quickfix.field.MsgType.QUOTE;
import static quickfix.field.MsgType.QUOTE_CANCEL;
import static quickfix.field.MsgType.QUOTE_REQUEST;
import static quickfix.field.MsgType.QUOTE_STATUS_REQUEST;
import static quickfix.field.MsgType.REGISTRATION_INSTRUCTIONS;
import static quickfix.field.MsgType.REGISTRATION_INSTRUCTIONS_RESPONSE;
import static quickfix.field.MsgType.REJECT;
import static quickfix.field.MsgType.RESEND_REQUEST;
import static quickfix.field.MsgType.SECURITY_DEFINITION;
import static quickfix.field.MsgType.SECURITY_DEFINITION_REQUEST;
import static quickfix.field.MsgType.SECURITY_LIST;
import static quickfix.field.MsgType.SECURITY_LIST_REQUEST;
import static quickfix.field.MsgType.SECURITY_STATUS;
import static quickfix.field.MsgType.SECURITY_STATUS_REQUEST;
import static quickfix.field.MsgType.SECURITY_TYPES;
import static quickfix.field.MsgType.SECURITY_TYPE_REQUEST;
import static quickfix.field.MsgType.SEQUENCE_RESET;
import static quickfix.field.MsgType.SETTLEMENT_INSTRUCTIONS;
import static quickfix.field.MsgType.TEST_REQUEST;
import static quickfix.field.MsgType.TRADE_CAPTURE_REPORT_REQUEST;
import static quickfix.field.MsgType.TRADING_SESSION_STATUS;
import static quickfix.field.MsgType.TRADING_SESSION_STATUS_REQUEST;
import static quickfix.field.MsgType.XML_MESSAGE;

/**
 * @author Eduardo Sanchez-Ros
 * @author Simon Lewis
 */
public class MessageAssert extends AbstractFieldMapAssert<MessageAssert, Message> {

	private final String beginString;
	Objects objects = Objects.instance();
	Failures failures = Failures.instance();
	Messages messages = Messages.instance();
	Versions versions = Versions.instance();
	Dictionaries dictionaries = Dictionaries.instance();

	public MessageAssert(Message message) {
		super(message, MessageAssert.class);
		this.beginString = messages.determineBeginString(info, actual);
	}

	@Override
	public String getBeginString() {
		return beginString;
	}

	protected Message getActual() {
		return actual;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX40}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.0\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=015\u0001&quot;);
	 * 		assertThat(message).isVersion40();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX40}.
	 */
	public MessageAssert isVersion40() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX40);
		return this;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX41}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.1\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=018\u0001&quot;);
	 * 		assertThat(message).isVersion41();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX41}.
	 */
	public MessageAssert isVersion41() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX41);
		return this;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX42}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.2\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=017\u0001&quot;);
	 * 		assertThat(message).isVersion42();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX42}.
	 */
	public MessageAssert isVersion42() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX42);
		return this;
	}

	/**
	 * Verifies that the actual {@link Message}'s FIX version number is {@link FixVersions#BEGINSTRING_FIX43}.
	 * Example:
	 * <pre>
	 * 	<code class='java'>
	 * 		Message message = new Message(&quot;8=FIX.4.3\u00019=61\u000135=A\u000134=1\u000149=BANZAI\u000152=20200408-06:49:07\u000156=EXEC\u000198=0\u0001108=30\u000110=018\u0001&quot;);
	 * 		assertThat(message).isVersion43();
	 * 	</code>
	 * </pre>
	 *
	 * @return this assertion object.
	 * @throws AssertionError if the actual {@code Message} is {@code null}.
	 * @throws AssertionError if the actual {@code Message}'s FIX version number is not {@link FixVersions#BEGINSTRING_FIX43}.
	 */
	public MessageAssert isVersion43() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX43);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion44() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIX44);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion50() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIXT11, FIX50);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion50sp1() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIXT11, FIX50SP1);
		return this;
	}

	/**
	 * @return this assertion object.
	 */
	public MessageAssert isVersion50sp2() {
		versions.assertMessageIsVersion(info, actual, BEGINSTRING_FIXT11, FIX50SP2);
		return this;
	}

	@Override
	public MessageAssert isEqualTo(Object expected) {
		// TODO
		return this;
	}

	public MessageHeaderAssert header() {
		isNotNull();
		if (actual.getHeader() == null)
			throw failures.failure(info, shouldHaveHeader(actual));
		return new MessageHeaderAssert(actual.getHeader(), this, beginString);
	}

	public MessageTrailerAssert trailer() {
		isNotNull();
		if (actual.getTrailer() == null)
			throw failures.failure(info, shouldHaveTrailer(actual));
		return new MessageTrailerAssert(actual.getTrailer(), this, beginString);
	}

	public MessageAssert isAdmin() {
		isNotNull();
		if (!actual.isAdmin())
			throw failures.failure(info, shouldBeAdminMessage(actual));
		return this;
	}

	public MessageAssert isApp() {
		isNotNull();
		if (!actual.isApp())
			throw failures.failure(info, shouldBeAppMessage(actual));
		return this;
	}

	public MessageAssert isEmpty() {
		isNotNull();
		if (!actual.isEmpty())
			throw failures.failure(info, shouldBeEmptyMessage(actual));
		return this;
	}

	public MessageAssert isHeartbeat() {
		messages.assertMessageIsOfType(info, actual, HEARTBEAT);
		return this;
	}

	public MessageAssert isTestRequest() {
		messages.assertMessageIsOfType(info, actual, TEST_REQUEST);
		return this;
	}

	public MessageAssert isResendRequest() {
		messages.assertMessageIsOfType(info, actual, RESEND_REQUEST);
		return this;
	}

	public MessageAssert isReject() {
		messages.assertMessageIsOfType(info, actual, REJECT);
		return this;
	}

	public MessageAssert isSequenceReset() {
		messages.assertMessageIsOfType(info, actual, SEQUENCE_RESET);
		return this;
	}

	public MessageAssert isLogout() {
		messages.assertMessageIsOfType(info, actual, LOGOUT);
		return this;
	}

	public MessageAssert isIndicationOfInterest() {
		messages.assertMessageIsOfType(info, actual, INDICATION_OF_INTEREST);
		return this;
	}

	public MessageAssert isAdvertisement() {
		messages.assertMessageIsOfType(info, actual, ADVERTISEMENT);
		return this;
	}

	public MessageAssert isExecution_report() {
		messages.assertMessageIsOfType(info, actual, EXECUTION_REPORT);
		return this;
	}

	public MessageAssert isOrder_cancel_reject() {
		messages.assertMessageIsOfType(info, actual, ORDER_CANCEL_REJECT);
		return this;
	}

	public MessageAssert isLogon() {
		messages.assertMessageIsOfType(info, actual, LOGON);
		return this;
	}

	public MessageAssert isNews() {
		messages.assertMessageIsOfType(info, actual, NEWS);
		return this;
	}

	public MessageAssert isEmail() {
		messages.assertMessageIsOfType(info, actual, EMAIL);
		return this;
	}

	public MessageAssert isNewOrderSingle() {
		messages.assertMessageIsOfType(info, actual, ORDER_SINGLE);
		return this;
	}

	public MessageAssert isNewOrderList() {
		messages.assertMessageIsOfType(info, actual, ORDER_LIST);
		return this;
	}

	public MessageAssert isOrder_cancel_request() {
		messages.assertMessageIsOfType(info, actual, ORDER_CANCEL_REQUEST);
		return this;
	}

	public MessageAssert isOrder_cancel_replace_request() {
		messages.assertMessageIsOfType(info, actual, ORDER_CANCEL_REPLACE_REQUEST);
		return this;
	}

	public MessageAssert isOrder_status_request() {
		messages.assertMessageIsOfType(info, actual, ORDER_STATUS_REQUEST);
		return this;
	}

	public MessageAssert isAllocation_instruction() {
		messages.assertMessageIsOfType(info, actual, ALLOCATION_INSTRUCTION);
		return this;
	}

	public MessageAssert isList_cancel_request() {
		messages.assertMessageIsOfType(info, actual, LIST_CANCEL_REQUEST);
		return this;
	}

	public MessageAssert isList_execute() {
		messages.assertMessageIsOfType(info, actual, LIST_EXECUTE);
		return this;
	}

	public MessageAssert isList_status_request() {
		messages.assertMessageIsOfType(info, actual, LIST_STATUS_REQUEST);
		return this;
	}

	public MessageAssert isList_status() {
		messages.assertMessageIsOfType(info, actual, LIST_STATUS);
		return this;
	}

	public MessageAssert isAllocation_instruction_ack() {
		messages.assertMessageIsOfType(info, actual, ALLOCATION_INSTRUCTION_ACK);
		return this;
	}

	public MessageAssert isDont_know_trade() {
		messages.assertMessageIsOfType(info, actual, DONT_KNOW_TRADE);
		return this;
	}

	public MessageAssert isQuote_request() {
		messages.assertMessageIsOfType(info, actual, QUOTE_REQUEST);
		return this;
	}

	public MessageAssert isQuote() {
		messages.assertMessageIsOfType(info, actual, QUOTE);
		return this;
	}

	public MessageAssert isSettlement_instructions() {
		messages.assertMessageIsOfType(info, actual, SETTLEMENT_INSTRUCTIONS);
		return this;
	}

	public MessageAssert isMarket_data_request() {
		messages.assertMessageIsOfType(info, actual, MARKET_DATA_REQUEST);
		return this;
	}

	public MessageAssert isMarket_data_snapshot_full_refresh() {
		messages.assertMessageIsOfType(info, actual, MARKET_DATA_SNAPSHOT_FULL_REFRESH);
		return this;
	}

	public MessageAssert isMarket_data_incremental_refresh() {
		messages.assertMessageIsOfType(info, actual, MARKET_DATA_INCREMENTAL_REFRESH);
		return this;
	}

	public MessageAssert isMarket_data_request_reject() {
		messages.assertMessageIsOfType(info, actual, MARKET_DATA_REQUEST_REJECT);
		return this;
	}

	public MessageAssert isQuote_cancel() {
		messages.assertMessageIsOfType(info, actual, QUOTE_CANCEL);
		return this;
	}

	public MessageAssert isQuote_status_request() {
		messages.assertMessageIsOfType(info, actual, QUOTE_STATUS_REQUEST);
		return this;
	}

	public MessageAssert isMass_quote_acknowledgement() {
		messages.assertMessageIsOfType(info, actual, MASS_QUOTE_ACKNOWLEDGEMENT);
		return this;
	}

	public MessageAssert isSecurity_definition_request() {
		messages.assertMessageIsOfType(info, actual, SECURITY_DEFINITION_REQUEST);
		return this;
	}

	public MessageAssert isSecurity_definition() {
		messages.assertMessageIsOfType(info, actual, SECURITY_DEFINITION);
		return this;
	}

	public MessageAssert isSecurity_status_request() {
		messages.assertMessageIsOfType(info, actual, SECURITY_STATUS_REQUEST);
		return this;
	}

	public MessageAssert isSecurity_status() {
		messages.assertMessageIsOfType(info, actual, SECURITY_STATUS);
		return this;
	}

	public MessageAssert isTrading_session_status_request() {
		messages.assertMessageIsOfType(info, actual, TRADING_SESSION_STATUS_REQUEST);
		return this;
	}

	public MessageAssert isTrading_session_status() {
		messages.assertMessageIsOfType(info, actual, TRADING_SESSION_STATUS);
		return this;
	}

	public MessageAssert isMass_quote() {
		messages.assertMessageIsOfType(info, actual, MASS_QUOTE);
		return this;
	}

	public MessageAssert isBusiness_message_reject() {
		messages.assertMessageIsOfType(info, actual, BUSINESS_MESSAGE_REJECT);
		return this;
	}

	public MessageAssert isBid_request() {
		messages.assertMessageIsOfType(info, actual, BID_REQUEST);
		return this;
	}

	public MessageAssert isBid_response() {
		messages.assertMessageIsOfType(info, actual, BID_RESPONSE);
		return this;
	}

	public MessageAssert isList_strike_price() {
		messages.assertMessageIsOfType(info, actual, LIST_STRIKE_PRICE);
		return this;
	}

	public MessageAssert isXml_message() {
		messages.assertMessageIsOfType(info, actual, XML_MESSAGE);
		return this;
	}

	public MessageAssert isRegistration_instructions() {
		messages.assertMessageIsOfType(info, actual, REGISTRATION_INSTRUCTIONS);
		return this;
	}

	public MessageAssert isRegistration_instructions_response() {
		messages.assertMessageIsOfType(info, actual, REGISTRATION_INSTRUCTIONS_RESPONSE);
		return this;
	}

	public MessageAssert isOrder_mass_cancel_request() {
		messages.assertMessageIsOfType(info, actual, ORDER_MASS_CANCEL_REQUEST);
		return this;
	}

	public MessageAssert isOrder_mass_cancel_report() {
		messages.assertMessageIsOfType(info, actual, ORDER_MASS_CANCEL_REPORT);
		return this;
	}

	public MessageAssert isNew_order_cross() {
		messages.assertMessageIsOfType(info, actual, NEW_ORDER_CROSS);
		return this;
	}

	public MessageAssert isCross_order_cancel_replace_request() {
		messages.assertMessageIsOfType(info, actual, CROSS_ORDER_CANCEL_REPLACE_REQUEST);
		return this;
	}

	public MessageAssert isCross_order_cancel_request() {
		messages.assertMessageIsOfType(info, actual, CROSS_ORDER_CANCEL_REQUEST);
		return this;
	}

	public MessageAssert isSecurity_type_request() {
		messages.assertMessageIsOfType(info, actual, SECURITY_TYPE_REQUEST);
		return this;
	}

	public MessageAssert isSecurity_types() {
		messages.assertMessageIsOfType(info, actual, SECURITY_TYPES);
		return this;
	}

	public MessageAssert isSecurity_list_request() {
		messages.assertMessageIsOfType(info, actual, SECURITY_LIST_REQUEST);
		return this;
	}

	public MessageAssert isSecurity_list() {
		messages.assertMessageIsOfType(info, actual, SECURITY_LIST);
		return this;
	}

	public MessageAssert isDerivative_security_list_request() {
		messages.assertMessageIsOfType(info, actual, DERIVATIVE_SECURITY_LIST_REQUEST);
		return this;
	}

	public MessageAssert isDerivative_security_list() {
		messages.assertMessageIsOfType(info, actual, DERIVATIVE_SECURITY_LIST);
		return this;
	}

	public MessageAssert isNew_order_multileg() {
		messages.assertMessageIsOfType(info, actual, NEW_ORDER_MULTILEG);
		return this;
	}

	public MessageAssert isMultileg_order_cancel_replace() {
		messages.assertMessageIsOfType(info, actual, MULTILEG_ORDER_CANCEL_REPLACE);
		return this;
	}

	public MessageAssert isTrade_capture_report_request() {
		messages.assertMessageIsOfType(info, actual, TRADE_CAPTURE_REPORT_REQUEST);
		return this;
	}

	// TODO: Rename to isOfType
	public MessageAssert hasMsgType(String expectedMsgType) {
		messages.assertMessageIsOfType(info, actual, expectedMsgType);
		return this;
	}

	// TODO: Rename to isOfType
	public MessageAssert hasMsgTypeName(String expectedMsgTypeName) {
		String msgType = dictionaries.getSessionDataDictionary(beginString).getMsgType(expectedMsgTypeName);
		System.out.println(msgType);
//		messages.assertMessageIsOfTypeName(info, actual, expectedMsgTypeName);
		return this;
	}

	public MessageAssert hasBodyLength(int expectedBodyLength) {
		isNotNull();

		if (actual.bodyLength() != expectedBodyLength)
			throw failures.failure(info, shouldBeEqual(actual, BodyLength.class, BodyLength.FIELD, actual.bodyLength(), expectedBodyLength));
		return this;
	}

	public MessageAssert usingDataDictionary(String beginString, DataDictionary dataDictionary) {
		checkArgument(dataDictionary != null, "'dataDictionary' must not be null");

		dictionaries.addDataDictionary(beginString, dataDictionary);
		return this;
	}
}
