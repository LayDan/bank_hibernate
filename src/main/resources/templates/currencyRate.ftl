<#import "parts/common.ftl" as C>
<@C.page>
Таблица рейтинга валют :
<#list currencyRates as c>

<table>
    <tr>
        <td><i>${c.first.valute}</i></td>
        <td><i>${c.x}</i></td>
        <td><i>${c.second.valute}</i></td>
    </tr>
</table>
<#else>
no rates
</#list>
</@C.page>