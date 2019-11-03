<#import "parts/common.ftl" as C>
<@C.page>
Список пользователей:
<table>
    <thead>
    <tr>
        <th>UserName</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list accounts as ac>
    <tr>
        <td>${ac.username}</td>
        <td><#list ac.roles as role>${role}<#sep>,</#list></td>
        <td><a href="/account/${ac.id}">edit</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</@C.page>