<#import "parts/common.ftl" as C>
<@C.page>
<div>
    <form method="post">
        <input type="text" name="number_card" placeholder="NumberCard"/> <br>
        <input type="number" name="amoung" step="any" placeholder="Amoung"/> <br>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <div>
            <input type="submit" value="Добавить"/>
        </div>
    </form>
</div>
</@C.page>