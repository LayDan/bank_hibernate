<#import "parts/common.ftl" as C>
<@C.page>
<div>
    <form method="post">
        <select name="first">
            <#list currencys as currency>
            <option name="first">${currency.valute}</option>
            <#else>
            none
        </#list>
        </select>
        <br>
        <input type="text" name="x" placeholder="К"/> <br>
        <select name="second">
            <#list currencys as currency>
            <option name="second">${currency.valute}</option>
            <#else>
            none
        </#list>
        </select>

        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>
</@C.page>