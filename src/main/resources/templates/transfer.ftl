<#import "parts/common.ftl" as C>
<@C.page>
User page
<div>
    <form method="post">

        <input type="number" name="number_card" placeholder="номер карти отправки"/> <br>
        <input type="text" name="amoung" placeholder="деньги"/><br>
        <br>
        <select name="self_number_card">
            <#list numberOfCards as card>
            <option name="self_number_card">${card.number_card}</option>
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