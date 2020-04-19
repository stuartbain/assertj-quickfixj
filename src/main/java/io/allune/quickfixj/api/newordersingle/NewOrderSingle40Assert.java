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
package io.allune.quickfixj.api.newordersingle;

import quickfix.fix40.NewOrderSingle;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingle40Assert extends AbstractNewOrderSingle<NewOrderSingle40Assert, NewOrderSingle> {

	public NewOrderSingle40Assert(NewOrderSingle actual) {
		super(NewOrderSingle40Assert.class, actual);
	}

	// From data dictionary
	//     <message name="NewOrderSingle" msgtype="D" msgcat="app">
	//      <field name="ClOrdID" required="Y"/>
	//      <field name="ClientID" required="N"/>
	//      <field name="ExecBroker" required="N"/>
	//      <field name="Account" required="N"/>
	//      <field name="SettlmntTyp" required="N"/>
	//      <field name="FutSettDate" required="N"/>
	//      <field name="HandlInst" required="Y"/>
	//      <field name="ExecInst" required="N"/>
	//      <field name="MinQty" required="N"/>
	//      <field name="MaxFloor" required="N"/>
	//      <field name="ExDestination" required="N"/>
	//      <field name="ProcessCode" required="N"/>
	//      <field name="Symbol" required="Y"/>
	//      <field name="SymbolSfx" required="N"/>
	//      <field name="SecurityID" required="N"/>
	//      <field name="IDSource" required="N"/>
	//      <field name="Issuer" required="N"/>
	//      <field name="SecurityDesc" required="N"/>
	//      <field name="PrevClosePx" required="N"/>
	//      <field name="Side" required="Y"/>
	//      <field name="LocateReqd" required="N"/>
	//      <field name="OrderQty" required="Y"/>
	//      <field name="OrdType" required="Y"/>
	//      <field name="Price" required="N"/>
	//      <field name="StopPx" required="N"/>
	//      <field name="Currency" required="N"/>
	//      <field name="IOIID" required="N"/>
	//      <field name="QuoteID" required="N"/>
	//      <field name="TimeInForce" required="N"/>
	//      <field name="ExpireTime" required="N"/>
	//      <field name="Commission" required="N"/>
	//      <field name="CommType" required="N"/>
	//      <field name="Rule80A" required="N"/>
	//      <field name="ForexReq" required="N"/>
	//      <field name="SettlCurrency" required="N"/>
	//      <field name="Text" required="N"/>
	//    </message>

}
