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

public class Dictionaries {

	private static final Dictionaries INSTANCE = new Dictionaries();

	private static final DefaultDataDictionaryProvider dataDictionaryProvider = new DefaultDataDictionaryProvider();

	//	private static final DefaultMessageFactory messageFactory = new DefaultMessageFactory();

	private Dictionaries() {
		//
	}

	public static Dictionaries instance() {
		return INSTANCE;
	}

	public static DataDictionary getSessionDataDictionary(String beginString) {
		return dataDictionaryProvider.getSessionDataDictionary(beginString);
	}

	public static void addDataDictionary(String beginString, DataDictionary dataDictionary) {
		dataDictionaryProvider.addTransportDictionary(beginString, dataDictionary);
	}

	//	@SuppressWarnings("unchecked")
	//	public static <T extends Message> T createFromMessage(String beginString, String msgType, String messageData) {
	//		Message message = messageFactory.create(beginString, msgType);
	//		DataDictionary dataDictionary = dataDictionaryProvider.getSessionDataDictionary(beginString);
	//
	//		try {
	//			message.fromString(messageData, dataDictionary, true);
	//			dataDictionary.validate(message);
	//		} catch (InvalidMessage | FieldNotFound | IncorrectTagValue | IncorrectDataFormat e) {
	//			throw new InvalidMessageException(e.getMessage(), e);
	//		}
	//		return (T) message;
	//	}
}
