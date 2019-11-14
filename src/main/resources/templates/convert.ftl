<#import "parts/common.ftl" as C>
<@C.page>
User page
<div>
    <form method="post">

        <select name="number_card">
            <#list numberOfCards as card>
            <option name="number_card">${card.number_card}</option>
            <#else>
            none
        </#list>
        </select>

        <select name="currency">
            <#list currencys as currency>
            <option name="currency">${currency.value}</option>
            <#else>
            none
        </#list>
        </select>
        <input type="hidden" value="${user.id}" name="user_id"/><br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>

</@C.page>