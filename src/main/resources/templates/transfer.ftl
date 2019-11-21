<#import "parts/common.ftl" as C>
<@C.page>
User page
<div>
    <form method="post">

        <input type="number" name="numberCard" placeholder="номер карти отправки"/> <br>
        <input type="text" name="amoung" placeholder="деньги"/><br>
        <br>
        <select name="selfNumberCard">
            <#list numberOfCards as card>
            <option name="selfNumberCard">${card.numberCard}</option>
            <#else>
            none
        </#list>
        </select>
        <input type="hidden" value="${user.id}" name="userId"/><br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">

        <div>
            <input type="submit" value="Добавить"/>
        </div>

    </form>
</div>

</@C.page>