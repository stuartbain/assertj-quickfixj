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
package io.allune.quickfixj.internal;

import quickfix.DataDictionary;
import quickfix.DefaultDataDictionaryProvider;
import quickfix.DefaultMessageFactory;
import quickfix.InvalidMessage;
import quickfix.Message;

/**
 * @author Eduardo Sanchez-Ros
 */
public class Messages {

	private static DefaultDataDictionaryProvider dataDictionaryProvider = new DefaultDataDictionaryProvider();

	private static DefaultMessageFactory messageFactory = new DefaultMessageFactory();

	public static DataDictionary getSessionDataDictionary(String beginString) {
		return dataDictionaryProvider.getSessionDataDictionary(beginString);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Message> T createFromMessage(String beginString, String msgType, String messageData) throws InvalidMessage {
		Message message = messageFactory.create(beginString, msgType);
		message.fromString(messageData, dataDictionaryProvider.getSessionDataDictionary(beginString), false);
		return (T) message;
	}
}
