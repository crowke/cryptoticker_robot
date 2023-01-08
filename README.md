# cryptoticker_robot

був написаний як спроба закрити практику в універі, за годину часу. відправляєш тікер боту - повертає його ціну на binance.

## приклад роботи

> -btc<br>
> ціна BTC: 16931,40$<br>

> -djaosdlnc<br>
> ви ввели неправильний тікер! спробуйте ще раз

## як заставити його працювати?

- поставити свої дані в [application.properties](https://github.com/qlspd/cryptoticker_robot/blob/main/src/main/resources/application.properties)<br>

telegram.bot.username=cryptotickerbot<br>
telegram.bot.token=1234567890:AAAA-1FlakvcVWewkdsQDKLSSFX20Iq6RBk

- `$ cd cryptoticker_robot`
- `$ gradle bootRun`
