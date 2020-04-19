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

import quickfix.fix44.NewOrderSingle;

/**
 * @author Eduardo Sanchez-Ros
 */
public class NewOrderSingle44Assert extends AbstractNewOrderSingle<NewOrderSingle44Assert, NewOrderSingle> {

	public NewOrderSingle44Assert(NewOrderSingle actual) {
		super(NewOrderSingle44Assert.class, actual);
	}

	// From data dictionary
	//  <message name="NewOrderSingle" msgtype="D" msgcat="app">
	//    <field name="ClOrdID" required="Y"/>
	//    <field name="SecondaryClOrdID" required="N"/>
	//    <field name="ClOrdLinkID" required="N"/>
	//    <component name="Parties" required="N"/>
	//    <field name="TradeOriginationDate" required="N"/>
	//    <field name="TradeDate" required="N"/>
	//    <field name="Account" required="N"/>
	//    <field name="AcctIDSource" required="N"/>
	//    <field name="AccountType" required="N"/>
	//    <field name="DayBookingInst" required="N"/>
	//    <field name="BookingUnit" required="N"/>
	//    <field name="PreallocMethod" required="N"/>
	//    <field name="AllocID" required="N"/>
	//    <group name="NoAllocs" required="N">
	//      <field name="AllocAccount" required="N"/>
	//      <field name="AllocAcctIDSource" required="N"/>
	//      <field name="AllocSettlCurrency" required="N"/>
	//      <field name="IndividualAllocID" required="N"/>
	//      <component name="NestedParties" required="N"/>
	//      <field name="AllocQty" required="N"/>
	//    </group>
	//    <field name="SettlType" required="N"/>
	//    <field name="SettlDate" required="N"/>
	//    <field name="CashMargin" required="N"/>
	//    <field name="ClearingFeeIndicator" required="N"/>
	//    <field name="HandlInst" required="N"/>
	//    <field name="ExecInst" required="N"/>
	//    <field name="MinQty" required="N"/>
	//    <field name="MaxFloor" required="N"/>
	//    <field name="ExDestination" required="N"/>
	//    <group name="NoTradingSessions" required="N">
	//      <field name="TradingSessionID" required="N"/>
	//      <field name="TradingSessionSubID" required="N"/>
	//    </group>
	//    <field name="ProcessCode" required="N"/>
	//    <component name="Instrument" required="Y"/>
	//    <component name="FinancingDetails" required="N"/>
	//    <group name="NoUnderlyings" required="N">
	//      <component name="UnderlyingInstrument" required="N"/>
	//    </group>
	//    <field name="PrevClosePx" required="N"/>
	//    <field name="Side" required="Y"/>
	//    <field name="LocateReqd" required="N"/>
	//    <field name="TransactTime" required="Y"/>
	//    <component name="Stipulations" required="N"/>
	//    <field name="QtyType" required="N"/>
	//    <component name="OrderQtyData" required="Y"/>
	//    <field name="OrdType" required="Y"/>
	//    <field name="PriceType" required="N"/>
	//    <field name="Price" required="N"/>
	//    <field name="StopPx" required="N"/>
	//    <component name="SpreadOrBenchmarkCurveData" required="N"/>
	//    <component name="YieldData" required="N"/>
	//    <field name="Currency" required="N"/>
	//    <field name="ComplianceID" required="N"/>
	//    <field name="SolicitedFlag" required="N"/>
	//    <field name="IOIID" required="N"/>
	//    <field name="QuoteID" required="N"/>
	//    <field name="TimeInForce" required="N"/>
	//    <field name="EffectiveTime" required="N"/>
	//    <field name="ExpireDate" required="N"/>
	//    <field name="ExpireTime" required="N"/>
	//    <field name="GTBookingInst" required="N"/>
	//    <component name="CommissionData" required="N"/>
	//    <field name="OrderCapacity" required="N"/>
	//    <field name="OrderRestrictions" required="N"/>
	//    <field name="CustOrderCapacity" required="N"/>
	//    <field name="ForexReq" required="N"/>
	//    <field name="SettlCurrency" required="N"/>
	//    <field name="BookingType" required="N"/>
	//    <field name="Text" required="N"/>
	//    <field name="EncodedTextLen" required="N"/>
	//    <field name="EncodedText" required="N"/>
	//    <field name="SettlDate2" required="N"/>
	//    <field name="OrderQty2" required="N"/>
	//    <field name="Price2" required="N"/>
	//    <field name="PositionEffect" required="N"/>
	//    <field name="CoveredOrUncovered" required="N"/>
	//    <field name="MaxShow" required="N"/>
	//    <component name="PegInstructions" required="N"/>
	//    <component name="DiscretionInstructions" required="N"/>
	//    <field name="TargetStrategy" required="N"/>
	//    <field name="TargetStrategyParameters" required="N"/>
	//    <field name="ParticipationRate" required="N"/>
	//    <field name="CancellationRights" required="N"/>
	//    <field name="MoneyLaunderingStatus" required="N"/>
	//    <field name="RegistID" required="N"/>
	//    <field name="Designation" required="N"/>
	//  </message>
}
