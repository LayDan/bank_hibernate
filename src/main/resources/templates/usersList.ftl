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
    <#list users as u>
    <tr>
        <td>${u.username}</td>
        <td><#list u.roles as role>${role}<#sep>,</#list></td>
        <td><a href="/usersList/${u.id}">edit</a></td>
    </tr>
    </#list>
    </tbody>
</table>
</@C.page>