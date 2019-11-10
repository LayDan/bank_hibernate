<#import "parts/common.ftl" as C>
<@C.page>
Лист счетов :
<#list currencyRates as c>

<table>
    <tr>
        <td><i>${c.firstValue}</i></td>
        <td><i>${c.coefficient}</i></td>
        <td><i>${c.secondValue}</i></td>
    </tr>
</table>
<#else>
no rates
</#list>
</@C.page>