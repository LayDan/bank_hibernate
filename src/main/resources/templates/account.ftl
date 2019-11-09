<#import "parts/common.ftl" as C>
<@C.page>
User page
<>
<a href="/addbill">Add bill</a>

Лист счетов :
<#list bills as b>
<div>
    <i>${b.number_card}</i>
    <i>${b.currency}</i>
    <i>${b.amoung}</i>
</div>
<#else>
no bills
</#list>
</div>

</@C.page>