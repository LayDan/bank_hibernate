<#import "parts/common.ftl" as C>
<@C.page>
Лист валют
<#list currencys as c>
<table>
    <tr>
        <td><i>${c.value}</i></td>
    </tr>
</table>
<#else>
no currency
</#list>

</@C.page>