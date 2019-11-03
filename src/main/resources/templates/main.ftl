<#import "parts/common.ftl" as C>
<#import "parts/login.ftl" as L>

<@C.page>
<@L.logout>
</@L.logout>

<div>
    <form method="post">
        <input type="text" name="FirstName" placeholder="Введите ваше имя"/> <br>
        <input type="text" name="SecondName" placeholder="Введите вашу фамилию"/><br>
        <input type="number" min="18" name="Age" placeholder="Введите ваш возраст"/><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit">Добавить</button>
        <a href="/account">user</a>
    </form>
</div>

Список пользователей:
<#list users as user>
<div>
    <b>${user.id}</b>
    <b>${user.firstName}</b>
    <b>${user.secondName}</b>
    <b>${user.age}</b>
</div>
</#list>
</@C.page>

