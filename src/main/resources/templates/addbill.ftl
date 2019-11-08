<#import "parts/common.ftl" as C>
<@C.page>
User page
<div>
    <form method="post">
        <input type="text" name="currency" placeholder="Валюта"/> <br>
        <input type="text" name="amoung" placeholder="деньги"/><br>
        <input type="hidden" value="${user.id}" name="user_id"/><br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>

</@C.page>