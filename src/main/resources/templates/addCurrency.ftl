<#import "parts/common.ftl" as C>
<@C.page>
<div>
    <form method="post">
        <input type="text" name="firstValue" placeholder="Первая валюта"/> <br>
        <input type="text" name="coefficient" placeholder="К"/> <br>
        <input type="text" name="secondValue" placeholder="Вторая валюта"/> <br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>
</div>

</@C.page>