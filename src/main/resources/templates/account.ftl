<#import "parts/common.ftl" as C>
<@C.page>

<#if amoung??>
${amoung} UAH
<#else>
</#if>

<div>User page</div>

<span>${userInfo.username}</span>
<div>
    <span>${userInfo.firstName}</span>
    <span>${userInfo.lastName}</span>
    <span>${userInfo.age}</span>
</div>
<div>

    <a href="/addbill">Add bill</a> <br>

    <a href="/addMoney">Add money to bill</a><br>

    <a href="/transfer">перевести деньги на другой счёт</a><br>

    <a href="/convert">Конвертация</a><br>

    <a href="/convertAllToUAH">Сумма всех счетов в UAH</a><br>

</div>

Лист счетов :
<#list bills as b>
<div>
    <i>${b.number_card}</i>
    <i>${b.currency.valute}</i>
    <i>${b.amoung}</i>
</div>
<#else>
no bills
</#list>

</@C.page>