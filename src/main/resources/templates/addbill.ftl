<#import "parts/common.ftl" as C>
<@C.page>
User page
<div>
    <form method="post">

        <select name="currency">
            <#list currencys as currency>
            <option name="currency">${currency.valute}</option>
            <#else>
            none
        </#list>
        </select>


        <!--        <input type="text" name="currency" placeholder="Валюта"/> <br>-->
        <input type="number" name="amoung" placeholder="деньги"/><br>
        <input type="hidden" value="${user.id}" name="userId"/><br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>

</@C.page>