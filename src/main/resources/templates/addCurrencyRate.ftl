<#import "parts/common.ftl" as C>
<@C.page>
<div>
    <form method="post">
        <select name="firstValue">
            <#list currencys as currency>
            <option name="firstValue">${currency.value}</option>
            <#else>
            none
        </#list>
        </select>
        <br>
        <input type="text" name="coefficient" placeholder="К"/> <br>
        <select name="secondValue">
            <#list currencys as currency>
            <option name="secondValue">${currency.value}</option>
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