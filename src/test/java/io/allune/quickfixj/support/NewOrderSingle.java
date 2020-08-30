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
package io.allune.quickfixj.support;

import lombok.experimental.SuperBuilder;
import quickfix.Message;
import quickfix.field.Account;
import quickfix.field.ClOrdID;
import quickfix.field.HandlInst;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.Price;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TransactTime;

import java.time.LocalDateTime;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toMap;
import static quickfix.FixVersions.BEGINSTRING_FIX40;
import static quickfix.FixVersions.BEGINSTRING_FIX41;
import static quickfix.FixVersions.BEGINSTRING_FIX42;
import static quickfix.FixVersions.BEGINSTRING_FIX43;
import static quickfix.FixVersions.BEGINSTRING_FIX44;
import static quickfix.FixVersions.FIX50;
import static quickfix.FixVersions.FIX50SP1;
import static quickfix.FixVersions.FIX50SP2;

/**
 * @author Eduardo Sanchez-Ros
 */
@SuperBuilder
public class NewOrderSingle extends AbstractMessage {

	private static Map<String, Supplier<? extends Message>> map = Stream.of(
			new SimpleEntry<String, Supplier<? extends Message>>(BEGINSTRING_FIX40, quickfix.fix40.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(BEGINSTRING_FIX41, quickfix.fix41.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(BEGINSTRING_FIX42, quickfix.fix42.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(BEGINSTRING_FIX43, quickfix.fix43.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(BEGINSTRING_FIX44, quickfix.fix44.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(FIX50, quickfix.fix50.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(FIX50SP1, quickfix.fix50sp1.NewOrderSingle::new),
			new SimpleEntry<String, Supplier<? extends Message>>(FIX50SP2, quickfix.fix50sp2.NewOrderSingle::new))
			.collect(toMap(Map.Entry::getKey, Map.Entry::getValue));

	private String clientOrderId;

	private Character handlInst;

	private String symbol;

	private Character side;

	private Double orderQty;

	private Character orderType;

	private Double price;

	private String account;

	private LocalDateTime transactTime;

	@Override
	protected <T extends Message> void addMessageFields(T order) {
		ofNullable(clientOrderId).ifPresent(value -> order.setField(new ClOrdID(clientOrderId)));
		ofNullable(handlInst).ifPresent(value -> order.setField(new HandlInst(handlInst)));
		ofNullable(symbol).ifPresent(value -> order.setField(new Symbol(symbol)));
		ofNullable(side).ifPresent(value -> order.setField(new Side(side)));
		ofNullable(orderQty).ifPresent(value -> order.setField(new OrderQty(orderQty)));
		ofNullable(orderType).ifPresent(value -> order.setField(new OrdType(orderType)));
		ofNullable(price).ifPresent(value -> order.setField(new Price(price)));
		ofNullable(account).ifPresent(value -> order.setField(new Account(account)));
		ofNullable(transactTime).ifPresent(value -> order.setField(new TransactTime(transactTime)));
	}

	@Override
	protected Map<String, Supplier<? extends Message>> getMap() {
		return map;
	}
}