<#import "parts/common.ftl" as C>
<@C.page>
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

</div>

Лист счетов :
<#list bills as b>
<div>
    <i>${b.number_card}</i>
    <i>${b.currency.value}</i>
    <i>${b.amoung}</i>
</div>
<#else>
no bills
</#list>

</@C.page>