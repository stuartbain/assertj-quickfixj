# assertj-quickfixj

    // Given
    Message message = new Message(
            "8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");
    
    // When/Then
    //@formatter:off
    assertThat(message)
            .hasFieldValue(Account.FIELD, "Marcel")
            .hasFieldValue(ClOrdID.FIELD, "13346")
            .hasFieldValue(Side.FIELD, "1")
            .hasFieldValue(Symbol.FIELD, "GBP/USD")
            .hasFieldValue(OrdType.FIELD, "2");
    //@formatter:on
    

    
    // Given
    Message message = new Message(
            "8=FIX.4.0\u00019=122\u000135=D\u000134=215\u000149=CLIENT12\u000152=20100225-19:41:57.316\u000138=1000\u000156=B\u00011=Marcel\u000111=13346\u000121=1\u000140=2\u000144=5\u000154=1\u000155=GBP/USD\u000159=0\u000160=20100225-19:39:52.020\u000110=074\u0001");
    
    // When/Then
    //@formatter:off
    assertThat(message)
            .isVersion40()
            .header()
                .hasField(BeginString.FIELD)
                .hasField(BodyLength.FIELD)
                .hasField(MsgType.FIELD)
                .hasField(MsgSeqNum.FIELD)
                .hasField(SenderCompID.FIELD)
                .hasField(SendingTime.FIELD)
                .hasField(TargetCompID.FIELD)
            .and()
            .trailer()
                .hasField(CheckSum.FIELD)
            .and()
                .hasField(Account.FIELD)
                .hasField(ClOrdID.FIELD)
                .hasField(Side.FIELD)
                .hasField(Symbol.FIELD)
                .hasField(OrdType.FIELD)
    ;
    //@formatter:on