<#import "parts/common.ftl" as C>
<@C.page>
User editor
<form action="/account" method="post">
    <input type="text" name="username" value="${account.username}">
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${account.roles?seq_contains(role)?string("checked",
            "")}>${role}</label>
    </div>
</#list>
<input type="hidden" value="${account.id}" name="accountId">
<input type="hidden" value="${_csrf.token}" name="_csrf">
<button type="submit">Save</button>
</form>

</@C.page>