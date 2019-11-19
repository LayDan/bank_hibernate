<#import "parts/common.ftl" as C>
<@C.page>
<div>
    <form method="post">
        <input type="text" name="valute" placeholder="Валюта"/> <br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>
</@C.page>